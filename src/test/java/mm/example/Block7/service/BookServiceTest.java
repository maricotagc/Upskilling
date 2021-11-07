package mm.example.Block7.service;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import mm.example.Block7.AbstractBaseTest;
import mm.example.Block7.model.Book;
import mm.example.Block7.repository.BookRepository;
import mm.example.Block7.utils.DatabaseManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(DataProviderRunner.class)
public class BookServiceTest extends AbstractBaseTest {

    DatabaseManager databaseManager;

    @Mock
    BookRepository bookRepositoryMock;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        databaseManager = new DatabaseManager();
        createTables(databaseManager.getConnection());
    }


    @DataProvider
    public static Object[][] bookData() {
        return new Object[][]{
                {"BOOK_D", "AUTHOR_D"},
                {"BOOK_E", "AUTHOR_E"},
                {"BOOK_F", "AUTHOR_F"}
        };
    }

    @DataProvider
    public static Object[][] booksIds() {
        return new Object[][]{
                {1},
                {2},
                {3}
        };
    }

    @DataProvider
    public static Object[][] bookNames() {
        return new Object[][]{
                {"Harry Potter and the Philosophers Stones"},
                {"Harry Potter and the Chamber of Secrets"},
                {"Harry Potter and the Prisoner of Azkaban"}
        };
    }

    @Test
    @UseDataProvider("bookData")
    public void createBook(String bookName, String bookAuthor) throws Exception {
        BookService bookService = new BookService(bookRepositoryMock);

        // given
        Book book1 = new Book();
        book1.setName(bookName);
        book1.setAuthor(bookAuthor);
        when(bookRepositoryMock.create(book1)).thenReturn(1);

        // when
        boolean result = bookService.createBook(book1);

        // then
        Assert.assertTrue(result);
    }

    @Test
    public void removeBook() throws Exception {
        DatabaseManager databaseManager = new DatabaseManager();
        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());
        BookService bookService = new BookService(bookRepository);

        Book book = new Book();
        book.setId(1);
        book.setName("Harry Potter and the Philosophers Stone");
        book.setAuthor("J.K. Rowling");

        try {
            Assert.assertEquals("Harry Potter and the Philosophers Stone", bookRepository.findByName("Harry Potter and the Philosophers Stone").getName());
            bookService.removeBook(book);
            Assert.assertEquals(null, bookRepository.findByName("Harry Potter and the Philosophers Stone").getName());
        } finally {
            databaseManager.closeConnection();
        }
    }

    @Test
    public void removeBookById() throws Exception {
        DatabaseManager databaseManager = new DatabaseManager();
        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());
        BookService bookService = new BookService(bookRepository);

        Book book = new Book();
        book.setId(1);
        book.setName("Harry Potter and the Philosophers Stone");
        book.setAuthor("J.K. Rowling");

        try {
            Assert.assertEquals("Harry Potter and the Philosophers Stone", bookRepository.findByName("Harry Potter and the Philosophers Stone").getName());
            bookService.removeBook(1);
            Assert.assertEquals(null, bookRepository.findByName("Harry Potter and the Philosophers Stone").getName());
        } finally {
            databaseManager.closeConnection();
        }
    }

    @Test
    @UseDataProvider("booksIds")
    public void findBookById(int bookId) throws Exception {
        DatabaseManager databaseManager = new DatabaseManager();
        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());
        BookService bookService = new BookService(bookRepository);

        Book expectedBook = new Book();
        expectedBook.setId(bookId);

        try {
            Assert.assertEquals(expectedBook.getId(), bookService.findById(bookId).getId());
        } finally {
            databaseManager.closeConnection();
        }
    }

    @Test
    @UseDataProvider("bookNames")
    public void findBookByName(String bookName) throws Exception {


        DatabaseManager databaseManager = new DatabaseManager();
        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());
        BookService bookService = new BookService(bookRepository);

        Book expectedBook = new Book();
        expectedBook.setName(bookName);

        try {
            Assert.assertEquals(expectedBook.getName(), bookService.findByName(bookName).getName());
        } finally {
            databaseManager.closeConnection();
        }
    }

    @After
    public void tearDown() {
        databaseManager.closeConnection();
    }
}