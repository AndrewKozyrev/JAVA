import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int containersInTruck = 12;
        int boxesInContainer = 27;

        Scanner scanner = new Scanner(System.in);
        String boxes = scanner.nextLine();

        int n = 0;

        try {
            n = Integer.parseInt(boxes);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.exit(1);
        }

        int j = 0, k = 0;

        for (int i = 1; i <= n; i++) {
            if (i % (containersInTruck * boxesInContainer) == 1) {
                System.out.println("Грузовик: " + ++j);
            }
            if (i % boxesInContainer == 1) {
                System.out.println("\tКонтейнер: " + ++k);
            }
            System.out.println("\t\tЯщик: " + i);
        }

        System.out.printf("Необходимо:\n"
            + "грузовиков - %d шт.\n"
            + "контейнеров - %d шт.", j, k);

    }

}
