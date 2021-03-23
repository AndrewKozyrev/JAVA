import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line;
        while (true) {
            System.out.println("Введите путь до папки:");
            line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }
            display(line);
        }
    }

    public static void display(String line) {
        long size = FileUtils.calculateFolderSize(line);
        if (size >= 0) {
            String info = FileUtils.outputFolderSize(size);
            System.out.printf("Размер папки %s составляет %s\n\n", line, info);
        }
        else {
            System.err.printf("Папки `%s` не существует!\n\n", line);
        }

    }
}
