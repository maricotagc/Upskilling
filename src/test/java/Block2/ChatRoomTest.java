package Block2;

import mm.example.Block2.ChatRoom;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;


public class ChatRoomTest {

    @Test
    public void shouldReturnYes() throws IOException {
        Assert.assertEquals("YES", ChatRoom.validateHello("ahhellllloou"));
    }

    @Test
    public void shouldReturnNo() throws IOException {
        Assert.assertEquals("NO", ChatRoom.validateHello("hlelo"));
    }

    @Test
    public void shouldReturnNO() throws IOException {
        Assert.assertEquals("NO", ChatRoom.validateHello("ehllo"));
    }

    @Test
    public void shouldReturnMagic() throws IOException {
        Assert.assertTrue("hhhhhhhhheeeellllllllo".matches(".*h.*e.*l.*l.*o.*"));
        Assert.assertTrue("HHHHHeellllllllo".toLowerCase().matches(".*h.*e.*l.*l.*o.*"));
    }
}