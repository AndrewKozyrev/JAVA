import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import model.Line;
import model.Station;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Main {

  private static final String DATA_FILE = "src/main/resources/map.json";
  private static final String PAGE_URL = "https://www.moscowmap.ru/metro.html#lines";
  private static StationIndex index;

  public static void main(String[] args) {
    index = new StationIndex("Moscow");
    try { // отлов исключений
      // получить HTML-код страницы
      var doc = Jsoup.connect(PAGE_URL).maxBodySize(0).get();
      // парсим линии
      parseLines(doc);
      // парсим станции
      parseStations(doc);
      // парсим переходы
      parseTransitions(doc);
      // создаём map.json
      writeToJSON(DATA_FILE);
      // читаем файл
      var metroMap = (JSONObject) readJSON(DATA_FILE);
      // выводим в консоль количество станций на каждой линии
      displayLineInfo(metroMap);

    } catch (Exception e) {
      e.printStackTrace(); // вывод ошибок
    }
  }

  public static void parseLines(Document doc) {
    // получить строки таблицы с линиями
    var temp = doc.select("div[id=\"metrodata\"]  > div > div > span[data-line]");
    /**
     * 1. вытащить значение аттрибута "data-line" из каждой строки
     * 2. вытащить название улицы из каждой строки
     * 3. добавить линию в индекс
     */
    for (var e : temp) {
      // номер линии находится в аттрибуте data-line
      String lineNumber = e.attr("data-line");
      String lineName = e.text();
      var line = new Line(lineName, lineNumber);
      index.addLine(line);
    }
  }

  public static void parseStations(Document doc) {
    // получить таблицы станций
    var temp = doc.select("div[id=\"metrodata\"] div.js-metro-stations");
    /**
     * 1. вытащить номер линии
     * 2. вытащить имя станции
     * 3. добавить станции в схему метро
     */
    for (var e : temp) {
      // номер линии по-прежнему находится в data-line
      String lineNumber = e.attr("data-line");
      var lineStations = e.select("span.name").eachText();
      index.addStations(lineNumber, lineStations);
    }
  }

  public static void parseTransitions(Document doc) {
    // получить таблицы станций
    var temp = doc.select("div[id=\"metrodata\"] div.js-metro-stations");
    /**
     * 1. получить линию
     * 2. получить имя станции
     * 3. получить линию перехода
     * 4. получить станцию перехода
     * 5. создать словарь {станция1 : переходы}
     */
    for (var e : temp) {
      // получить номер линии
      String lineNumber1 = e.attr("data-line");
      // получить только те элементы линии, в которых есть переходы
      var stationWithTransitions = e.select("a:has(span[title])");
      for (var e1 : stationWithTransitions) {
        // имя станции
        String stationName1 = e1.select("span.name").text();
        Station station1 = index.getStation(stationName1, lineNumber1);
        // получить все элементы с переходами
        var elements = e1.select("span[title]");
        var connectedStations = new ArrayList<Station>();
        for (var e2 : elements) {
          // получить аттрибут с номером линии перехода
          String lineNumber2 = e2.attr("class");
          // номер линии перехода находится после последнего тире
          int i = lineNumber2.lastIndexOf("-") + 1;
          lineNumber2 = lineNumber2.substring(i);
          // получить аттрибут с названием станции перехода
          String stationName2 = e2.attr("title");
          // вот таким страшным способом я пытаюсь получить имя станции
          i = stationName2.indexOf("«") + 1;
          int j = stationName2.lastIndexOf("»");
          stationName2 = stationName2.substring(i, j);
          Station station2 = index.getStation(stationName2, lineNumber2);
          connectedStations.add(station2);
        }
        index.addTransition(station1, connectedStations);
      }
    }
  }

  public static void writeToJSON(String filename) throws Exception {
    // корневой объект
    var root = new JSONObject();
    // объект первого уровня (значение : список_значений)
    var stations = (JSONObject) getStationsJSON();
    // добавить в кореневой объект пару станции: объект первого уровня
    root.put("stations", stations);
    // список первого уровня (список списков)
    var connections = (JSONArray) getConnectionsJSON();
    // добавить в кореневой объект пару "connections": список первого уровня
    root.put("connections", connections);
    // список первого уровня (список объектов)
    var lines = (JSONArray) getLinesJSON();
    // добавить в кореневой объект пару "connections": список первого уровня
    root.put("lines", lines);

    try (PrintWriter writer = new PrintWriter(filename)) {
      root.writeJSONString(writer);
    }

  }

  public static Map getStationsJSON() {
    var stations = new JSONObject();
    index.getLines().forEach(line -> {
          // список станций
          var list = new JSONArray();
          // добавить каждую станцию линии в список
          line.getStations().stream().map(Station::getName).forEach(list::add);
          // добавить пару номер_станции: список_станций
          stations.put(line.getNumber(), list);
        }
    );
    return stations;
  }

  public static List getConnectionsJSON() {
    var connections = new JSONArray();
    // для каждого перехода
    index.getTransitions().forEach(t -> {
      // создать список второго уровня (здесь будет инфо по переходу)
      var list = new JSONArray();
      // для каждой станции перехода
      t.getConnected().forEach(s -> {
        // создать объект второго уровня (здесь будет храниться инфо по станции перехода)
        var object = new JSONObject();
        // добавить в него пары ключ-значение
        object.put("line", s.getLine().getNumber());
        object.put("station", s.getName());
        // добавить в список второго уровня объект второго уровня
        list.add(object);
      });
      // добавить в список первого уровня список второго уровня
      connections.add(list);
    });
    return connections;
  }

  public static List getLinesJSON() {
    var lines = new JSONArray();
    // для каждой линии метро
    index.getLines().forEach(l -> {
      // создать объект
      var object = new JSONObject();
      // заполнить объект парами ключ-значение
      object.put("number", l.getNumber());
      object.put("name", l.getName());
      // поместить объект в список первого уровня
      lines.add(object);
    });

    return lines;
  }

  public static Object readJSON(String filename) throws Exception {
    StringBuilder builder = new StringBuilder();
    // прочитать весь файл и записать в список строк, конкатенировать строки
    Files.readAllLines(Paths.get(filename)).forEach(builder::append);
    // получить объект json из текста
    return JSONValue.parse(builder.toString());
  }

  public static void displayLineInfo(JSONObject map) {
    // берём из корневого объекта список линий
    var lines = (JSONArray) map.get("lines");
    for (var line : lines) { // для каждой линии
      // получить номер
      var id = ((JSONObject) line).get("number");
      // получить имя
      var name = ((JSONObject) line).get("name");
      // получить количество станций на данной линии из ключа `stations`
      var size = ((JSONArray) ((JSONObject) map.get("stations")).get(id)).size();
      System.out.printf("=======================================================\n"
          + "Линия: %s\nНазвание: %s\nКоличество станций: %d\n"
          + "=======================================================\n\n\n", id, name, size);
    }
  }
}
