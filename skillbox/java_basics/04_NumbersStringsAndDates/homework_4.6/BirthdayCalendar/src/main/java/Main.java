import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        int day = 20;
        int month = 10;
        int year = 1992;

        System.out.println(collectBirthdays(year, month, day));

    }

    public static String collectBirthdays(int year, int month, int day) {

        SimpleDateFormat format = new SimpleDateFormat(" - dd.MM.yyyy - EEE", Locale.ENGLISH);
        Calendar calendar = new GregorianCalendar(year,month-1, day);
        StringBuilder birthdays = new StringBuilder();
        Calendar currentDate = new GregorianCalendar();

        for (int i = 0; calendar.before(currentDate); i++) {
            birthdays.append(i).append(format.format(calendar.getTime()));
            calendar.add(Calendar.YEAR, 1);
            if (calendar.before(currentDate)) {
                birthdays.append(System.lineSeparator());
            }
        }

        return birthdays.toString();
    }
}
