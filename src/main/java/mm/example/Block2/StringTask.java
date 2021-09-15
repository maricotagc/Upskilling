//C:\Users\maric\Upskilling\src\files\Problem - 118A - Codeforces.pdf

package mm.example.Block2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringTask {

    public static String insertDot(String initialWord) {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < initialWord.length(); i++) {
            stringBuilder.append(".");
            stringBuilder.append(initialWord.charAt(i));
        }
        return stringBuilder.toString().toLowerCase();
    }

    public static void updateString() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = String.valueOf(br.readLine()).toLowerCase().replaceAll("[aeiouyAEIOUY]", "");
        System.out.println(insertDot(word));
    }

    public static void main(String[] args) throws IOException {
        StringTask.updateString();
    }
}

