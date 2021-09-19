package mm.example.Block7.model;

import java.util.Objects;

public class Book {

    private int id;
    private String name;
    private String author;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public boolean equals(Object o) {if (this == o) return true;if (!(o instanceof Book))

        return false;
        Book book = (Book) o;
        return id == book.id && name.equals(book.name) && author.equals(book.author);
    }

    @Override
    public int hashCode() {return Objects.hash(id, name, author);    }}