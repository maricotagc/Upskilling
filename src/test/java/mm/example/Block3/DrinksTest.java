package mm.example.Block3;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

import static org.hamcrest.core.IsEqual.equalTo;

public class DrinksTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldReturnExceptionForNonIntegerNumberOfDrinks() throws Exception {
        thrown.expect(Exception.class);
        thrown.expectMessage(equalTo("The input must be a valid integer. User has inputted: [MARI]"));
        Drinks.validateNumberOfDrinks("MARI");
    }

    @Test
    public void shouldReturnIntegerNumberOfDrinks() throws Exception {
        Integer expected = 5;
        Assert.assertEquals(expected , Drinks.validateNumberOfDrinks("5"));
    }

    @Test
    public void shouldReturnExceptionForNonIntegerOJPercentage() throws Exception {
        thrown.expect(Exception.class);
        thrown.expectMessage(equalTo("The orange juice percentage must be a valid integer"));
        String[] input = {"Mari", "Rapha"};
        Drinks.validateOJPercentage(input);
    }

    @Test
    public void shouldReturnArrayListOJPercentage() throws Exception {
        String[] input = {"5","8"};
        ArrayList<Double> expected = new ArrayList<>();
        expected.add(5D);
        expected.add(8D);
        Assert.assertEquals(expected, Drinks.validateOJPercentage(input));
    }

    @Test
    public void shouldReturnPercentage() {
        ArrayList<Double> ojPercentages = new ArrayList<>();
        ojPercentages.add(50D);
        ojPercentages.add(50D);
        ojPercentages.add(100D);
        Double expected = 66.66666666666667D;

        Assert.assertEquals(expected, Drinks.calculateOrangePercentual(3, ojPercentages));
    }
}