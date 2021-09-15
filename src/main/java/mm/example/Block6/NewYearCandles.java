package mm.example.Block6;

import java.io.IOException;
import java.util.Scanner;

public class NewYearCandles {

    public int calculateHoursOfCandleLight(int[] candlesArr) {
        int candles = candlesArr[0];
        int wentOutCandles = candlesArr[1];
        int lightHours = candlesArr[0];

        while (candles >= wentOutCandles) {
            lightHours = lightHours + (candles / wentOutCandles);
            candles = (candles / wentOutCandles) + (candles % wentOutCandles);
        }
        return lightHours;
    }

    public static void main(String[] args) throws IOException {
        int[] candlesArr = new int[2];
        Scanner scanner = new Scanner(System.in);
        candlesArr[0] = scanner.nextInt();
        candlesArr[1] = scanner.nextInt();
        NewYearCandles test = new NewYearCandles();
        System.out.println(test.calculateHoursOfCandleLight(candlesArr));
    }
}

