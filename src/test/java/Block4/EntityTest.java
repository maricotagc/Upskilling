package Block4;

import mm.example.Block4.Entity;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class EntityTest {

    @Test
    public void shouldReturnTrueForEquals() {
        Entity test = new Entity("Brazil", "Brazil");
        Assert.assertTrue(test.equals(test));
    }

    @Test
    public void shouldReturnFalseForEquals() {
        Entity test = new Entity("Brazil", "Brasil");
        Assert.assertFalse(test.equals(test));
    }

    @Test
    public void shouldReturnToStringForCompleteEntity() {
        ArrayList<String> alternateNames = new ArrayList<>();
        alternateNames.add("AN1");
        alternateNames.add("AN2");
        Entity test = new Entity("Brazil", "Brazil");
        test.setComment("Mariana");
        test.setMainIdentifier(56);
        test.setAlternateNames(alternateNames);
        String expected = "nameOfEntity=Brazil| countryOfIncorporation=Brazil| alternateNames='AN1''AN2'|mainIdentifier='56'|comment='Mariana'|";
        Assert.assertEquals(expected, test.toString());
    }

    @Test
    public void shouldReturnToStringForEmptyOptionalAttributes() {
        Entity test = new Entity("Brazil", "Brazil");
        String expected = "nameOfEntity=Brazil| countryOfIncorporation=Brazil| ";
        Assert.assertEquals(expected, test.toString());
    }

    @Test
    public void shouldReturnToStringForOnlyComment() {
        Entity test = new Entity("Brazil", "Brazil");
        String expected = "nameOfEntity=Brazil| countryOfIncorporation=Brazil| comment='Mariana'|";
        test.setComment("Mariana");
        Assert.assertEquals(expected, test.toString());
    }

    @Test
    public void shouldReturnToStringForOnlyIdentifier() {
        Entity test = new Entity("Brazil", "Brazil");
        String expected = "nameOfEntity=Brazil| countryOfIncorporation=Brazil| mainIdentifier='5'|";
        test.setMainIdentifier(5);
        Assert.assertEquals(expected, test.toString());
    }

    @Test
    public void shouldReturnToStringForOnlyAlternateNames() {
        ArrayList altNames = new ArrayList();
        altNames.add("AN1");
        altNames.add("AN2");
        Entity test = new Entity("Brazil", "Brazil");
        test.setAlternateNames(altNames);
        String expected = "nameOfEntity=Brazil| countryOfIncorporation=Brazil| alternateNames='AN1''AN2'|";
        Assert.assertEquals(expected, test.toString());
    }
}