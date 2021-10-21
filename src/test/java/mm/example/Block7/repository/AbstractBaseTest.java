package mm.example.Block7.repository;

import mm.example.Block7.model.Book;
import mm.example.Block7.model.Library;
import mm.example.Block7.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractBaseTest {
    private Book book1 = new Book();
    private Book book2 = new Book();
    private Book book3 = new Book();
    private Book book4 = new Book();
    private Book book5 = new Book();

    private Library library1 = new Library();
    private Library library2 = new Library();
    private Library library3 = new Library();
    private Library library4 = new Library();
    private Library library5 = new Library();

    private DatabaseManager databaseManager;
    {
        try {
            databaseManager = new DatabaseManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BookRepository bookRepository = new BookRepository(databaseManager.getConnection());
    LibraryRepository libraryRepository = new LibraryRepository(databaseManager.getConnection());
    BookLibraryRepository bookLibraryRepository = new BookLibraryRepository(databaseManager.getConnection());

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

    public void createManyBooks() throws Exception {
        book1.setName("Harry Potter and the Philosophers Stone");
        book1.setAuthor("J.K. Rowling");

        book2.setName("Harry Potter and the Chamber of Secrets");
        book2.setAuthor("J.K. Rowling");

        book3.setName("Harry Potter and the Prisoner of Azkaban");
        book3.setAuthor("J.K. Rowling");

        book4.setName("Harry Potter and the Goblet of Fire");
        book4.setAuthor("J.K. Rowling");

        book5.setName("Harry Potter and the Order of the Phoenix");
        book5.setAuthor("J.K. Rowling");

        try {
            bookRepository.add(book1);
            bookRepository.add(book2);
            bookRepository.add(book3);
            bookRepository.add(book4);
            bookRepository.add(book5);
        } catch (Exception e) {
            throw new Exception("It was not possible to create books.", e);
        }
    }

    public void createManyLibraries() throws Exception {
        library1.setName("NEW YORK PUBLIC LIBRARY");
        library1.setAddress("476 5th Ave, New York, NY 10018, United States");

        library2.setName("Shanghai Library");
        library2.setAddress("1555 Huaihai Road, Xuhui District, Shanghai, China");

        library3.setName("Library and Archives Canada");
        library3.setAddress("395 Wellington St, Ottawa, ON K1A 0N4, Canada");

        library4.setName("Russian State Library");
        library4.setAddress("Vozdvizhenka St., 3/5, Moscow, Russia, 119019");

        library5.setName("National Diet Library");
        library5.setAddress("1 Chome-10-1 Nagatachō, Chiyoda City, Tokyo 100-8924, Japan");

        try {
            libraryRepository.add(library1.getName(), library1.getAddress());
            libraryRepository.add(library2.getName(), library2.getAddress());
            libraryRepository.add(library3.getName(), library3.getAddress());
            libraryRepository.add(library4.getName(), library4.getAddress());
            libraryRepository.add(library5.getName(), library5.getAddress());
        } catch (Exception e){
            throw new Exception("It was not possible to create libraries.", e);
        }
    }

    public void emptyAllTables(Connection connection) throws SQLException {
        Statement statement = null;

        try {
            statement = connection.createStatement();
            String emptyBookTable = "DELETE FROM book";
            statement.executeUpdate(emptyBookTable);

            String emptyLibraryTable = "DELETE FROM book";
            statement.executeUpdate(emptyLibraryTable);

            String emptyBookLibraryTable = "DELETE FROM book_library";
            statement.executeUpdate(emptyBookLibraryTable);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            statement.close();
        }
    }

    public void addManyBooksToManyLibraries() throws Exception {
            createManyBooks();
            createManyLibraries();
        try {
            bookLibraryRepository.addBookToLibrary(1, 1, 150, 65);
            bookLibraryRepository.addBookToLibrary(2, 1, 250, 55);
        } catch (Exception e) {
            throw new Exception("It was not possible to add many books to the libraries.", e);
        }
    }

}
