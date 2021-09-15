package mm.example.Block8.Repository;

import mm.example.Block7.Book;

import java.sql.*;

public class BookDB {
    private final Connection connection;
    private static final String SQL_INSERT = "INSERT INTO BOOK(name,author) " + "VALUES(?,?)";
    private static final String SQL_SELECT_ALL = "SELECT id, name, author FROM book";

    public BookDB(Connection connection) throws SQLException {
        this.connection = connection;
    }

    //TODO make it return list of books;
    public void viewAllBooks() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = stmt.executeQuery(SQL_SELECT_ALL);
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "  " + rs.getString("name") + " " + rs.getString("author"));
            }


        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

        public int addBook(Book book) throws SQLException {
        PreparedStatement stmt = null;
        int result = 0;
        try {
            stmt = connection.prepareStatement(SQL_INSERT);
            stmt.setString(1, book.getName());
            stmt.setString(2, book.getAuthor());
            result = stmt.executeUpdate();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return result;
    }
}