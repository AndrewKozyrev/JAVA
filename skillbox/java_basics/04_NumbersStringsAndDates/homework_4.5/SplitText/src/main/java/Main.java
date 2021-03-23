public class Main {

  public static void main(String[] args) {

  }

  public static String splitTextInToWords(String text) {
    String[] words = text.split("[^a-zA-z’]+");
    StringBuilder result = new StringBuilder();

    for (String word: words) {
      result.append(word).append('\n');
    }
    result.deleteCharAt(result.length()-1);

    return result.toString();
  }

}