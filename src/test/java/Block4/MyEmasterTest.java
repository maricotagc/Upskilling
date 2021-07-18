package Block4;

import mm.example.Block4.Entity;
import mm.example.Block4.MyEmaster;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class MyEmasterTest {

    Entity entity1;
    Entity entity2;
    Entity entity3;
    Entity entity4;
    MyEmaster test;
    ArrayList<Entity> entities1 = new ArrayList<>();

    @Before
    public void initialize() {
        entity1 = new Entity("Brazil", "Brazil");
        entity2 = new Entity("Brazil", "Brazil");
        entity3 = new Entity("Poland", "Poland");
        entity4 = new Entity("Japan", "Japan");
        test = new MyEmaster();
    }

    @Test
    public void shouldReturnTrueForNewEntityOnTheList() {
        test.addNewMyEmaster(entity1);
        test.addNewMyEmaster(entity2);
        test.printShowAllEntities();
    }

    @Test
    public void removeAllEntitiesByName() {
        test.addNewMyEmaster(entity1);
        test.addNewMyEmaster(entity4);
        test.printShowAllEntities();
        test.removeAllEntitiesByName("Japan");
        test.printShowAllEntities();
    }

    @Test
    public void shouldReturnListOfUniqueEntities() {
        List<Entity> expectedEntities = new ArrayList<>();
        expectedEntities.add(entity1);
        expectedEntities.add(entity4);
        test.addNewMyEmaster(entity1);
        test.addNewMyEmaster(entity4);
        Assert.assertEquals(expectedEntities, test.showAllEntities());
    }

    @Test
    public void removeAllElementInMyEmaster() {
        test.addNewMyEmaster(entity1);
        test.addNewMyEmaster(entity4);
        test.printShowAllEntities();
        test.removeAllElementInMyEmaster();
        System.out.println("After removal: ");
        test.printShowAllEntities();
    }
}