import java.util.stream.Stream;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Stream.iterate(new int[] {1, 1}, x -> new int[] {x[1], x[0] + x[1]})
            .limit(10)
            .forEach(x -> System.out.println(x[0]));
    }
}
