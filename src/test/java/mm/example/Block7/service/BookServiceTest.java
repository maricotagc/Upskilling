package mm.example.Block7.service;

import mm.example.Block7.AbstractBaseTest;
import mm.example.Block7.model.Book;
import mm.example.Block7.repository.BookRepository;
import mm.example.Block7.utils.DatabaseManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class BookServiceTest extends AbstractBaseTest {

    DatabaseManager databaseManager;

    @Mock
    BookRepository bookRepositoryMock;

    @BeforeAll
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        databaseManager = new DatabaseManager();
        createTables(databaseManager.getConnection());
    }

    @Test
    public void shouldCreateBook() throws Exception {
        // given
        BookService bookService = new BookService(bookRepositoryMock);
        Book book1 = new Book();
        book1.setName("BOOK_NAME_A");
        book1.setAuthor("BOOK_AUTHOR_A");

        when(bookRepositoryMock.create(book1)).thenReturn(1);
        when(bookRepositoryMock.findByName(book1.getName())).thenReturn(null);

        // when
        boolean result = bookService.createBook(book1);

        // then
        assertTrue(result);
    }

    @Test
    public void shouldRemoveBook() throws Exception {
        BookService bookService = new BookService(bookRepositoryMock);

        // given
        Book book1 = new Book();
        book1.setId(1);
        book1.setName("BOOK_NAME_A");
        book1.setAuthor("BOOK_AUTHOR_A");

        when(bookRepositoryMock.existsBook(1)).thenReturn(true);
        when(bookRepositoryMock.remove(book1.getId())).thenReturn(1);

        // when
        boolean result = bookService.removeBook(book1);

        // then
        assertTrue(result);
    }

    @Test
    public void shouldRemoveBookById() throws Exception {
        //given
        BookService bookService = new BookService(bookRepositoryMock);
        Book book1 = new Book();
        book1.setId(1);
        book1.setName("BOOK_NAME_A");
        book1.setAuthor("BOOK_AUTHOR_A");

        when(bookRepositoryMock.existsBook(1)).thenReturn(true);
        when(bookRepositoryMock.remove(book1.getId())).thenReturn(1);

        // when
        bookService.removeBook(book1.getId());

        // then
        bookService.removeBook(book1.getId());
    }

    @Test
    public void shouldFindBookById() throws Exception {
        // given
        BookService bookService = new BookService(bookRepositoryMock);
        Book book1 = new Book();
        book1.setId(1);
        book1.setName("BOOK_NAME_A");
        book1.setAuthor("BOOK_AUTHOR_A");

        when(bookRepositoryMock.findById(1)).thenReturn(book1);

        // when
        Book actual = bookService.findById(1);

        // then
        assertEquals(book1, actual);

    }

    @Test
    public void shouldFindBookByName() throws Exception {
        // given
        BookService bookService = new BookService(bookRepositoryMock);
        Book expectedBook = new Book();
        expectedBook.setName("BOOK_NAME_A");
        expectedBook.setAuthor("BOOK_AUTHOR_A");

        when(bookRepositoryMock.findByName(expectedBook.getName())).thenReturn(expectedBook);

        // when
        Book actualBook = bookService.findByName(expectedBook.getName());

        // then
        assertEquals(expectedBook, actualBook);
    }

    @AfterAll
    public void tearDown() {
        databaseManager.closeConnection();
    }
}