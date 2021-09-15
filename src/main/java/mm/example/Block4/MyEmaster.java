package mm.example.Block4;

import java.util.ArrayList;
import java.util.List;

public class MyEmaster {

    private List<Entity> entityList = new ArrayList<>();

    public void addNewMyEmaster(Entity newEntity) {
        entityList.add(newEntity);
    }

    public void removeAllEntitiesByName(String entityName) {
        for (int i = 0; i < entityList.size(); i++) {
            if (entityList.get(i).getNameOfEntity().equals(entityName)) {
                entityList.remove(entityList.get(i));
            }
        }
    }

    public List<Entity> showAllEntities() {
        List<Entity> uniqueEntities = new ArrayList<>();

        for (Entity element : entityList) {
            if (!uniqueEntities.contains(element)) {
                uniqueEntities.add(element);
            }
        }
        return uniqueEntities;
    }

    public void printShowAllEntities() {

        List<Entity> uniqueEntities = new ArrayList<>();
        uniqueEntities = showAllEntities();

        for (Entity element : entityList) {
            System.out.println(uniqueEntities);
        }
    }

    public void removeAllElementInMyEmaster() {
        entityList.removeAll(entityList);
    }
}