package mm.example.Block7;

import java.sql.SQLException;

public class ListOfBooks {
    public BookDb bookdb;

    public void setAvailable_books(int available_books, Book bookId) throws SQLException {
        bookdb.setNumberOfAvailableBookById(available_books, bookId.getId());
    }

    public void showAllBooksInShop() throws SQLException {
        bookdb.showAllBooksInShop();
    }

    public void showAllAvailableBooksInShop() throws SQLException {
        bookdb.showAllAvailableBooksInShop();
    }

}
