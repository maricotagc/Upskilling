package mm.example.Block7.service;

import mm.example.Block7.AbstractBaseTest;
import mm.example.Block7.exception.BookException;
import mm.example.Block7.exception.LibraryException;
import mm.example.Block7.model.Book;
import mm.example.Block7.model.Library;
import mm.example.Block7.repository.BookLibraryRepository;
import mm.example.Block7.repository.BookRepository;
import mm.example.Block7.repository.LibraryRepository;
import mm.example.Block7.utils.DatabaseManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookLibraryServiceTest extends AbstractBaseTest {

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
    public void shouldReturnAvailableCopiesAfterRentingBook() throws Exception {
        // given
        BookLibraryRepository bookLibraryRepository = new BookLibraryRepository(databaseManager.getConnection());
        BookLibraryService bookLibraryService = new BookLibraryService(bookLibraryRepository);
        createTestData();

        // when
        bookLibraryService.rentBook(1, 1);

        // then
        assertEquals(21, bookLibraryRepository.findAvailableCopies(1, 1));
        removeTestData();
    }

    @Test
    public void shouldReturnSuccessfulMessageAfterBookRefund() throws Exception {
        // given
        createTestData();
        BookLibraryRepository bookLibraryRepository = new BookLibraryRepository(databaseManager.getConnection());
        BookLibraryService bookLibraryService = new BookLibraryService(bookLibraryRepository);

        // when
        int actualResult = bookLibraryService.returnBook(1, 1);

        // then
        assertEquals(1, actualResult);
        assertEquals(23, bookLibraryRepository.findAvailableCopies(1, 1));
    }

    @AfterAll
    public static void tearDown() {
        databaseManager.closeConnection();
    }
}