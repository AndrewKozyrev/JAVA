import com.skillbox.airport.*;
import java.util.List;

public class Loader {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        List<Aircraft> list = airport.getAllAircrafts();
        int count = list.size();
        System.out.println(count);

    }
}
