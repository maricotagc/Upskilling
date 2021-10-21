package mm.example.Block7.model;

import java.util.Objects;

public class Library {
    private int id;
    private String name;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Library))

            return false;
        Library library = (Library) o;
        return id == library.id && Objects.equals(name, library.name) && Objects.equals(address, library.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address
        );
    }
}