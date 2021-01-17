package DAY_3_HT;

import java.util.ArrayList;

public class exercise_03 {
    public static void main(String[] args){
        int product = 1, sum = 0, i = 1;
        ArrayList<Integer> nums = new ArrayList<Integer>();
        while (product <= 300 && sum <= 200)
        {
            nums.add(i);
            System.out.print(i + "  ");
            i += 3;
            product *= i;
            sum  += i;
        }
        System.out.println("\nTotal count: " + nums.size());
    }
}
