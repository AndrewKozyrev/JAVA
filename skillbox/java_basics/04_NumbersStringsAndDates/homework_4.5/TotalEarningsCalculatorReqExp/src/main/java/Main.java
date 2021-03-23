public class Main {

  public static void main(String[] args) {
  }

  public static int calculateSalarySum(String text){
    // Проблема в том, что в результате, первый элемент будет пустой строкой
    String[] nums = text.split("[^0-9]+");
    int sum = 0;
    for (String s : nums) {
      if (s.length() > 0) {
        sum += Integer.parseInt(s);
      }
    }
    return sum;
  }

}