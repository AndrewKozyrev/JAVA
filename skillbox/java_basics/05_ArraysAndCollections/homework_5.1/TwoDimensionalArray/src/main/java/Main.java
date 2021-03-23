public class Main {
    public static void main(String[] args) {
        char[][] array = TwoDimensionalArray.getTwoDimensionalArray(7);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println("");
        }
    }
}
