package DAY_3_HT;

import java.util.Scanner;

public class exercise_11 {
    public static void main(String[] args){
        System.out.println("Введите # для завершения.");
        Scanner scan = new Scanner(System.in);
        do {
            double num = Math.random()*6 - 3;
            System.out.println(num);
        }
        while (!scan.nextLine().equals("#"));
    }
}
