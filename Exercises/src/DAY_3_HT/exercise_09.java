package DAY_3_HT;

import java.util.Random;

public class exercise_09 {
    public static void main(String[] args){
        Random gen = new Random();
        int[] nums = new int[8];
        for (int i = 0; i < nums.length; i++){
            nums[i] = gen.nextInt(10) + 1;
            System.out.print(nums[i] + " ");
            if ((i + 1) % 2 != 0){
                nums[i] = 0;
            }
        }
        System.out.println();
        for (int e : nums){
            System.out.print(e + " ");
        }
        // Создать массив из трёх любых элементов
        int[] anyArray = {123, 456, 789};
    }
}
