package mm.example.Block6;

import org.junit.Assert;
import org.junit.Test;

public class ILoveUsernameTest {

    @Test
    public void shoulReturnNumberOfAmazingPerformances() {
        String[] income = {"1","0","1","1","1","1","0","1","1","0","1","1","1","1","0","0","0","1","0","0","1","1","1","0","1","0","0","1","1","0","0","0","0","1","0","0","0","1","1","0","0","0","1","0","0","0","1","1","1","1","0","0","1","0","1","0","0","1"};
        ILoveUsername test = new ILoveUsername();
        Assert.assertEquals(1, test.verifyAmazingPerformances(income));
    }

}