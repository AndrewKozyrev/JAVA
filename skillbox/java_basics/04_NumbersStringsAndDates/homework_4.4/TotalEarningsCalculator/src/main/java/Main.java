public class Main {

  public static void main(String[] args) {

    String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";

    int[] indices = new int[6];
    indices[0] = text.indexOf("заработал") + "заработал".length();
    indices[1] = text.indexOf("рублей");
    indices[2] = text.indexOf("-") + 1;
    indices[3] = text.indexOf("рубля");
    indices[4] = text.lastIndexOf("-") + 1;
    indices[5] = text.lastIndexOf("рублей");

    int sum = 0;

    for (int i = 0; i < 5; i+=2) {
      sum += Integer.parseInt(text.substring(indices[i], indices[i+1]).trim());
    }

    System.out.println(sum);

  }

}