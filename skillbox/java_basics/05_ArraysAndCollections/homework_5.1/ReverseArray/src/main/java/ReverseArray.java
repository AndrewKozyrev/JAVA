public class ReverseArray {

    public static String[] reverse (String[] strings) {
        int n = strings.length;
        for (int i = 0; i < n / 2; i++) {
            String temp = strings[i];
            strings[i] = strings[n-i-1];
            strings[n-i-1] = temp;
        }

        return strings;
    }
}
