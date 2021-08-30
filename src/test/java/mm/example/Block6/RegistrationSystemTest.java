package mm.example.Block6;

import org.junit.Assert;
import org.junit.Test;

public class RegistrationSystemTest {

    @Test
    public void shouldReturnOKForInexistentUser() {
        RegistrationSystem test = new RegistrationSystem();
        Assert.assertEquals("OK", test.createNewUser("Ana"));
    }

    @Test
    public void shouldReturnNewDuplicatedUser() {
        RegistrationSystem test = new RegistrationSystem();
        Assert.assertEquals("OK", test.createNewUser("Ana"));
        Assert.assertEquals("Ana1", test.createNewUser("Ana"));
        Assert.assertEquals("Ana2", test.createNewUser("Ana"));
    }
}