package mm.example.Block7.repository;

import mm.example.Block7.model.Book;
import mm.example.Block7.utils.DatabaseManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BookRepositoryTest {

    @Test
    public void shouldConnectToTheDatabase() throws Exception {
        DatabaseManager databaseManager = new DatabaseManager();
    }

    @Before
    public void setUp() throws Exception {
        DatabaseManager databaseManager = new DatabaseManager();
        try {
            createTables(databaseManager.getConnection());
        } finally {
            databaseManager.closeConnection();
        }
    }

    @Test
    public void shouldReturn1ForBookSuccessfullyAdded() throws Exception {
        DatabaseManager databaseManager = new DatabaseManager();

        Book book1 = new Book();
        book1.setId(1);
        book1.setName("Harry Potter and the Philosophers Stone");
        book1.setAuthor("J.K. Rowling");

        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());

        try {
            int result = bookRepository.add(book1);
            Book book = bookRepository.findById(1);
            Assert.assertEquals(1, result);
            Assert.assertEquals("Harry Potter and the Philosophers Stone", book.getName());
            Assert.assertEquals("J.K. Rowling", book.getAuthor());
            Assert.assertEquals(1, book.getId());
        } finally {
            databaseManager.closeConnection();
        }
    }

    @Test
    public void shouldReturn1ForBookSuccessfullyRemoved() throws Exception {
        DatabaseManager databaseManager = new DatabaseManager();
        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());
        Book book1 = new Book();
        book1.setId(1);
        book1.setName("Harry Potter and the Philosophers Stone");
        book1.setAuthor("J.K. Rowling");
        try {


            bookRepository.add(book1);
            int result = bookRepository.remove(book1.getId());
            Assert.assertEquals(1, result);
        } finally {
            databaseManager.closeConnection();
        }
    }

    @Test
    public void viewAllBooks() throws Exception {
        DatabaseManager databaseManager = new DatabaseManager();
        BookRepository bookRepository =


                new BookRepository(databaseManager.getConnection());

        try {
            Book book1 = new Book();
            book1.setId(1);
            book1.setName("Harry Potter and the Philosophers Stone");
            book1.setAuthor("J.K. Rowling");
            bookRepository.add(book1);

            Book book2 = new Book();
            book2.setId(2);
            book2.setName("Harry Potter and the Chamber of Secrets");
            book2.setAuthor("J.K. Rowling");
            bookRepository.add(book2);

            List<Book> expected = new ArrayList<>();
            expected.add(book1);
            expected.add(book2);

            List<Book> actual = bookRepository.findAll();
            assertThat(expected).hasSameElementsAs(actual);
        } finally {
            databaseManager.closeConnection();
        }
    }

    @Test
    public void findBookById() throws Exception {
        DatabaseManager databaseManager = new DatabaseManager();
        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());
        try {
            Book book1 = new Book();
            book1.setId(6);
            book1.setName("Harry Potter and the Philosophers Stone");
            book1.setAuthor("J.K. Rowling");
            bookRepository.add(book1


            );

            Book book = bookRepository.findById(6);
            Assert.assertEquals(6, book.getId());
        } finally {
            databaseManager.closeConnection();
        }
    }

    public void createTables(Connection connection) throws SQLException {
        Statement statement = null;

        try {
            statement = connection.createStatement();
            String createBookTable = "DROP TABLE IF EXISTS book;" +
                    " CREATE TABLE book " +
                    "(id INT PRIMARY KEY NOT NULL," +
                    " name TEXT NOT NULL, " +
                    " author TEXT NOT NULL)";
            statement.executeUpdate(createBookTable);

            String createLibraryTable = "DROP TABLE IF EXISTS library;" +
                    "CREATE TABLE library " +
                    "(id INT PRIMARY KEY NOT NULL," +
                    " name TEXT NOT NULL, " +
                    " address VARCHAR(500) NOT NULL)";
            statement.executeUpdate(createLibraryTable);

            String createBookLibraryTable = "DROP TABLE IF EXISTS book_library;" +
                    "CREATE TABLE book_library " +
                    "(book_id INT NOT NULL," +
                    " library_id INT NOT NULL, " +
                    " total_copies INT NOT NULL, " +
                    " available_copies INT NOT NULL, " +
                    " FOREIGN KEY(book_id) REFERENCES book(id)," +
                    " FOREIGN KEY(library_id) REFERENCES library(id) )";
            statement.executeUpdate(createBookLibraryTable);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            statement.close();
        }
        System.out.println("Tables created successfully");
    }
}