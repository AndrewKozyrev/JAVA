package DAY_3_HT;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class exercise_06 {
    public static void main(String[] args){
        String[] months = {"январь", "февраль", "март", "апрель", "май",
                "июнь", "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"};
        Scanner scan = new Scanner(System.in);
        int index;

        do {
            System.out.print("Введите месяц: ");
            try {
                int number = scan.nextInt();
                index = number - 1;
            } catch (InputMismatchException e) {
                String name = scan.nextLine().toLowerCase();
                index = Arrays.asList(months).indexOf(name);
            }
        }
        while (index < 0 || index > 11);
        switch (index){
            case 0:
            case 1:
            case 11:
                System.out.println("Зима");
                break;
            case 2:
            case 3:
            case 4:
                System.out.println("Весна");
                break;
            case 5:
            case 6:
            case 7:
                System.out.println("Лето");
                break;
            case 8:
            case 9:
            case 10:
                System.out.println("Осень");
                break;
            default:
                System.out.println("Прошу извинить, программа сошла с ума...");
        }

    }
}
