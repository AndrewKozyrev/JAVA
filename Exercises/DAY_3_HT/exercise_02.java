package DAY_3_HT;

import java.util.Scanner;

public class exercise_02 {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter 3 digit number: ");
        int n = scan.nextInt();
        int sum = n % 10 + (n / 10) % 10 + (n / 100 ) % 10;
        System.out.println("Sum: " + sum);
    }
}
