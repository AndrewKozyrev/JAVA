package DAY_3_HT;

import java.util.Arrays;

public class exercise_15 {
    public static void main(String[] args){
        double[] nums1 = {2, 1, 0, 5, 4, 8, 9};
        double[] nums2 = {2, 3, 0, 5, 5, 9, 0};
        System.out.println(Arrays.toString(divideArrays(nums1, nums2)));
    }
    public static double[] divideArrays(double[] a, double[] b){
        final int N = a.length;
        double[] result = new double[N];
        for (int i = 0; i < N; i++) {
            try {
                result[i] = a[i] / b[i];
            } catch (ArithmeticException e1) {
                result[i] = Double.NaN;
            } catch (ArrayIndexOutOfBoundsException e2) {
                if (a.length > b.length) { result[i] = a[i]; }
            }
        }
        return result;
    }
}
