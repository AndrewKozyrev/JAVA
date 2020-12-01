package DAY_3_HT;

import java.util.ArrayList;
import java.util.Collections;

public class exercise_14 {
    public static void main(String[] args){
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for (int i = 1; i <= 99; i+= 2){
            nums.add(i);
        }
        System.out.println(nums);
        Collections.reverse(nums);
        System.out.println(nums);
    }
}
