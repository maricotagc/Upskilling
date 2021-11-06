package mm.example.Block7.repository;

import mm.example.Block7.AbstractBaseTest;
import mm.example.Block7.exception.BookException;
import mm.example.Block7.model.Book;
import mm.example.Block7.utils.DatabaseManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BookRepositoryTest extends AbstractBaseTest {

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
        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());

        Book book1 = new Book();
        book1.setName("Harry Potter and the Philosophers Stone");
        book1.setAuthor("J.K. Rowling");

        try {
            bookRepository.create(book1);
            Book actualBook = bookRepository.findByName("Harry Potter and the Philosophers Stone");

            Assert.assertEquals("Harry Potter and the Philosophers Stone", actualBook.getName());
            Assert.assertEquals("J.K. Rowling", actualBook.getAuthor());
            Assert.assertEquals(1, actualBook.getId());
        } finally {
            databaseManager.closeConnection();
        }
    }

    @Test
    public void shouldRemoveBook() throws Exception {
        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());
        createTestData();
        try {
            Assert.assertEquals("BOOK_A", bookRepository.findByName("BOOK_A").getName());
            Assert.assertEquals(1, bookRepository.remove(1));
            Assert.assertEquals(null, bookRepository.findByName("BOOK_A").getName());
        } finally {
            removeTestData();
            databaseManager.closeConnection();
        }
    }

    @Test
    public void shouldFindAllBooks() throws Exception {
        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());
        createTestData();

        try {
            Book book1 = new Book();
            book1.setId(1);
            book1.setName("BOOK_A");
            book1.setAuthor("AUTHOR_A");

            Book book2 = new Book();
            book2.setId(2);
            book2.setName("BOOK_B");
            book2.setAuthor("AUTHOR_B");

            List<Book> expected = new ArrayList<>();
            expected.add(book1);
            expected.add(book2);

            List<Book> actual = bookRepository.findAll();
            assertThat(expected).hasSameElementsAs(actual);

        } finally {
            removeTestData();
        }
    }

    @Test
    public void shouldFindBookById() throws Exception {
        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());
        createTestData();
        Assert.assertTrue(bookRepository.existsBook(1));
        Assert.assertTrue(bookRepository.existsBook(2));
        Assert.assertFalse(bookRepository.existsBook(3));
    }

    @Test
    public void shouldFindBookByName() throws Exception {
        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());
        createTestData();
        Assert.assertEquals("BOOK_A", bookRepository.findByName("BOOK_A").getName());
        removeTestData();
    }

    @After
    public void tearDown() {
        databaseManager.closeConnection();
    }
}