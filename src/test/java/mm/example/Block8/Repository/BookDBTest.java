package mm.example.Block8.Repository;

import mm.example.Block7.Book;
import mm.example.Block8.ConnectionToDB;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class BookDBTest {

    @Test
    public void shouldReturnBookSuccessfullyAddedToDB() throws SQLException, ClassNotFoundException {
        Book book1 = new Book();
        book1.setName("Mari");
        book1.setAuthor("Moita");

        BookDB bookDB = new BookDB(new ConnectionToDB().openDBConnection());
        Assert.assertEquals(1, bookDB.addBook(book1));
        bookDB.viewAllBooks();
    }
}