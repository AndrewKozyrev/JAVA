package DAY_3_HT;

import java.util.Scanner;

public class exercise_18 {
    public static void main(String[] args){
        System.out.println(divide(0, 0));
    }

    public static double divide(Object a, Object b) throws IllegalArgumentException {
        if ( !Number.class.isInstance(a) || !Number.class.isInstance(b)  )
            throw new IllegalArgumentException("Input numbers!");
        try {
                return ((Number) a ).doubleValue() / ((Number) b ).doubleValue();
        } catch (ArithmeticException e) {
            throw new ArithmeticException(e.toString());
        }

    }
}
