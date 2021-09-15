//C:\Users\maric\Upskilling\src\files\Problem - 486A - Codeforces.pdf

package mm.example.Block3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CalculatingFunction {

    public static Long convertAndValidateInput(String input) throws Exception {
        Long i;
        try {
            i = Long.valueOf(input);
        } catch (Exception e) {
            throw new Exception("The input must be a valid integer. Input: [" + input + "]");
        }

        if (i < 1) {
            throw new Exception("The input integer must be equals or bigger than 1 and less than 1.000.000.000. Input: [" + input + "]");
        }
        return i;
    }

    public static long calculateInteger(Long input) {

        if (input % 2 == 0) {
            return input/2;
        } else {
            return (input/2 + 1) * -1;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            long input = CalculatingFunction.convertAndValidateInput(br.readLine());
            System.out.println(CalculatingFunction.calculateInteger(input));
        } catch (Exception e) {
            System.out.println("0");
        }
    }
}
