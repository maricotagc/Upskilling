package mm.example.Block7.repository;

import mm.example.Block7.model.Book;
import mm.example.Block7.model.Library;
import mm.example.Block7.model.LibraryManager;
import mm.example.Block7.utils.DatabaseManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LibraryManagerRepositoryTest extends AbstractBaseTest{
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
    public void addBookToLibrary() throws Exception {
        Book book1 = new Book();
        book1.setId(1);
        book1.setName("Harry Potter and the Philosophers Stone");
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
            libraryRepository.add(library1.getName(), library1.getAddress());
            int result = libraryManagerRepository.addBookToLibrary(book1, library1, 60, 22);
            Assert.assertEquals(1, result);
        } finally {
            databaseManager.closeConnection();
        }
    }

    @Test
    public void updateAvailableBooks() throws Exception {
        Book book1 = new Book();
        book1.setId(1);
        book1.setName("Harry Potter and the Philosophers Stone");book1.setAuthor("'J.K. Rowling");

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
            libraryRepository.add(library1.getName(), library1.getAddress());
            libraryManagerRepository.addBookToLibrary(book1, library1, 99, 99);
            int result = libraryManagerRepository.updateAvailableBooksById(1, library1, 3);
            Assert.assertEquals(1, result);
        } finally {
            databaseManager.closeConnection();
        }
    }

    @Test
    public void showAllAvailableBooks() throws Exception {

        List<LibraryManager> libraryManagerList = new ArrayList<>();

        createManyBooks();
        createManyLibraries();
//        addManyBooksToManyLibraries();
//
//        DatabaseManager databaseManager = new DatabaseManager();
//        LibraryManagerRepository libraryManagerRepository = new LibraryManagerRepository(databaseManager.getConnection());
//
//        try {
//            libraryManagerList = libraryManagerRepository.showAllAvailableBooks();
//
//        } finally {
//            databaseManager.closeConnection();
//        }
    }

    @Test
    public void showAvailableBooksById() throws Exception {
        Book book1 = new Book();
        book1.setId(1);
        book1.setName("Harry Potter and the Philosophers Stone");
        book1.setAuthor("'J.K. Rowling");

        Library library1 = new Library();
        library1.setId(1);
        library1.setName("Shanghai Library");
        library1.setAddress("1555 Huaihai Road, Xuhui District, Shanghai, China");

        DatabaseManager databaseManager = new DatabaseManager();

        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());
        LibraryRepository libraryRepository = new LibraryRepository(databaseManager.getConnection());
        LibraryManagerRepository libraryManagerRepository = new LibraryManagerRepository(databaseManager.getConnection());

        try {
            bookRepository.add(book1);
            libraryRepository.add(library1.getName(), library1.getAddress());
            libraryManagerRepository.addBookToLibrary(book1, library1, 60, 22);
            int result = libraryManagerRepository.showAvailableBooksById(1, 1);
            Assert.assertEquals(22, result);
        } finally {
            databaseManager.closeConnection();
        }
    }

    @Test
    public void rentBookById() {

    }

}