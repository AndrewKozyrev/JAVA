package DAY_3_HT;

import java.util.Scanner;

public class exercise_04 {
    public static void main(String[] args){
        //  Обработка ввода пользователя
        Scanner scan = new Scanner(System.in);
        short num;
        do {
            System.out.print("Input number: ");
            num = scan.nextShort();
        }
        while(num < 0 || num > 19);
        System.out.print(num);

        // Хранение результата
        int result;
        // Если введено любое, отличное от нуля число
        if (num != 0) {
            result = num;
            while (true) {
                if (num < 2) {
                    break;
                }
                result *= (num - 1);
                num -= 1;
            }
        }
        else {      // Если ввели нуль
            result = 1;
        }

        System.out.print("! = " + result);
    }
}
