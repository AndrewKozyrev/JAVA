package DAY_3_HT;

import java.util.Arrays;
import java.util.Random;

public class exercise_12 {
    public static void main(String[] args){
        int[][] nums = new int[6][7];
        int l_bound = 0, u_bound = 9;
        Random gen = new Random();
        // Initialization of matrix with random integers
        for (int i = 0; i < nums.length; i++){
            for (int j = 0; j < nums[i].length; j++){
                nums[i][j] = gen.nextInt(l_bound + u_bound+1) - l_bound;
                System.out.print(nums[i][j] + " ");
            }
            System.out.println();
        }

        // Implementation of an algorithm
        for (int i = 0; i < nums.length; i++){
            int max = nums[i][0];
            int index = 0;
            for (int j = 0; j < nums[i].length; j++){
                if (max < nums[i][j]){
                    max = nums[i][j];
                    index = j;
                }

            }
            if (index != 0)
            {
                nums[i][index] = nums[i][0];
                nums[i][0] = max;
            }
            System.out.println(Arrays.toString(nums[i]));
        }
    }
}
