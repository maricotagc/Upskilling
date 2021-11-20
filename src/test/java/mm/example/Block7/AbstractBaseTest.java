package mm.example.Block7;

import mm.example.Block7.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractBaseTest {

    private DatabaseManager databaseManager;
    {
        try {
            databaseManager = new DatabaseManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTables(Connection connection) throws SQLException {
        Statement statement = null;

        try {
            statement = connection.createStatement();
            String createBookTable = "DROP TABLE IF EXISTS book;" +
                    " CREATE TABLE book " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " name TEXT NOT NULL, " +
                    " author TEXT NOT NULL)";
            statement.executeUpdate(createBookTable);

            String createLibraryTable = "DROP TABLE IF EXISTS library;" +
                    "CREATE TABLE library " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
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
    }
}