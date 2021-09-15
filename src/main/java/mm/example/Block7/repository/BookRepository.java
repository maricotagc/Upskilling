package mm.example.Block7.repository;

import mm.example.Block7.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private static final String SQL_INSERT = "INSERT INTO book (id, name, author) VALUES (?,?,?)";
    private static final String SQL_SELECT_ALL = "SELECT id, name, author FROM book";
    private static final String SQL_SELECT_BY_ID = "SELECT id, name, author FROM book WHERE id = ?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM book WHERE id = ?";
    private static final String SQL_DELETE_ALL = "DELETE FROM book";

    private final Connection connection;

    public BookRepository(Connection connection) {
        this.connection = connection;
    }

    public int add(Book b) throws Exception {
        PreparedStatement preparedStatement = null;
        int result;
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setInt(1, b.getId());
            preparedStatement.setString(2, b.getName());
            preparedStatement.setString(3, b.getAuthor());
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

    public int remove(int bookId) throws Exception {
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
        return result;
    }

    public int deleteAll() throws Exception {
        PreparedStatement stmt = null;
        int result;
        try {
            stmt = connection.prepareStatement(SQL_DELETE_ALL);
            result = stmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception("It was not possible to remove all books from the database.", e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return result;
    }

    public List<Book> findAll() throws Exception {
        List<Book> books = new ArrayList<>();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = stmt.executeQuery();
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
            if (stmt != null) {
                stmt.close();
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
}
