import java.util.Scanner;

public class Main {
    public static final String WRONG_INPUT_FORMAT = "Неверный формат ввода";

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String command;
        PhoneBook book = new PhoneBook();
        do {
            System.out.println("Введите номер, имя или команду:");
            command = reader.nextLine();
            if (PhoneBook.parsePhoneNumber(command).equals(WRONG_INPUT_FORMAT)) {
                if (command.equals("LIST")) {
                    for (String contact : book.getAllContacts()) {
                        System.out.println(contact);
                    }
                }
                else if (!PhoneBook.isValidName(command))
                {
                    System.out.println(WRONG_INPUT_FORMAT);
                }
                else if (!book.getPhonesByName(command).isEmpty()) {
                    for (String contact : book.getPhonesByName(command)) {
                        System.out.println(contact);
                    }
                }
                else {
                    System.out.printf("Такого имени в телефонной книге нет.\n"
                        + "Введите номер телефона для абонента \"%s\":\n", command);
                    String phone = reader.nextLine();
                    book.addContact(phone, command);
                }
            }
            else {
                String phone = PhoneBook.parsePhoneNumber(command);
                if (!book.getNameByPhone(phone).isEmpty()) {
                    System.out.println(book.getNameByPhone(phone));
                }
                else {
                    System.out.printf("Такого номера нет в телефонной книге.\n"
                        + "Введите имя абонента для номера \"%s\":\n", phone);
                    String name = reader.nextLine();
                    book.addContact(phone, name);
                }
            }


        } while (!command.isEmpty());
    }
}
