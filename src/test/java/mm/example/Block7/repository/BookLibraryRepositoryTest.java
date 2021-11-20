package mm.example.Block7.repository;

import mm.example.Block7.AbstractBaseTest;
import mm.example.Block7.exception.BookException;
import mm.example.Block7.exception.LibraryException;
import mm.example.Block7.model.Book;
import mm.example.Block7.model.Library;
import mm.example.Block7.utils.DatabaseManager;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookLibraryRepositoryTest extends AbstractBaseTest {

    static DatabaseManager databaseManager;

    @BeforeEach
    public void setUp() throws Exception {
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
        // given
        createTestData();

        //when
        BookLibraryRepository bookLibraryRepository = new BookLibraryRepository(databaseManager.getConnection());

        //then
        assertEquals(1, bookLibraryRepository.addBook(1, 1, 60, 22));
        assertEquals(22, bookLibraryRepository.findAvailableCopies(1,1));
        removeTestData();
    }

    @Test
    public void shouldUpdateAvailableCopies() throws Exception {
        // given
        createTestData();
        BookLibraryRepository bookLibraryRepository = new BookLibraryRepository(databaseManager.getConnection());

        //when
        bookLibraryRepository.updateAvailableCopies(5, 1, 1);

        //then
        assertEquals(5, bookLibraryRepository.findAvailableCopies(1, 1));
        removeTestData();
    }

    @Test
    public void shouldReturnListOfAvailableBooksInLibrary() throws Exception {
        // given
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

        List<Book> expectedResult = new ArrayList<>();
        expectedResult.add(book1);
        expectedResult.add(book2);

        // when
        List<Book> actualResult = bookLibraryRepository.findAllAvailableBooks(1);

        // then
        assertEquals(expectedResult, actualResult);
        removeTestData();
    }

    @Test
    public void shouldReturnAvailableCopies() throws Exception {
        // given
        createTestData();
        BookLibraryRepository bookLibraryRepository = new BookLibraryRepository(databaseManager.getConnection());

        // when
        int actualResult = bookLibraryRepository.findAvailableCopies(1, 1);

        // then
        assertEquals(22, actualResult);
        removeTestData();
    }

    @Test
    public void shouldReturnTotalRentedBooks() throws Exception {
        // given
        createTestData();
        BookLibraryRepository bookLibraryRepository = new BookLibraryRepository(databaseManager.getConnection());

        // when
        int actualResult = bookLibraryRepository.findTotalRentedBooks();

        //then
        assertEquals(202, actualResult);
        removeTestData();
    }

    @Test
    public void shouldReturn0AvailableBooksAfterRentingAllBooks() throws Exception {
        // given
        createTestData();
        BookLibraryRepository bookLibraryRepository = new BookLibraryRepository(databaseManager.getConnection());

        // when
        bookLibraryRepository.updateAvailableCopies(0, 1, 1);
        bookLibraryRepository.updateAvailableCopies(0, 2, 1);
        bookLibraryRepository.updateAvailableCopies(0, 1, 2);
        bookLibraryRepository.updateAvailableCopies(0, 2, 2);

        // then
        assertEquals(0, bookLibraryRepository.findAllAvailableCopies());
        removeTestData();
    }

    @Test
    public void findAllBooksInLibrary() throws Exception {
        // given
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

        List<Book> expectedResult = new ArrayList<>();
        expectedResult.add(book1);
        expectedResult.add(book2);

        // when
        List<Book> actualResult = bookLibraryRepository.findAllBooksInLibrary(1);

        // then
        assertEquals(expectedResult, actualResult);
        removeTestData();
    }

    @AfterAll
    public static void tearDown() {
        databaseManager.closeConnection();
    }
}