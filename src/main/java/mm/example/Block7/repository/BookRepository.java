//layer that connects the code to the repository (database) and has methods CRUD

package mm.example.Block7.repository;

import mm.example.Block7.exception.BookException;
import mm.example.Block7.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookRepository extends AbstractRepository {
    private static final String SQL_INSERT = "INSERT INTO book (name, author) VALUES (?,?)";
    private static final String SQL_SELECT_ALL = "SELECT id, name, author FROM book";
    private static final String SQL_SELECT_BY_ID = "SELECT id, name, author FROM book WHERE id = ?";
    private static final String SQL_COUNT_BY_ID = "SELECT COUNT(*) AS count FROM book WHERE id = ?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM book WHERE id = ?";
    private static final String SQL_SELECT_BY_BOOK_NAME = "SELECT id, name, author FROM book WHERE name = ?";

    public BookRepository(Connection connection) {
        super(connection);
    }

    public int create(Book b) throws BookException {
        PreparedStatement preparedStatement = null;
        int result;

        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, b.getName());
            preparedStatement.setString(2, b.getAuthor());
            result = preparedStatement.executeUpdate();
        } catch (Exception exception) {
            System.err.println(exception);
            throw new BookException("It was not possible to add the book: " + b);
        } finally {
            close(preparedStatement);
        }
        return result;
    }

    public int remove(int bookId) throws BookException {
        PreparedStatement preparedStatement = null;
        int result;

        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID);
            preparedStatement.setInt(1, bookId);
            result = preparedStatement.executeUpdate();
        } catch (Exception exception) {
            System.err.println(exception);
            throw new BookException("It was not possible to remove the book with id = " + bookId + " from the database.");
        } finally {
            close(preparedStatement);
        }
        return result;
    }

    public List<Book> findAll() throws BookException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Book> books = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book1 = new Book();
                book1.setId(resultSet.getInt("id"));
                book1.setName(resultSet.getString("name"));
                book1.setAuthor(resultSet.getString("author"));
                books.add(book1);
            }
        } catch (Exception e) {
            throw new BookException("It was not possible to find books");
        } finally {
            close(resultSet);
            close(preparedStatement);
        }
        return books;
    }

    public Book findById(int bookId) throws BookException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Book book1 = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
            preparedStatement.setInt(1, bookId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                book1 = new Book();
                book1.setId(resultSet.getInt("id"));
                book1.setName(resultSet.getString("name"));
                book1.setAuthor(resultSet.getString("author"));
            }
        } catch (Exception e) {
            throw new BookException("It was not possible to find book id = " + bookId);
        } finally {
            close(resultSet);
            close(preparedStatement);
        }
        return book1;
    }

    public boolean existsBook(int bookId) throws BookException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int result = 0;

        try {
            preparedStatement = connection.prepareStatement(SQL_COUNT_BY_ID);
            preparedStatement.setInt(1, bookId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt("count");
            }
        } catch (Exception e) {
            throw new BookException("It was not possible to find book id = " + bookId);
        } finally {
            close(resultSet);
            close(preparedStatement);
        }
        return result > 0;
    }

    public Book findByName(String bookName) throws BookException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Book book1 = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_BOOK_NAME);
            preparedStatement.setString(1, bookName);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                book1 = new Book();
                book1.setId(resultSet.getInt("id"));
                book1.setName(resultSet.getString("name"));
                book1.setAuthor(resultSet.getString("author"));
            }
        } catch (Exception e) {
            throw new BookException("It was not possible to find book by name = " + bookName);
        } finally {
            close(preparedStatement);
            close(resultSet);
        }
        return book1;
    }
}
