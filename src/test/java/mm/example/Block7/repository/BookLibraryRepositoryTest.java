package mm.example.Block7.repository;

import mm.example.Block7.AbstractBaseTest;
import mm.example.Block7.exception.BookException;
import mm.example.Block7.exception.LibraryException;
import mm.example.Block7.model.Book;
import mm.example.Block7.model.Library;
import mm.example.Block7.service.BookLibraryService;
import mm.example.Block7.utils.DatabaseManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BookLibraryRepositoryTest extends AbstractBaseTest {
    DatabaseManager databaseManager;

    @Before
    public void tearUp() throws Exception {
        databaseManager = new DatabaseManager();
        createTables(databaseManager.getConnection());
    }

    public void createTestData() throws Exception {
        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());
        Book book1;
        book1 = new Book();
        book1.setName("BOOK_A");
        book1.setAuthor("AUTHOR_A");

        Book book2;
        book2 = new Book();
        book2.setName("BOOK_B");
        book2.setAuthor("AUTHOR_B");

        try {
            bookRepository.create(book1);
            bookRepository.create(book2);
        } catch (Exception e) {
            throw new Exception("It was not possible to create books.", e);
        }

        LibraryRepository libraryRepository = new LibraryRepository(databaseManager.getConnection());
        Library library1 = new Library();
        library1.setName("LIBRARY_A");
        library1.setAddress("ADDRESS_A");

        Library library2 = new Library();
        library2.setName("LIBRARY_B");
        library2.setAddress("ADDRESS_B");

        try {
            libraryRepository.create(library1.getName(), library1.getAddress());
            libraryRepository.create(library2.getName(), library2.getAddress());
        } catch (Exception e) {
            throw new Exception("It was not possible to create library.", e);
        }

        BookLibraryRepository bookLibraryRepository = new BookLibraryRepository(databaseManager.getConnection());
        bookLibraryRepository.addBook(1, 1, 60, 22);
        bookLibraryRepository.addBook(1, 2, 160, 2);
        bookLibraryRepository.addBook(2, 1, 10, 9);
        bookLibraryRepository.addBook(2, 2, 10, 5);
    }

    public void removeTestData() {
        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());
        LibraryRepository libraryRepository = new LibraryRepository(databaseManager.getConnection());
        try {
            bookRepository.remove(1);
            bookRepository.remove(2);
        } catch (BookException e) {
            e.getMessage();
        }
        try {
            libraryRepository.remove(1);
            libraryRepository.remove(2);
        } catch (LibraryException e) {
            e.getMessage();
        }
    }

    @Test
    public void shouldAddBookToTheLibrary() throws Exception {
        BookLibraryRepository bookLibraryRepository = new BookLibraryRepository(databaseManager.getConnection());
        createTestData();
        Assert.assertEquals(1, bookLibraryRepository.addBook(1, 1, 60, 22));
        Assert.assertEquals(22, bookLibraryRepository.findAvailableCopies(1,1));
        removeTestData();
    }

    @Test
    public void shouldUpdateAvailableCopies() throws Exception {
        createTestData();
        BookLibraryRepository bookLibraryRepository = new BookLibraryRepository(databaseManager.getConnection());
        Assert.assertEquals(22, bookLibraryRepository.findAvailableCopies(1,1));
        Assert.assertEquals(1, bookLibraryRepository.updateAvailableCopies(5, 1, 1));
        Assert.assertEquals(5, bookLibraryRepository.findAvailableCopies(1, 1));
        removeTestData();
    }

    @Test
    public void shouldReturnListOfAvailableBooksInLibrary() throws Exception {
        BookLibraryRepository bookLibraryRepository = new BookLibraryRepository(databaseManager.getConnection());
        createTestData();

        Book book1 = new Book();
        book1.setId(1);
        book1.setAuthor("AUTHOR_A");
        book1.setName("BOOK_A");

        Book book2 = new Book();
        book2.setId(2);
        book2.setAuthor("AUTHOR_B");
        book2.setName("BOOK_B");

        List<Book> er = new ArrayList<>();
        er.add(book1);
        er.add(book2);

        Assert.assertEquals(er, bookLibraryRepository.findAllAvailableBooks(1));
        removeTestData();
    }

    @Test
    public void shouldReturnAvailableCopies() throws Exception {
        createTestData();
        BookLibraryRepository bookLibraryRepository = new BookLibraryRepository(databaseManager.getConnection());
        Assert.assertEquals(22, bookLibraryRepository.findAvailableCopies(1, 1));
        removeTestData();
    }

    @Test
    public void shouldReturnTotalRentedBooks() throws Exception {
        createTestData();
        BookLibraryRepository bookLibraryRepository = new BookLibraryRepository(databaseManager.getConnection());
        Assert.assertEquals(202, bookLibraryRepository.findTotalRentedBooks());
        removeTestData();
    }

    @Test
    public void shouldReturn0AvailableBooksAfterRentingAllBooks() throws Exception {
        createTestData();
        BookLibraryRepository bookLibraryRepository = new BookLibraryRepository(databaseManager.getConnection());
        Assert.assertEquals(1, bookLibraryRepository.updateAvailableCopies(0, 1, 1));
        Assert.assertEquals(1, bookLibraryRepository.updateAvailableCopies(0, 2, 1));
        Assert.assertEquals(1, bookLibraryRepository.updateAvailableCopies(0, 1, 2));
        Assert.assertEquals(1, bookLibraryRepository.updateAvailableCopies(0, 2, 2));
        Assert.assertEquals(0, bookLibraryRepository.findAllAvailableCopies());
        removeTestData();
    }

    @Test
    public void findAllBooksInLibrary() throws Exception {
        createTestData();
        BookLibraryRepository bookLibraryRepository = new BookLibraryRepository(databaseManager.getConnection());

        Book book1 = new Book();
        book1.setId(1);
        book1.setAuthor("AUTHOR_A");
        book1.setName("BOOK_A");

        Book book2 = new Book();
        book2.setId(2);
        book2.setAuthor("AUTHOR_B");
        book2.setName("BOOK_B");

        List<Book> er = new ArrayList<>();
        er.add(book1);
        er.add(book2);

        Assert.assertEquals(er, bookLibraryRepository.findAllBooksInLibrary(1));
        removeTestData();
    }

    @After
    public void tearDown() {
        databaseManager.closeConnection();
    }
}