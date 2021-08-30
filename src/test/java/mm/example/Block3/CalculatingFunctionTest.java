package mm.example.Block3;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.IsEqual.equalTo;

public class CalculatingFunctionTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldFunctionReturnSuccessfully() {
        Assert.assertEquals(2L, CalculatingFunction.calculateInteger(4L));
    }

    @Test
    public void shouldFunctionReturnSuccessfullyForOdd() {
        Assert.assertEquals(-3L,CalculatingFunction.calculateInteger(5L));
        Assert.assertEquals(-2L,CalculatingFunction.calculateInteger(3L));
    }

    @Test
    public void shouldFunctionReturnUnsuccessfullyForZero() throws Exception {
        thrown.expect(Exception.class);
        thrown.expectMessage(equalTo("The input integer must be equals or bigger than 1 and less than 1.000.000.000. Input: [0]"));
        CalculatingFunction.convertAndValidateInput("0");
    }

    @Test
    public void shouldFunctionReturnUnsuccessfullyForNonNumber() throws Exception {
        thrown.expect(Exception.class);
        thrown.expectMessage(equalTo("The input must be a valid integer. Input: [Mari]"));
        CalculatingFunction.convertAndValidateInput("Mari");
    }
}