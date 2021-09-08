package mm.example.Block7;

import java.sql.*;

public class BookDb {
    private Connection connection;
    private Statement stmt;

    public BookDb(Connection connection) throws SQLException {
        this.connection = connection;
        stmt = connection.createStatement();
    }

    // TODO: how to commit?
    public void addBook(Book book) throws SQLException {
        PreparedStatement psrmt = connection.prepareStatement("INSERT INTO BOOK(ID,NAME,AUTHOR) " +
                "VALUES(?,?,?)");
        psrmt.setString(1, String.valueOf(book.getId()));
        psrmt.setString(2, book.getName());
        psrmt.setString(3, book.getAuthor());

        int affectedRows = psrmt.executeUpdate();
        if (affectedRows > 0) {
            System.out.println("Book successfully added to DB");
        }
    }

    public void removeBookById(Book book) throws SQLException {
        PreparedStatement psrmt = connection.prepareStatement("DELETE FROM BOOK WHERE ID = ?");
        psrmt.setString(1, String.valueOf(book.getId()));
        int affectedRows = psrmt.executeUpdate();
        if (affectedRows > 0) {
            System.out.println("Book successfully removed from DB");
        }
    }

    public void AddBookToLibrary(Book book, int copies, int availableBooks) throws SQLException {
        PreparedStatement psrmt = connection.prepareStatement("INSERT INTO LIST_OF_BOOKS VALUES (?,?,?);");
        psrmt.setString(1, String.valueOf(book.getId()));
        psrmt.setString(2, String.valueOf(copies));
        psrmt.setString(3, String.valueOf(availableBooks));
        int affectedRows = psrmt.executeUpdate();
        if (affectedRows > 0) {
            System.out.println("Book was successfully added to the library.");
        }
    }


    public void setNumberOfAvailableBookById(int availableBooks, int bookId) throws SQLException {
        PreparedStatement psrmt = connection.prepareStatement("UPDATE LIST_OF_BOOKS SET AVAILABLE_BOOKS = ? WHERE BOOK_ID= ?;");
        psrmt.setString(1, String.valueOf(availableBooks));
        psrmt.setString(2, String.valueOf(bookId));
        int affectedRows = psrmt.executeUpdate();
        if (affectedRows > 0) {
            System.out.println("Number of available books was successfully updated into the DB.");
        }
    }

    public void showAllAvailableBooksInShop() throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT LIST_OF_BOOKS.COPIES, BOOK.NAME FROM LIST_OF_BOOKS INNER JOIN BOOK ON LIST_OF_BOOKS.BOOK_ID = BOOK.ID;");
        while (rs.next()) {
            System.out.println(rs.getInt("COPIES") + "  " + rs.getString("NAME"));
        }
    }

    public void showAllBooksInShop() throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * FROM LIST_OF_BOOKS;");
        while (rs.next()) {
            System.out.println(rs.getInt("BOOK_ID") + "  " + rs.getInt("COPIES") + " " + rs.getInt("AVAILABLE_BOOKS"));
        }
    }
}