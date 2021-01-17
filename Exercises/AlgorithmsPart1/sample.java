import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class sample {
    public static void main(String[] args) {
        int[] m1 = {3, 4, 5, 7, 9, 10, 1, 2, 0, 0, 10, 12, 13, 14, 14, 8, 0, 2, 3, 4, -2};
        List<Integer> list = new ArrayList<>();
        for (int i : m1) {
    	    list.add(i);
        }
        int temp2 = Collections.max(list) + 1;
        int temp;
        List<Integer> m2 = new ArrayList<Integer>();
        for (int i = 1; i <= m1.length; i++) {
            temp = Collections.min(list);
            m2.add(temp);
            list.set(list.indexOf(temp), temp2);
        }
        System.out.println(m2.toString());
    }
}
