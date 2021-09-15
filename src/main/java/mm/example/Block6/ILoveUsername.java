//C:\Users\maric\Upskilling\src\files\Problem - 155A - Codeforces.pdf

package mm.example.Block6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ILoveUsername {

    private int amazingPerformances = 0;
    private Integer bestPerformance = null;
    private Integer worstPerformance = null;

    public int verifyAmazingPerformances(String[] points) {
        for (int i = 0; i < points.length; i++) {
            verifyAmazingPerformances(Integer.parseInt(points[i]));
        }
        return amazingPerformances;
    }

    private void verifyAmazingPerformances(int points) {

        if (bestPerformance == null) {
            bestPerformance = points;
        }

        if (worstPerformance == null) {
            worstPerformance = points;
        }

        if (points > bestPerformance) {
            bestPerformance = points;
            amazingPerformances = amazingPerformances + 1;
        }

        if (points < worstPerformance) {
            worstPerformance = points;
            amazingPerformances = amazingPerformances + 1;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int rowsNumber = Integer.parseInt(br.readLine());

        String[] income = br.readLine().split(" ");

        int result = 0;

        ILoveUsername test = new ILoveUsername();

        result = test.verifyAmazingPerformances(income);

        System.out.println(result);
    }
}
