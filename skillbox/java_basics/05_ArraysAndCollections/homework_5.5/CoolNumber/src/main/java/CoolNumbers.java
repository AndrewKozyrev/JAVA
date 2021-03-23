import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class CoolNumbers {

    private static final String LETTERS = "АВЕКМНОРСТУХ";
    public static ArrayList<String> coolNumbers = new ArrayList<>();

    public static List<String> generateCoolNumbers() {

        for (int letter1 = 0; letter1 < LETTERS.length(); letter1++) {
            for (int number = 0; number <= 9; number++) {
                for (int letter2 = 0; letter2 < LETTERS.length(); letter2++) {
                    for (int letter3 = 0; letter3 < LETTERS.length(); letter3++) {
                        for (int region = 1; region <= 199; region++) {
                            // making a cool number
                            StringBuilder coolNumber = new StringBuilder();
                            coolNumber.append(LETTERS.charAt(letter1));
                            coolNumber.append(number + "" + number + "" + number);
                            coolNumber.append(LETTERS.charAt(letter2)).append(LETTERS.charAt(letter3));
                            coolNumber.append(String.format("%02d", region));
                            coolNumbers.add(coolNumber.toString());
                        }
                    }
                }
            }
        }

        return coolNumbers;
    }

    public static boolean bruteForceSearchInList(List<String> list, String number) {
        boolean result;
        long startTime = System.nanoTime();
        result = list.contains(number);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.printf("Поиск перебором: номер%s найден, поиск занял %dнс\n",
            result ? "" : " не" , duration);

        return result;
    }

    public static boolean binarySearchInList(List<String> sortedList, String number) {
        int result;
        Collections.sort(sortedList);
        long startTime = System.nanoTime();
        result = Collections.binarySearch(sortedList, number);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.printf("Бинарный поиск: номер%s найден, поиск занял %dнс\n",
            result >= 0 ? "" : " не" , duration);

        return result >= 0;
    }


    public static boolean searchInHashSet(HashSet<String> hashSet, String number) {
        boolean result;
        long startTime = System.nanoTime();
        result = hashSet.contains(number);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.printf("Поиск в HashSet: номер%s найден, поиск занял %dнс\n",
            result ? "" : " не" , duration);

        return result;
    }

    public static boolean searchInTreeSet(TreeSet<String> treeSet, String number) {
        boolean result;
        long startTime = System.nanoTime();
        result = treeSet.contains(number);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.printf("Поиск в TreeSet: номер%s найден, поиск занял %dнс\n",
            result ? "" : " не" , duration);

        return result;
    }

}
