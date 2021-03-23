import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileUtils {
    public static void copyFolder(String sourceDirectory, String destinationDirectory) {
        File source = new File(sourceDirectory);
        File dest = new File(destinationDirectory);
        try {
            if (source.isFile()) {
                Files.createDirectories(dest.toPath());
                File newFile = new File(dest, source.getName());
                Files.copy(source.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
            else if (source.isDirectory()) {
                for (File child : source.listFiles()) {
                    if (child.isDirectory()) {
                        File newDest = new File(dest, child.getName());
                        copyFolder(child.getPath(), newDest.getPath());
                    }
                    else {
                        copyFolder(child.getPath(), dest.getPath());
                    }
                }
            }
            else {
                throw new IOException("Папки <" + source + "> не существует.");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
