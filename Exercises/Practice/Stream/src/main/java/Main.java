import java.util.*;

public class Main {

    public static class PhoneBook {

        private static final HashMap<String, String> PHONE_NUMBERS = new HashMap<String, String>() {
            {
                put("Jos de Vos", "016/161616");
                put("An de Toekan", "016/161617");
                put("Kris de Vis", "016/161618");
            }
        };

        private HashMap<String, String> phoneBookEntries = PHONE_NUMBERS;

        PhoneBook() {
        }

        public HashMap<String, String> getPhoneBookEntries() {
            return phoneBookEntries;
        }

        public Optional<String> findPhoneNumberByName(String name) {
            return phoneBookEntries.entrySet().stream()
                .filter(e -> e.getKey().equals(name))
                .map(Map.Entry::getValue)
                .findFirst();
        }

        public Optional<String> findNameByPhoneNumber(String phoneNumber) {
            return phoneBookEntries.entrySet().stream()
                .filter(e -> e.getValue().equals(phoneNumber))
                .map(Map.Entry::getKey)
                .findFirst();
        }

        @Override
        public String toString() {
            System.out.println("Hello from PhoneBook's toString method");
            return "PhoneBook{" +
                "phoneBookEntries=" + phoneBookEntries +
                '}';
        }

    }

    public static void main(String[] args) {
        PhoneBook pb = new PhoneBook();
        pb.findNameByPhoneNumber("016/161616").ifPresent(System.out::println);
    }
}