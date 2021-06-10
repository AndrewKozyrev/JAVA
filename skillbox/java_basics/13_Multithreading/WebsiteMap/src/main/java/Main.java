import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ForkJoinPool;

public class Main {

  private static final String RESOURCE = "https://lenta.ru/";
  public static void main(String[] args) {
    String result = new ForkJoinPool().invoke(new WebCrawler(RESOURCE));
    String filename = "sitemap_" + RESOURCE.split("/")[2] + ".txt";
    try {
      Files.writeString(Paths.get("src/main/resources/" + filename), result);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
