package mm.example.Block7.repository;

import mm.example.Block7.AbstractBaseTest;
import mm.example.Block7.model.Book;
import mm.example.Block7.utils.DatabaseManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BookRepositoryTest extends AbstractBaseTest {

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
    public void shouldReturnIdNameAuthorOfAddedBook() throws Exception {
        DatabaseManager databaseManager = new DatabaseManager();
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
    public void shouldReturnNullAfterBookRemoval() throws Exception {
        addManyBooksToManyLibraries();
        DatabaseManager databaseManager = new DatabaseManager();
        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());

        try {
            Assert.assertEquals("Harry Potter and the Philosophers Stone", bookRepository.findByName("Harry Potter and the Philosophers Stone").getName());
            Assert.assertEquals("Book was successfully removed from book table.", bookRepository.remove(1));
            Assert.assertEquals(null, bookRepository.findByName("Harry Potter and the Philosophers Stone").getName());
        } finally {
            databaseManager.closeConnection();
        }
    }

    @Test
    public void shouldReturnListOfAllBooks() throws Exception {
        DatabaseManager databaseManager = new DatabaseManager();
        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());

        try {
            Book book1 = new Book();
            book1.setId(1);
            book1.setName("Harry Potter and the Philosophers Stone");
            book1.setAuthor("J.K. Rowling");
            bookRepository.create(book1);

            Book book2 = new Book();
            book2.setId(2);
            book2.setName("Harry Potter and the Chamber of Secrets");
            book2.setAuthor("J.K. Rowling");
            bookRepository.create(book2);

            List<Book> expected = new ArrayList<>();
            expected.add(book1);
            expected.add(book2);

            List<Book> actual = bookRepository.findAll();
            assertThat(expected).hasSameElementsAs(actual);

        } finally {
            databaseManager.closeConnection();
        }
    }

    @Test
    public void findBookById() throws Exception {
        DatabaseManager databaseManager = new DatabaseManager();
        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());

        try {
            Book book1 = new Book();
            book1.setId(1);
            book1.setName("Harry Potter and the Philosophers Stone");
            book1.setAuthor("J.K. Rowling");
            bookRepository.create(book1);
            Assert.assertEquals(true, bookRepository.findBookById(1));
        } finally {
            databaseManager.closeConnection();
        }
    }

    @Test
    public void findBookByName() throws Exception {
        DatabaseManager databaseManager = new DatabaseManager();
        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());

        try {
            Book book1 = new Book();
            book1.setId(1);
            book1.setName("Harry Potter and the Philosophers Stone");
            book1.setAuthor("J.K. Rowling");
            bookRepository.create(book1);

            Book book = bookRepository.findByName("Harry Potter and the Philosophers Stone");
            Assert.assertEquals("Harry Potter and the Philosophers Stone", book.getName());
        } finally {
            databaseManager.closeConnection();
        }
    }

}