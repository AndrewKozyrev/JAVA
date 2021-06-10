import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;

public class ImageResizer implements Runnable {
  private static int counter = 1;
  private static final long start = Main.start;
  private final int id = counter++;
  private File[] files;
  private String dstFolder;
  private int newWidth;

  public ImageResizer(File[] files, String dstFolder, int newWidth) {
    this.files = files;
    this.dstFolder = dstFolder;
    this.newWidth = newWidth;
  }

  @Override
  public void run() {
    System.out.printf("Thread #%d started after %d ms%n", id, (System.currentTimeMillis() - start));
    try {
      for (File file : files) {
        BufferedImage image = ImageIO.read(file);
        if (image == null) {
          continue;
        }

        var scalingFactor = image.getWidth() / newWidth;
        var newHeight = image.getHeight() / scalingFactor;
        var newImage = Scalr.resize(image, newWidth, newHeight);
        File newFile = new File(dstFolder + "/" + file.getName());
        ImageIO.write(newImage, "jpg", newFile);
      }

    } catch (Exception ex) {
      ex.printStackTrace();
    }

    System.out.printf("Thread #%d finished after %d ms%n", id, (System.currentTimeMillis() - start));
  }
}
