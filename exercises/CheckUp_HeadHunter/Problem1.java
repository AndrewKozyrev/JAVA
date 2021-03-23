package CheckUp_HeadHunter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem1 {
    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        
        try {
            input = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] lines = input.split(" ");
        System.out.println( lines[0].charAt(0) + "\n" + lines[1].charAt(1) );
    }
}
