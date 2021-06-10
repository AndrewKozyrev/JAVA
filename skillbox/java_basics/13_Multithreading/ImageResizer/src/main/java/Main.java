import java.io.File;

public class Main {

    private static String srcFolder = "C:\\Users\\hitma\\Downloads\\Image";
    private static String dstFolder = "C:\\Users\\hitma\\Downloads\\dest";
    public static long start;

    public static void main(String[] args) {

        File srcDir = new File(srcFolder);

        start = System.currentTimeMillis();

        // Создаём массив файлов
        File[] files = srcDir.listFiles();
        // было файлов
        var remFiles = files.length;
        // кол-во потоков
        var threadCount = 4;
        for (int i = 0, n = files.length / threadCount; i < threadCount - 1; i++) {
            // запишем следующие n файлов в массив
            var temp = new File[n];
            System.arraycopy(files, i * n, temp,0, n);
            var thread = new ImageResizer(temp, dstFolder, 300);
            // запускаем поток
            new Thread(thread).start();
            // осталось файлов
            remFiles -= n;
        }
        // запишем остаток в массив
        var temp = new File[remFiles];
        System.arraycopy(files, files.length - remFiles, temp, 0, remFiles);
        var thread = new ImageResizer(temp, dstFolder, 300);
        new Thread(thread).start();

    }
}
