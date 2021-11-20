package mm.example.Block7.repository;

import mm.example.Block7.AbstractBaseTest;
import mm.example.Block7.exception.BookException;
import mm.example.Block7.model.Book;
import mm.example.Block7.utils.DatabaseManager;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class BookRepositoryTest extends AbstractBaseTest {

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
    }

    public void removeTestData() {
        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());
        try {
            bookRepository.remove(1);
            bookRepository.remove(2);
        } catch (BookException e) {
            e.getMessage();
        }
    }

    @Test
    public void shouldCreateBook() throws Exception {
        // given
        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());

        Book book1 = new Book();
        book1.setName("Harry Potter and the Philosophers Stone");
        book1.setAuthor("J.K. Rowling");

        //when
        try {
            bookRepository.create(book1);

        // then
            Book actualBook = bookRepository.findByName("Harry Potter and the Philosophers Stone");
            assertEquals("Harry Potter and the Philosophers Stone", actualBook.getName());
            assertEquals("J.K. Rowling", actualBook.getAuthor());
            assertEquals(1, actualBook.getId());
        } finally {
            databaseManager.closeConnection();
        }
    }

    @Test
    public void shouldRemoveBook() throws Exception {
        // given
        createTestData();
        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());

        // when
        try {
            int actualResult = bookRepository.remove(1);

        // then
            assertEquals(1, actualResult);
            assertEquals(null, bookRepository.findById(1));
        } finally {
            removeTestData();
            databaseManager.closeConnection();
        }
    }

    @Test
    public void shouldFindAllBooks() throws Exception {
        // given
        createTestData();
        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());

        try {
            Book book1 = new Book();
            book1.setId(1);
            book1.setName("BOOK_A");
            book1.setAuthor("AUTHOR_A");

            Book book2 = new Book();
            book2.setId(2);
            book2.setName("BOOK_B");
            book2.setAuthor("AUTHOR_B");

            List<Book> expectedResult = new ArrayList<>();
            expectedResult.add(book1);
            expectedResult.add(book2);

        // when

            List<Book> actual = bookRepository.findAll();

        // then
            assertThat(expectedResult).hasSameElementsAs(actual);

        } finally {
            removeTestData();
        }
    }

    @Test
    public void shouldFindBookById() throws Exception {
        // given
        createTestData();
        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());

        // when
        boolean actualResult1 = bookRepository.existsBook(1);
        boolean actualResult2 = bookRepository.existsBook(2);
        boolean actualResult3 = bookRepository.existsBook(3);

        // then
        assertTrue(actualResult1);
        assertTrue(actualResult2);
        assertFalse(actualResult3);
    }

    @Test
    public void shouldFindBookByName() throws Exception {
        // given
        createTestData();
        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());

        // when
        String actualResult = bookRepository.findByName("BOOK_A").getName();

        // then
        assertEquals("BOOK_A", actualResult);
        removeTestData();
    }

    @AfterAll
    public static void tearDown() {
        databaseManager.closeConnection();
    }
}