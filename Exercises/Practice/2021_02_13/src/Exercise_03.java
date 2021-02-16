import java.util.Scanner;

public class Exercise_03 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    double x, y, z;
    x = scanner.nextDouble();
    y = scanner.nextDouble();
    z = scanner.nextDouble();

    double mean = (x + y + z) / 3;
    System.out.println(mean);

    double var1 = Math.floor( mean / 2 );

    if (var1 > 3) {
      System.out.println("Программа выполнена корректно");
    }

  }
}
