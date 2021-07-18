package Block0;

import mm.example.Block0.FindMaxMinArray;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.IsEqual.equalTo;

public class UpskillingTest {
    FindMaxMinArray test = new FindMaxMinArray();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldReturnExpectedResult() {

                Assert.assertEquals(98787, test.findMaxAndMinInArray(new int[]{-1, 5, 1, 1001, 1600, 1450, -3, -4, 0, 98787}));
    }

    @Test
    public void shouldReturnExceptionForNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(equalTo("The array is either empty or null"));
        test.findMaxAndMinInArray(null);
    }

    @Test
    public void shouldReturnExceptionForEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(equalTo("The array is either empty or null"));
        test.findMaxAndMinInArray(new int[]{});
    }

    @Test
    public void shouldReturnExceptionforOutOfRange() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(equalTo("The values in this array are invalid"));
        test.findMaxAndMinInArray(new int[]{1001, 1002, 1499});
    }

    @Test
    public void shouldReturnExceptionforNegative() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(equalTo("The values in this array are invalid"));
        test.findMaxAndMinInArray(new int[]{-1001, -1, -99});
    }
}
