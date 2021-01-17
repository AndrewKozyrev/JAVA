package DAY_3_HT;

public class exercise_10 {
    public static void main(String[] args){
        String[] nums = new String[3];
        try {
            nums[4] = "foo";
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Выход за пределы массива.");
        } finally {
            nums[0] = "bar";
            System.out.println("Значение первого элемента массива инициализировано");
        }
    }
}
