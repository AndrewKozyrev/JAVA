import java.util.Scanner;

public class Main {

  private static final String NOT_VALID_PHONE_RESPONSE = "Неверный формат номера";

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }

      String phone = input.replaceAll("[^0-9]+", "");

      switch (phone.length()) {
        case 11:
          if (phone.charAt(0) != '7' && phone.charAt(0) != '8') {
            System.out.println(NOT_VALID_PHONE_RESPONSE);
            continue;
          } else {
            phone = '7' + phone.substring(1);
          }
          break;

        case 10:
          phone = "7" + phone;
          break;

        default:
          System.out.println(NOT_VALID_PHONE_RESPONSE);
          continue;
      }

      System.out.println(phone);
    }
  }

}
