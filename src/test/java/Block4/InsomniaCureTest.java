package Block4;

import mm.example.Block4.InsomniaCure;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class InsomniaCureTest {

    @Test
    public void shouldReturnNumberOfDamagedDragons() {
        List<Integer> inputs = new ArrayList<>();
        inputs.add(2);
        inputs.add(3);
        inputs.add(4);
        inputs.add(5);
        inputs.add(24);
        Assert.assertEquals(17, InsomniaCure.damagedDragons(inputs));
    }
}