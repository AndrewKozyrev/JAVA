import core.Line;
import core.Station;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import junit.framework.TestCase;
import org.junit.jupiter.api.DisplayName;

public class RouteCalculatorTest extends TestCase {

  private StationIndex stationIndex;
  private RouteCalculator calculator;

  @Override
  protected void setUp() throws Exception {
    stationIndex = new StationIndex();
    List<String> stationNames = List.of("Первая_1", "Первая_2", "Первая_3",
        "Вторая_1", "Вторая_2", "Вторая_3", "Третья_1", "Третья_2", "Третья_3",
        "Четвертая_1", "Четвертая_2", "Четвертая_3", "Пятая_1", "Пятая_2");

    // creates lines
    List<Line> lines = new ArrayList<>() {{
      add(new Line(1, "Первая"));
      add(new Line(2, "Вторая"));
      add(new Line(3, "Третья"));
      add(new Line(4, "Четвертая"));
      add(new Line(5, "Пятая"));
    }};

    // creates list of stations
    List<Station> stations = makeStations(stationNames, lines);

    // adds stations to each line and adds lines to stationIndex
    lines.stream()
        .peek(stationIndex::addLine)
        .forEach(line ->
        stations.stream()
        .filter(station -> station.getLine().equals(line))
        .forEach(line::addStation));

    // adds connections to stationIndex
    Stream.of(List.of("Первая_2", "Третья_2"), List.of("Вторая_2", "Третья_3"),
    List.of("Вторая_3", "Четвертая_3"))
        .map(this::makeConnection)
        .forEach(stationIndex::addConnection);

    calculator = new RouteCalculator(stationIndex);
  }

  private List<Station> makeStations(List<String> names, List<Line> lines) {
    return names.stream()
        .map(name ->  {
          String[] name_prefix = name.split("_", 2);    // Когда же нас научат рефлексии
          Line line = lines.stream()
              .filter(l -> l.getName().equals(name_prefix[0])).findAny().orElse(null);
          return new Station(name, line);
        })
        .peek(stationIndex::addStation)     // adds stations to stationIndex
        .collect(Collectors.toList());
  }

  private List<Station> makeConnection(List<String> names) {
    return names.stream().map(stationIndex::getStation).collect(Collectors.toList());
  }

  @DisplayName("Проверка маршрута без пересадок")
  public void testGetRouteOnTheLine() {
    System.out.println("testGetRouteOnTheLine");
    Station from = stationIndex.getStation("Первая_1");
    Station to = stationIndex.getStation("Первая_3");
    List<Station> expected = List.of(stationIndex.getStation("Первая_1"),
        stationIndex.getStation("Первая_2"),
        stationIndex.getStation("Первая_3"));
    List<Station> actual = calculator.getShortestRoute(from, to);

    assertEquals(expected, actual);
  }

  @DisplayName("Проверка маршрута с одной пересадкой")
  public void testGetRouteWithOneConnection() {
    System.out.println("testGetRouteWithOneConnection");
    Station from = stationIndex.getStation("Первая_1");
    Station to = stationIndex.getStation("Третья_1");
    List<Station> expected = Stream.of("Первая_1", "Первая_2","Третья_2","Третья_1")
        .map(stationIndex::getStation).collect(Collectors.toList());
    List<Station> actual = calculator.getShortestRoute(from, to);

    assertEquals(expected, actual);
  }

  @DisplayName("Проверка маршрута с двумя пересадками")
  public void testGetRouteWithTwoConnections() {
    System.out.println("testGetRouteWithTwoConnections");
    Station from = stationIndex.getStation("Первая_1");
    Station to = stationIndex.getStation("Вторая_1");
    List<Station> expected = Stream.of("Первая_1", "Первая_2", "Третья_2",
        "Третья_3", "Вторая_2", "Вторая_1")
        .map(stationIndex::getStation)
        .collect(Collectors.toList());
    List<Station> actual = calculator.getShortestRoute(from, to);

    assertEquals(expected, actual);
  }

  @DisplayName("Проверка разных маршрутов.")
  public void testGetShortestRoute() {
    System.out.println("testGetShortestRoute");
    Station from = stationIndex.getStation("Первая_1");
    Station to = stationIndex.getStation("Четвертая_1");
    /*List<Station> expected = Stream.of("Первая_1", "Первая_2", "Третья_2",
        "Третья_3", "Вторая_2", "Вторая_3", "Четвертая_3", "Четвертая_2", "Четвертая_1")
        .map(stationIndex::getStation).collect(Collectors.toList());*/
    List<Station> actual = calculator.getShortestRoute(from, to);
    assertEquals(Collections.emptyList(), actual);
    from = stationIndex.getStation("Первая_1");
    to = stationIndex.getStation("Первая_1");
    actual = calculator.getShortestRoute(from ,to);
    assertEquals(List.of(from), actual);
    from = stationIndex.getStation("Первая_2");
    to = stationIndex.getStation("Третья_2");
    actual = calculator.getShortestRoute(from, to);
    assertEquals(List.of(from, to), actual);
  }

  @DisplayName("Проверка связанности двух станций.")
  public void testIsConnected() {
    System.out.println("testIsConnected");
    Station station1 = stationIndex.getStation("Вторая_3");
    Station station2 = stationIndex.getStation("Пятая_1");
    List<Station> expected = Collections.emptyList();
    List<Station> actual = calculator.getShortestRoute(station1, station2);

    assertEquals(expected, actual);
  }

  @DisplayName("Проверка расчёта времени маршрута.")
  public void testCalculateDuration() {
    System.out.println("testCalculateDuration");
    List<Station> route = Stream.of("Первая_2", "Третья_2",
        "Третья_3", "Вторая_2", "Вторая_3", "Четвертая_3", "Четвертая_2", "Четвертая_1")
        .map(stationIndex::getStation)
        .collect(Collectors.toList());
    var expected = 20.5;
    var actual = RouteCalculator.calculateDuration(route);

    assertEquals(expected, actual);
  }

  @Override
  protected void tearDown() throws Exception {
    System.out.println("Execution done!");
  }
}
