import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }

      // Меньше двух пробелов - значит не более двух слов
      if (input.indexOf(' ') == input.lastIndexOf(' ')) {
        System.out.println("Введенная строка не является ФИО");
        break;
      }

      String name1 = input.substring(0, input.indexOf(' '));
      String name2 = input.substring(input.indexOf(' ') + 1, input.lastIndexOf(' '));
      String name3 = input.substring(input.lastIndexOf(' ') + 1);

      // Второе слово содержит пробел - значит слов больше трёх
      if (name2.contains(" ")) {
        System.out.println("Введенная строка не является ФИО");
        break;
      }

      // Слово содержит цифры - значит не является именем
      if (containsNumber(name1) || containsNumber(name2) || containsNumber(name3)) {
        System.out.println("Введенная строка не является ФИО");
        break;
      }

      // Слова точно являются именами
      System.out.printf("Фамилия: %s%n", name1);
      System.out.printf("Имя: %s%n", name2);
      System.out.printf("Отчество: %s%n", name3);
    }
  }

  public static boolean containsNumber(String s) {
    String nums = "0123456789";
    for (int i = 0; i < nums.length(); i++) {
      if (s.contains(String.valueOf(nums.charAt(i)))) {
        return true;
      }
    }
    return false;
  }
}