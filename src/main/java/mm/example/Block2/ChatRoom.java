//https://codeforces.com/problemset/problem/58/A

package mm.example.Block2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChatRoom {

    public static String validateHello(String initialWord) throws IOException {

        StringBuilder finalWord = new StringBuilder("     ");

        for (int i = 0; i < initialWord.length(); i++) {
            if (initialWord.charAt(i) == 'h' && finalWord.charAt(0) == ' ') {
                finalWord.setCharAt(0, 'h');
            } else if (initialWord.charAt(i) == 'e' && finalWord.charAt(1) == ' ' && finalWord.charAt(0) != ' ') {
                finalWord.setCharAt(1, 'e');
            } else if (initialWord.charAt(i) == 'l') {
                if (finalWord.charAt(2) == ' '  && finalWord.charAt(1) != ' ') {
                    finalWord.setCharAt(2, 'l');
                } else if (finalWord.charAt(3) == ' '  && finalWord.charAt(2) != ' ') {
                    finalWord.setCharAt(3, 'l');
                }
            } else if (initialWord.charAt(i) == 'o' && finalWord.charAt(4) == ' '  && finalWord.charAt(3) != ' ') {
                finalWord.setCharAt(4, 'o');
            }
        }
        return finalWord.toString().equals("hello") ? "YES" : "NO";
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String initialWord = String.valueOf(br.readLine());

        ChatRoom.validateHello(initialWord);
    }
}
