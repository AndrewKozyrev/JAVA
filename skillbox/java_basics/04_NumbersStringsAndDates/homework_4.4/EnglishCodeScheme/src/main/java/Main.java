package main.java;

public class Main {

  public static void main(String[] args) {
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    for (Character c : alphabet.toCharArray()) {
      System.out.printf("Буква: %c\t\tКод: %d\n", c, (int) c);
    }
  }

}
