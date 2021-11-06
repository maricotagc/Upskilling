package mm.example.Block7.service;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import mm.example.Block7.AbstractBaseTest;
import mm.example.Block7.model.Book;
import mm.example.Block7.repository.BookRepository;
import mm.example.Block7.utils.DatabaseManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class BookServiceTest extends AbstractBaseTest {

    @Before
    public void setUp() throws Exception {
        DatabaseManager databaseManager = new DatabaseManager();
        try {
            createTables(databaseManager.getConnection());
        } finally {
            databaseManager.closeConnection();
        }
    }

    @DataProvider
    public static Object[][] bookData() {
        return new Object[][]{
                {"Harry Potter and the Philosophers Stone", "J.K. Rowling"},
                {"Harry Potter # 2", "J.K. Rowling"},
                {"Harry Potter # 3", "J.K. Rowling"}
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
        DatabaseManager databaseManager = new DatabaseManager();
        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());
        BookService bookService = new BookService(bookRepository);

        Book book1 = new Book();
        book1.setName(bookName);
        book1.setAuthor(bookAuthor);

        try {
            bookService.createBook(book1);
            Book actualBook = bookRepository.findByName(bookName);

            Assert.assertEquals(bookName, actualBook.getName());
            Assert.assertEquals(bookAuthor, actualBook.getAuthor());
            Assert.assertEquals(1, actualBook.getId());

        } finally {
            databaseManager.closeConnection();
        }
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


}