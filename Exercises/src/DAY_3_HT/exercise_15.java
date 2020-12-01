package DAY_3_HT;

import java.util.Arrays;

public class exercise_15 {
    public static void main(String[] args){
        double[] nums1 = {2, 0, 3, 3.32533};
        double[] nums2 = {3, 1, 0};
        System.out.println(Arrays.toString(divideArrays(nums1, nums2)));
    }
    public static double[] divideArrays(double[] a, double[] b){
        final int N = Math.max(a.length, b.length);
        double[] result = new double[N];
        System.out.printf("\n\nВы пытаетесь разделить\n%s\n\tна\n%s\n\n", Arrays.toString(a), Arrays.toString(b));
        for (int i = 0; i < N; i++) {
            try {
                result[i] = Math.round(1000 * a[i] / b[i]) / 1000.0;
                if (b[i] == 0)  { throw new ArithmeticException(); }
            } catch (ArithmeticException e1) {
                System.out.printf("\nОшибка при попытке деления элемента №%d:\t[%.2f]/[%.0f]" +
                        "\nДеление на ноль запрещено.\nЭлементу №%d частного " +
                        "будет присвоено значение 'NaN'\n\n", i+1, a[i], b[i], i+1);
                result[i] = Double.NaN;
            } catch (ArrayIndexOutOfBoundsException e2) {
                if (a.length > b.length) {
                    System.out.printf("\nВыход за пределы массива-делителя." +
                            "\nЭлементу №%d частного будет присвоено значение" +
                            " [%.3f] делимого\n\n", i+1, a[i]);
                    result[i] = Math.round(1000*a[i])/1000.0;
                }
                else {
                    System.out.printf("\nВыход за пределы массива-делимого." +
                            "\nЭлементу №%d частного будет присвоено значение" +
                            " 'NaN'\n\n", i+1);
                    result[i] = Double.NaN;
                }
            }
        }
        return result;
    }
}
