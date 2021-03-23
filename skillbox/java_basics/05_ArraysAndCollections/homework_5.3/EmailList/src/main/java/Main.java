import java.util.Scanner;

public class Main {
    public static final String WRONG_EMAIL_ANSWER = "Неверный формат email";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EmailList emailList = new EmailList();

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }

            String[] command = input.split("\\s+");

            if (command[0].equals("ADD")) {
                emailList.add(command[1]);
            } else if (command[0].equals("LIST")) {
                emailList.printEmails();
            }

        }
    }
}
