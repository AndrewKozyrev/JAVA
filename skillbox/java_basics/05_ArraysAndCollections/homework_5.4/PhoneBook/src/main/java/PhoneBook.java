import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class PhoneBook {

    private HashMap<String, String> map = new HashMap<>();

    public void addContact(String phone, String name) {
        // проверьте корректность формата имени и телефона
        // если такой номер уже есть в списке, то перезаписать имя абонента
        if (parsePhoneNumber(phone).equals(Main.WRONG_INPUT_FORMAT) || !isValidName(name)) {
            System.out.println(Main.WRONG_INPUT_FORMAT);
        } else {
            map.put(parsePhoneNumber(phone), name);
            System.out.println("Контакт сохранен!");
        }

    }

    public String getNameByPhone(String phone) {
        if (map.containsKey(phone)) {
            return map.get(phone) + " - " + phone;
        }
        return "";
    }

    public Set<String> getPhonesByName(String name) {
        TreeSet<String> contacts = new TreeSet<>();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue().equals(name)) {
                contacts.add(entry.getValue() + " - " + entry.getKey());
            }
        }

        return contacts;
    }

    public Set<String> getAllContacts() {
        TreeSet<String> contacts = new TreeSet<>();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            contacts.add(entry.getValue() + " - " + entry.getKey());
        }

        return contacts;
    }

    public static String parsePhoneNumber(String number) {
        number = number.replaceAll("[^0-9]+", "");

        switch (number.length()) {
            case 11:
                if (number.charAt(0) != '7' && number.charAt(0) != '8') {
                    System.out.println(Main.WRONG_INPUT_FORMAT);
                } else {
                    number = '7' + number.substring(1);
                }
                break;

            case 10:
                number = "7" + number;
                break;

            default:
                number = Main.WRONG_INPUT_FORMAT;
        }

        return number;
    }

    public static boolean isValidName(String name) {
        if (name.matches("[a-zA-Z\\u0400-\\u04FF]+")) {
            return true;
        }
        return false;
    }

}