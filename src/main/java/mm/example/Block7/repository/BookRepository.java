package mm.example.Block7.repository;

import mm.example.Block7.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private static final String SQL_INSERT = "INSERT INTO book (name, author) VALUES (?,?)";
    private static final String SQL_SELECT_ALL = "SELECT id, name, author FROM book";
    private static final String SQL_SELECT_BY_ID = "SELECT id, name, author FROM book WHERE id = ?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM book WHERE id = ?";
    private static final String SQL_SELECT_BY_BOOK_NAME = "SELECT id, name, author FROM book WHERE name like ?";

    private final Connection connection;

    public BookRepository(Connection connection) {
        this.connection = connection;
    }

    public int add(Book b) throws Exception {
        PreparedStatement preparedStatement = null;
        int result;
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, b.getName());
            preparedStatement.setString(2, b.getAuthor());
            result = preparedStatement.executeUpdate();
        } catch (Exception exception) {
            throw new Exception("It was not possible to add the book: " + b, exception);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return result;
    }

    public String remove(int bookId) throws Exception {
        PreparedStatement stmt = null;
        int result;
        try {
            stmt = connection.prepareStatement(SQL_DELETE_BY_ID);
            stmt.setInt(1, bookId);
            result = stmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception("It was not possible to remove the book with id = " + bookId + " from the database.", e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return "Book was successfully removed from book table.";
    }

    public List<Book> findAll() throws Exception {
        List<Book> books = new ArrayList<>();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Book book1 = new Book();
                book1.setId(rs.getInt("id"));
                book1.setName(rs.getString("name"));
                book1.setAuthor(rs.getString("author"));
                books.add(book1);
            }
        } catch (Exception e) {
            throw new Exception("It was not possible to find books", e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return books;
    }

    public Book findById(int bookId) throws Exception {
        PreparedStatement stmt = null;
        Book book1 = new Book();
        try {
            stmt = connection.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                book1.setId(rs.getInt("id"));
                book1.setName(rs.getString("name"));
                book1.setAuthor(rs.getString("author"));
            }
        } catch (Exception e) {
            throw new Exception("It was not possible to find book id = " + bookId, e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return book1;
    }

    public Book findByBookName(String bookName) throws Exception {
        PreparedStatement stmt = null;
        Book book1 = new Book();
        try {
            stmt = connection.prepareStatement(SQL_SELECT_BY_BOOK_NAME);
            stmt.setString(1, bookName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                book1.setId(rs.getInt("id"));
                book1.setName(rs.getString("name"));
                book1.setAuthor(rs.getString("author"));
            }
        } catch (Exception e) {
            throw new Exception("It was not possible to find book by name = " + bookName, e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return book1;
    }


}
