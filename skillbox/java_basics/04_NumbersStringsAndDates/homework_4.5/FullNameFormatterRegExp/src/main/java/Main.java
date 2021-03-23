import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }

      String[] words = input.split("\\s+");

      if (words.length != 3 || input.matches(".*\\d.*")) {
        System.out.println("Введенная строка не является ФИО");
        break;
      }

      System.out.println("Фамилия: " + words[0]);
      System.out.println("Имя: " + words[1]);
      System.out.println("Отчество: " + words[2]);

    }
  }

}