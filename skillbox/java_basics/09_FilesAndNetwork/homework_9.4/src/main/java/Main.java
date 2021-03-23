import java.io.File;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Main {

  private static final File LOCATION = new File("images");

  public static void main(String[] args) {
    try { // ловить исключения
      // получить ресурс из интернета через протокол HTTP
      Document doc = Jsoup.connect("https://lenta.ru").get();

      // получить все ссылки в <img ... src="example.com" ... >, сделать ссылки FQDN
      List<String> links = doc.select("img").eachAttr("abs:src");
      // очистить каталог перед заполнением
      FileUtils.deleteDirectory(LOCATION);
      short cnt = 1;
      /** для каждой ссылки:  1. причесать имя файла по url ссылки
       *                      2. загрузить данные по ссылке
       *                      3. создать файлы в папке LOCATION
       *                      4. заполнить файл байтами
       */
      for (String link : links) {
        // оставить только последний домен
        int i = link.lastIndexOf('/') + 1;
        String name = link.substring(i);
        // сохранить только имя файла, без расширения .jpg
        int j = name.contains(".jpg") ? name.lastIndexOf('.') : name.length();
        name = name.substring(0, j);
        // заменить спецсимволы на "_"
        name = name.replaceAll("[?.]", "_");
        // добавить расширение jpg к именам файлов
        name = name + ".jpg";
        // скачать данные по ссылке
        byte[] data = Jsoup.connect(link).ignoreContentType(true).execute().bodyAsBytes();
        // добавить имя файла к корню
        File file = new File(LOCATION, name);
        // запись данных в файл
        FileUtils.writeByteArrayToFile(file, data);
        // Вывод в консоль
        System.out.println(cnt++ + " ---> " + name);
      }

    } catch (Exception e) {
      e.printStackTrace(); // выводить исключения
    }

  }

}
