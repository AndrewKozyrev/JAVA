import java.io.File;

public class FileUtils {

    public static long calculateFolderSize(String path) {
        File file;
        long size = 0;
        try {
            file = new File(path);
            if (!file.exists()) {
                return -1;
            }
            if (file.isFile()) {
                return file.length();
            }
            if (file.isDirectory() && file.list() == null) {
                return 0;
            }
            for (File node : file.listFiles()) {
                size += calculateFolderSize(node.getAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return size;
    }

    public static String outputFolderSize(long size) {
        if (size >= 1e+9) {
            return String.format("%.1f Гб", size / 1e+9);
        }
        else if (size >= 1e+6) {
            return String.format("%.1f Мб", size / 1e+6);
        }
        else if (size >= 1e+3) {
            return String.format("%.1f Кб", size / 1e+3);
        }
        else {
            return size + " байт";
        }
    }
}
