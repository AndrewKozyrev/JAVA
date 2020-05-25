package DAY_3_HT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class exercise_18 {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите первое число: ");
        String num1 = read.readLine();
        System.out.print("Введите второе число: ");
        String num2 = read.readLine();
        System.out.println("Результат: " + divideIntegers(num1, num2));
    }

    public static double divideIntegers(String a, String b) throws IllegalArgumentException {
        try {
            int num1 = Integer.parseInt(a);
            int num2 = Integer.parseInt(b);
            if (num2 == 0) {throw new ArithmeticException();}
            return num1 / (double) num2;
        } catch (ArithmeticException e1) {
            throw new ArithmeticException("Делить на ноль нельзя.");

        } catch (NumberFormatException e2) {
            String msg = "Вы пытаетесь разделить " + a + "\tна\t" + b + "\n" +
                    "Хотя бы один из аргументов не является целым числом.\n";
            throw new NumberFormatException(msg + e2.toString());
        }

    }
}
