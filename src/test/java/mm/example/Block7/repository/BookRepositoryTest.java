package mm.example.Block7.repository;

import mm.example.Block7.model.Book;
import mm.example.Block7.utils.ConnectionManager;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BookRepositoryTest {

    @Test
    public void shouldReturn1ForBookSuccessfullyAdded() throws Exception {
        Book book1 = new Book();
        book1.setId(1);
        book1.setName("Harry Potter and the Philosophers Stone");
        book1.setAuthor("J.K. Rowlings");
        ConnectionManager connectionManager = new ConnectionManager();

        BookRepository bookRepository = new BookRepository(connectionManager.getConnection());

        try {
            bookRepository.deleteAll();
            int result = bookRepository.add(book1);
            Book book = bookRepository.findById(1);
            Assert.assertEquals(1, result);
            Assert.assertEquals("Harry Potter and the Philosophers Stone", book.getName());
            Assert.assertEquals("J.K. Rowlings", book.getAuthor());
            Assert.assertEquals(1, book.getId());
        } finally {
            connectionManager.closeConnection();
        }
    }

    @Test
    public void shouldReturn1ForBookSuccessfullyRemoved() throws Exception {
        ConnectionManager connectionManager = new ConnectionManager();
        BookRepository bookRepository = new BookRepository(connectionManager.getConnection());
        Book book1 = new Book();
        book1.setId(1);
        book1.setName("Harry Potter and the Philosophers Stone");
        book1.setAuthor("J.K. Rowling");

        try {
            bookRepository.deleteAll();
            bookRepository.add(book1);
            int result = bookRepository.remove(book1.getId());
            Assert.assertEquals(1, result);
        } finally {
            connectionManager.closeConnection();
        }
    }

    @Test
    public void viewAllBooks() throws Exception {
        BookRepository bookRepository = new BookRepository(new ConnectionManager().getConnection());
        bookRepository.deleteAll();

        Book book1 = new Book();
        book1.setId(1);
        book1.setName("Harry Potter and the Philosophers Stone");
        book1.setAuthor("J.K. Rowling");
        bookRepository.add(book1);

        Book book2 = new Book();
        book2.setId(2);
        book2.setName("Harry Potter and the Chamber of Secrets");
        book2.setAuthor("J.K. Rowling");
        bookRepository.add(book2);

        List<Book> expected = new ArrayList<>();
        expected.add(book1);
        expected.add(book2);

        List<Book> actual = bookRepository.findAll();
        assertThat(expected).hasSameElementsAs(actual);
        }

    @Test
    public void findBookById() throws Exception {
        BookRepository bookRepository = new BookRepository(new ConnectionManager().getConnection());
        bookRepository.deleteAll();

        Book book1 = new Book();
        book1.setId(6);
        book1.setName("Harry Potter and the Philosophers Stone");
        book1.setAuthor("J.K. Rowling");
        bookRepository.add(book1);

        Book book = bookRepository.findById(6);
        Assert.assertEquals(6, book.getId());
    }
}



