//C:\Users\maric\Upskilling\src\files\Problem - 71A - Codeforces.pdf

package mm.example.Block1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WayTooLongWords {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int firstNum = Integer.parseInt(br.readLine());

        for (int i = 0; i < firstNum; i++) {
            String word = String.valueOf(br.readLine());

            if (word.length() > 10) {
                int numberOfCharacters = word.length() - 2;
                String firstLetter = String.valueOf(word.charAt(0));
                String lastLetter = String.valueOf(word.charAt(word.length() - 1));
                System.out.println(firstLetter + numberOfCharacters + lastLetter);
            } else {
                System.out.println(word);
            }
        }
    }
}
