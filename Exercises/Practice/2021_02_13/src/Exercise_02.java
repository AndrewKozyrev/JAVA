public class Exercise_02 {

  public static void main(String[] args) {
    int[] array = {11, 22, 33, 44, 55};
    int last = array[array.length-1];

    array[array.length-1] = array[0];
    array[0] = last;
    System.out.println(array[0] + array[(array.length)/2]);
  }
}
