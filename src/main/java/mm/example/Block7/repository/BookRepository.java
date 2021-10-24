package mm.example.Block7.repository;

import mm.example.Block7.exception.BookException;
import mm.example.Block7.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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

    public void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (Exception e) {
            //
        }
    }

    public int add(Book b) throws BookException {
        PreparedStatement preparedStatement = null;
        int result;
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, b.getName());
            preparedStatement.setString(2, b.getAuthor());
            result = preparedStatement.executeUpdate();
        } catch (Exception exception) {
            throw new BookException("It was not possible to add the book: " + b);
        } finally {
            close(preparedStatement);
        }
        return result;
    }

    public String remove(int bookId) throws Exception {
        PreparedStatement preparedStatement = null;
        int result;
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID);
            preparedStatement.setInt(1, bookId);
            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new Exception("It was not possible to remove the book with id = " + bookId + " from the database.", e);
        } finally {
            close(preparedStatement);
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
            close(preparedStatement);
        }
        return books;
    }

    public Book findById(int bookId) throws Exception {
        PreparedStatement preparedStatement = null;
        Book book1 = new Book();
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
            preparedStatement.setInt(1, bookId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                book1.setId(rs.getInt("id"));
                book1.setName(rs.getString("name"));
                book1.setAuthor(rs.getString("author"));
            }
        } catch (Exception e) {
            throw new Exception("It was not possible to find book id = " + bookId, e);
        } finally {
            close(preparedStatement);
        }
        return book1;
    }

    public Book findByBookName(String bookName) throws Exception           {
        PreparedStatement preparedStatement = null;
        Book book1 = new Book();
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_BOOK_NAME);
            preparedStatement.setString(1, bookName);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                book1.setId(rs.getInt("id"));
                book1.setName(rs.getString("name"));
                book1.setAuthor(rs.getString("author"));
            }
        } catch (Exception e) {
            throw new Exception("It was not possible to find book by name = " + bookName, e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return book1;
    }


}
