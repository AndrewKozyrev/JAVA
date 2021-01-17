package DAY_3_HT;

public class exercise_08 {
    public static void main(String[] args) {
        int[] buffer = {1, 1};
        int next;
        for (int i = 0; i < 11; i++) {
            System.out.print(buffer[0] + " ");
            next = buffer[0] + buffer[1];
            buffer[0] = buffer[1];
            buffer[1] = next;
        }
    }
}