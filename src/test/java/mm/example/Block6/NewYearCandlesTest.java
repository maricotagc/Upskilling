package mm.example.Block6;

import org.junit.Assert;
import org.junit.Test;

public class NewYearCandlesTest {

    @Test
    public void calculateHoursOfCandleLight() {
        int[] candlesStr1 = new int[2];
        candlesStr1[0] = 1000;
        candlesStr1[1] = 1000;
        NewYearCandles test1 = new NewYearCandles();
        Assert.assertEquals(1001, test1.calculateHoursOfCandleLight(candlesStr1));

        int[] candlesStr2 = new int[2];
        candlesStr2[0] = 123;
        candlesStr2[1] = 5;
        NewYearCandles test2 = new NewYearCandles();
        Assert.assertEquals(153, test2.calculateHoursOfCandleLight(candlesStr2));

        int[] candlesStr3 = new int[2];
        candlesStr3[0] = 1000;
        candlesStr3[1] = 3;
        NewYearCandles test3 = new NewYearCandles();
        Assert.assertEquals(1499, test3.calculateHoursOfCandleLight(candlesStr3));

        int[] candlesStr4 = new int[2];
        candlesStr3[0] = 1000;
        candlesStr3[1] = 2;
        NewYearCandles test4 = new NewYearCandles();
        Assert.assertEquals(1999, test4.calculateHoursOfCandleLight(candlesStr3));
    }
}