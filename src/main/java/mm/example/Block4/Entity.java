package mm.example.Block4;

import java.util.ArrayList;

public class Entity {

    private String nameOfEntity;
    private String countryOfIncorporation;
    private Integer mainIdentifier;
    private ArrayList<String> alternateNames = new ArrayList<>();
    private String comment;

    public Entity(String nameOfEntity, String countryOfIncorporation) {
        this.nameOfEntity = nameOfEntity;
        this.countryOfIncorporation = countryOfIncorporation;
    }

    public void setNameOfEntity(String nameOfEntity) {
        this.nameOfEntity = nameOfEntity;
    }

    public void setCountryOfIncorporation(String countryOfIncorporation) {
        this.countryOfIncorporation = countryOfIncorporation;
    }

    public void setMainIdentifier(Integer mainIdentifier) {
        this.mainIdentifier = mainIdentifier;
    }

    public void setAlternateNames(ArrayList<String> alternateNames) {
        this.alternateNames = alternateNames;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getNameOfEntity() {
        return nameOfEntity;
    }

    public String getCountryOfIncorporation() {
        return countryOfIncorporation;
    }

    public Integer getMainIdentifier() {
        return mainIdentifier;
    }

    public ArrayList<String> getAlternateNames() {
        return alternateNames;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {


        StringBuilder res = new StringBuilder();

        res.append("nameOfEntity=").append(nameOfEntity).append("| ");
        res.append("countryOfIncorporation=").append(countryOfIncorporation).append("| ");

        if (alternateNames.size() > 0) {
            res.append("alternateNames=");
            for (int i = 0; i < alternateNames.size(); i++) {
                res.append("'" + alternateNames.get(i) + "'");
            }
            res.append("|");
        }

        if (this.mainIdentifier != null) {
            res.append("mainIdentifier='" + this.mainIdentifier + "'|");
        }

        if (this.comment != null) {
            res.append("comment='" + this.comment + "'|");
        }

        return res.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entity)) return false;
        Entity entity = (Entity) o;
        return nameOfEntity.equals(entity.nameOfEntity) && countryOfIncorporation.equals(entity.countryOfIncorporation);
    }
}
