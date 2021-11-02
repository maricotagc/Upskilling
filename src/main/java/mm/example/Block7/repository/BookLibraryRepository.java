//layer that connects the code to the repository (database) and has methods CRUD
package mm.example.Block7.repository;

import mm.example.Block7.exception.BookLibraryRepositoryException;
import mm.example.Block7.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookLibraryRepository {

    private static final String SQL_INSERT_BOOK_INTO_LIBRARY =
            "INSERT INTO book_library (book_id, library_id, total_copies, available_copies) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE_AVAILABLE_BOOKS =
            "UPDATE book_library SET available_copies = ? WHERE book_id = ? AND library_id = ?";
    private static final String SQL_COUNT_AVAILABLE_BOOKS =
            "SELECT SUM(available_copies) AS available_copies FROM book_library";
    private static final String SQL_FIND_NUMBER_AVAILABLE_BOOKS =
            "SELECT available_copies FROM book_library WHERE book_id = ? AND library_id = ?";
    private static final String SQL_ALL_RENTED_BOOKS =
            "SELECT SUM(total_copies-available_copies) AS rented_copies FROM book_library";
    private static final String SQL_SELECT_ALL_AVAILABLE_BOOKS_IN_LIBRARY =
            "SELECT distinct book.id, book.name, book.author FROM book JOIN book_library " +
                    "ON book.id = book_library.book_id JOIN library ON library.id = book_library.library_id " +
                    "WHERE book_library.available_copies > 0 AND library_id = ?";
    private static final String SQL_SELECT_ALL_BOOKS_IN_LIBRARY =
            "SELECT distinct book.id, book.name, book.author FROM book JOIN book_library " +
                    "ON book.id = book_library.book_id JOIN library ON library.id = book_library.library_id " +
                    "WHERE library_id = ?";

    private final Connection connection;

    public BookLibraryRepository(Connection connection) {
        this.connection = connection;
    }

    private void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (Exception e) {
            //TODO add log
        }
    }

    private void close(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (Exception e) {
            //TODO add log
        }
    }

    public int addBook(int bookId, int libraryId, int totalCopies, int availableCopies) throws BookLibraryRepositoryException {
        PreparedStatement preparedStatement = null;
        int result;
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT_BOOK_INTO_LIBRARY);
            preparedStatement.setInt(1, bookId);
            preparedStatement.setInt(2, libraryId);
            preparedStatement.setInt(3, totalCopies);
            preparedStatement.setInt(4, availableCopies);
            result = preparedStatement.executeUpdate();
        } catch (Exception exception) {
            throw new BookLibraryRepositoryException("It was not possible to add book id " + bookId + " to the library id " + libraryId);
        } finally {
            close(preparedStatement);
        }
        return result;
    }

    public int updateAvailableCopies(int bookId, int libraryId, int availableCopies) throws BookLibraryRepositoryException {
        PreparedStatement preparedStatement = null;
        int result;

        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_AVAILABLE_BOOKS);
            preparedStatement.setInt(1, availableCopies);
            preparedStatement.setInt(2, bookId);
            preparedStatement.setInt(3, libraryId);
            result = preparedStatement.executeUpdate();
        } catch (Exception exception) {
            // log exception
            throw new BookLibraryRepositoryException("It was not possible to update the number of available copies.");
        } finally {
            close(preparedStatement);
        }
        return result;
    }

    public List<Book> findAllAvailableBooks(int libraryId) throws BookLibraryRepositoryException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Book> availableBooks = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_AVAILABLE_BOOKS_IN_LIBRARY);
            preparedStatement.setInt(1, libraryId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setAuthor(resultSet.getString("author"));
                availableBooks.add(book);
            }
        } catch (Exception e) {
            // log exception
            throw new BookLibraryRepositoryException("It was not possible to retrieve all available books from the database");
        } finally {
            close(resultSet);
            close(preparedStatement);
        }
        return availableBooks;
    }

    public int findAllAvailableCopies() throws BookLibraryRepositoryException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int availableBooks = 0;

        try {
            preparedStatement = connection.prepareStatement(SQL_COUNT_AVAILABLE_BOOKS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                availableBooks = resultSet.getInt("available_copies");
            }
        } catch (Exception e) {
            throw new BookLibraryRepositoryException("It was not possible to retrieve the number of all available books from the database");
        } finally {
            close(resultSet);
            close(preparedStatement);
        }
        return availableBooks;
    }

    public int findAvailableCopies(int bookId, int libraryId) throws BookLibraryRepositoryException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int availableCopies = 0;

        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_NUMBER_AVAILABLE_BOOKS);
            preparedStatement.setInt(1, bookId);
            preparedStatement.setInt(2, libraryId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                availableCopies = resultSet.getInt("available_copies");
            }
        } catch (Exception e) {
            // log exception
            throw new BookLibraryRepositoryException("It was not possible to check how many available books exist in the database.");
        } finally {
            close(preparedStatement);
        }
        return availableCopies;
    }

    public int findTotalRentedBooks() throws BookLibraryRepositoryException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int rentedBooks = 0;

        try {
            preparedStatement = connection.prepareStatement(SQL_ALL_RENTED_BOOKS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                rentedBooks = resultSet.getInt("rented_copies");
            }
        } catch (Exception e) {
            throw new BookLibraryRepositoryException("It was not possible to retrieve number of rented books");
        } finally {
            close(resultSet);
            close(preparedStatement);
        }
        return rentedBooks;
    }

    public List<Book> findAllBooksInLibrary(int libraryId) throws BookLibraryRepositoryException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Book> availableBooks = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_BOOKS_IN_LIBRARY);
            preparedStatement.setInt(1, libraryId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setAuthor(resultSet.getString("author"));
                availableBooks.add(book);
            }
        } catch (Exception e) {
            // log exception
            throw new BookLibraryRepositoryException("It was not possible to retrieve all books in this library from the database");
        } finally {
            close(resultSet);
            close(preparedStatement);
        }
        return availableBooks;
    }
}