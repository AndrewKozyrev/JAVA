import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        Calendar flightDate = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        final int TWO_HOURS = 2;

        var result = airport.getTerminals().stream()
                .flatMap(x -> x.getFlights().stream())
                .filter(f -> f.getType() == Flight.Type.DEPARTURE)
                .filter(f -> {
                    now.setTime(new Date());
                    flightDate.setTime(f.getDate());
                    return flightDate.after(now);
                })
                .filter(f -> {
                    flightDate.add(Calendar.HOUR_OF_DAY, -TWO_HOURS);
                    return flightDate.before(now);
                })
                .collect(Collectors.toList());

        return result;
    }

}