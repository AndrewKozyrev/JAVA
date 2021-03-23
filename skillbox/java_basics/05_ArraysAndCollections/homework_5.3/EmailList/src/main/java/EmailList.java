import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class EmailList {

   TreeSet<String> emails;

    public EmailList() {
        emails = new TreeSet<>();
    }

    public void add(String email) {
      if (!email.matches("\\w+@\\w+\\.\\w+")) {
        System.out.println(Main.WRONG_EMAIL_ANSWER);
      } else {
        emails.add(email.toLowerCase());
      }
    }

    public List<String> getSortedEmails() {
        return new ArrayList<>(emails);
    }

    public void printEmails() {
        for (String s : getSortedEmails()) {
            System.out.println(s);
        }
    }

}
