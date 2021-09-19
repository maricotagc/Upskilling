package mm.example.Block7.repository;

import mm.example.Block7.model.Library;
import mm.example.Block7.utils.DatabaseManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class LibraryRepositoryTest {

    @Before
    public void setUp() throws     Exception {
        DatabaseManager databaseManager = new DatabaseManager();
        try {
            createTables(databaseManager.getConnection());
        } finally {
            databaseManager.closeConnection();
        }
    }

    @Test
    public void shouldReturn1ForLibrarySuccessfullyAdded() throws Exception {DatabaseManager databaseManager = new DatabaseManager();LibraryRepository libraryRepository = new LibraryRepository(

            databaseManager.getConnection());

        try {
            int result = libraryRepository.add(1, "NEW YORK PUBLIC LIBRARY", "476 5th Ave, New York, NY 10018, United States");
            Library library = libraryRepository.findById(1);
            Assert.assertEquals(1, result);
            Assert.assertEquals("NEW YORK PUBLIC LIBRARY", library.getName());
            Assert.assertEquals("476 5th Ave, New York, NY 10018, United States", library.getAddress());
            Assert.assertEquals(1, library.getId());
        } finally {
            databaseManager.closeConnection();
        }
    }

    @Test
    public void shouldReturn1ForLibrarySuccessfullyRemoved() throws Exception {DatabaseManager databaseManager = new DatabaseManager();LibraryRepository libraryRepository = new LibraryRepository(

            databaseManager.getConnection());
        try {
            libraryRepository.add(2, "Shanghai Library", "1555 Huaihai Road, Xuhui District, Shanghai, China");
            int result = libraryRepository.remove(2);
            Assert.assertEquals(1, result);
        } finally {
            databaseManager.closeConnection();
        }
    }

    @Test
    public void shouldReturn1ForLibrarySuccessfullyUpdated    () throws Exception {
        DatabaseManager databaseManager = new DatabaseManager();
        LibraryRepository libraryRepository = new LibraryRepository(databaseManager.getConnection());
        Library library;
        try {
            libraryRepository.add(3, "Library and Archives Canada", "395 Wellington St, Ottawa, ON K1A 0N4, Canada");
            library = libraryRepository.findById(3);
            int result = libraryRepository.update(3, "Library and Archives Canada", "385 Wellington St, Ottawa, ON K1A 0N4, Canada");
            Assert.assertEquals(1, result);
            Assert.assertEquals(3, library.getId());
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
                    " name TEXT NOT NULL, "+
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