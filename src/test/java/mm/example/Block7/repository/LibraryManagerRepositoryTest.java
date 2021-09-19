package mm.example.Block7.repository;

import mm.example.Block7.model.Book;
import mm.example.Block7.model.Library;
import mm.example.Block7.utils.DatabaseManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class LibraryManagerRepositoryTest {
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
    public void addBookToLibrary() throws Exception {Book book1 = new Book();book1.setId(1);book1


            .setName("Harry Potter and the Philosophers Stone");
        book1.setAuthor("'J.K. Rowling");

        Library library1 = new Library();
        library1.setId(2);
        library1.setName("Shanghai Library");
        library1.setAddress("1555 Huaihai Road, Xuhui District, Shanghai, China");

        DatabaseManager databaseManager = new DatabaseManager();

        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());
        LibraryRepository libraryRepository = new LibraryRepository(databaseManager.getConnection());
        LibraryManagerRepository libraryManagerRepository = new LibraryManagerRepository(databaseManager.getConnection());

        try {
            bookRepository.add(book1);
            libraryRepository.add(library1.getId(), library1.getName(), library1.getAddress());
            int result = libraryManagerRepository.addBookToLibrary(book1, library1, 60, 22);
            Assert.assertEquals(1, result);
        } finally {
            databaseManager.closeConnection();
        }
    }

    @Test
    public void updateAvailableBooks() throws Exception {Book book1 = new Book();book1.setId(1);book1.setName("Harry Potter and the Philosophers Stone");book1.setAuthor(



            "'J.K. Rowling");

        Library library1 = new Library();
        library1.setId(2);
        library1.setName("Shanghai Library");
        library1.setAddress("1555 Huaihai Road, Xuhui District, Shanghai, China");

        DatabaseManager databaseManager = new DatabaseManager();

        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());
        LibraryRepository libraryRepository = new LibraryRepository(databaseManager.getConnection());
        LibraryManagerRepository libraryManagerRepository = new LibraryManagerRepository(databaseManager.getConnection());

        try {
            bookRepository.add(book1);
            libraryRepository.add(library1.getId(), library1.getName(), library1.getAddress());
            libraryManagerRepository.addBookToLibrary(book1, library1, 99, 99);
            int result = libraryManagerRepository.updateAvailableBooks(book1, library1, 3);
            Assert.assertEquals(1, result);
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