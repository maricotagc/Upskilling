package mm.example.Block7;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class BookDbTest {

    @Test
    public void showAllBooksInShop() throws SQLException, ClassNotFoundException {
        ConnectionMysql con = new ConnectionMysql();
        Connection connection = con.createDBConnection();
        BookDb bookDb = new BookDb(connection);

        bookDb.showAllBooksInShop();
    }

    @Test
    public void showAllAvailableBooksInShop() throws SQLException, ClassNotFoundException {
        ConnectionMysql con = new ConnectionMysql();
        Connection connection = con.createDBConnection();
        BookDb bookDb = new BookDb(connection);

        bookDb.showAllAvailableBooksInShop();
    }

    @Test
    public void addBook() throws SQLException, ClassNotFoundException {
        Book book1 = new Book();
        book1.setId(99);
        book1.setName("Lusiadas");
        book1.setAuthor("Luis de Camões");
        ConnectionMysql con = new ConnectionMysql();
        Connection connection = con.createDBConnection();
        BookDb bookDb = new BookDb(connection);
        bookDb.addBook(book1);
    }

    @Test
    public void removeBook() throws SQLException, ClassNotFoundException {
        Book book1 = new Book();
        book1.setId(77);
        book1.setName("Tieta");
        book1.setAuthor("Jorge Amado");
        ConnectionMysql con = new ConnectionMysql();
        Connection connection = con.createDBConnection();
        BookDb bookDb = new BookDb(connection);
        bookDb.addBook(book1);
        bookDb.removeBookById(book1);
    }

    @Test
    public void setNumberOfAvailableBooks() throws SQLException, ClassNotFoundException {
        Book book1 = new Book();
        book1.setId(125);
        book1.setName("O Pequeno Príncipe");
        book1.setAuthor("Antoine de Saint-Exupéry");
        ConnectionMysql con = new ConnectionMysql();
        Connection connection = con.createDBConnection();
        BookDb bookDb = new BookDb(connection);
        bookDb.addBook(book1);
        bookDb.AddBookToLibrary(book1, 15, 5);
        bookDb.setNumberOfAvailableBookById(6, book1.getId());
    }
}