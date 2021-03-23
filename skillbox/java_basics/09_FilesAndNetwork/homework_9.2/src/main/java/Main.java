import java.util.Scanner;

public class Main {
    private static Scanner scanner;
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        while (true) {
            String sourceDir = request("Введите папку для копирования: ");
            String destDir = request("Введите папку назначения: ");
            FileUtils.copyFolder(sourceDir, destDir);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static String request(String message) {
        System.out.print(message);
        String line = "";
        while (line.isEmpty()) {
            line = scanner.nextLine();
        }
        return line;
    }
}
