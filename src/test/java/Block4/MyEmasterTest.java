package Block4;

import mm.example.Block4.Entity;
import mm.example.Block4.MyEmaster;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

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

    //TODO how to assert a void method?
    @Test
    public void shouldReturnTrueForNewEntityOnTheList() {
        test.addNewMyEmaster(entity1);
        test.addNewMyEmaster(entity2);
        test.showAllEntities();
    }

    //TODO how to assert a void method?
    @Test
    public void removeAllEntitiesByName() {
        test.addNewMyEmaster(entity1);
        test.addNewMyEmaster(entity4);
        test.showAllEntities();
        test.removeAllEntitiesByName("Japan");
        test.showAllEntities();
    }

    @Test
    public void showAllEntities() {
        test.addNewMyEmaster(entity1);
        test.addNewMyEmaster(entity4);
        test.showAllEntities();
    }

    @Test
    public void removeAllElementInMyEmaster() {
        test.addNewMyEmaster(entity1);
        test.addNewMyEmaster(entity4);
        test.showAllEntities();
        test.removeAllElementInMyEmaster();
        test.showAllEntities();
    }
}