package mm.example.Block7.repository;

import mm.example.Block7.model.Book;
import mm.example.Block7.model.Library;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class LibraryManagerRepository {
    private static final String SQL_ADD_BOOK_TO_LIBRARY = "INSERT INTO book_library (book_id, library_id, total_copies, available_copies) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE_AVAILABLE_BOOKS = "UPDATE book_library SET available_copies = ? WHERE book_id = ? AND library_id = ?";

    private final Connection connection;

    public LibraryManagerRepository(Connection connection) {
        this.connection = connection;
    }

    public int addBookToLibrary(Book book, Library library, int totalCopies, int availableCopies) throws Exception {
        PreparedStatement preparedStatement = null;
        int result;
        try {
            preparedStatement = connection.prepareStatement(SQL_ADD_BOOK_TO_LIBRARY);
            preparedStatement.setInt(1, book.getId());
            preparedStatement.setInt(2, library.getId());
            preparedStatement.setInt(3, totalCopies);
            preparedStatement.setInt(4, availableCopies);
            result = preparedStatement.executeUpdate();
        } catch (Exception exception) {
            throw new Exception("It was not possible to add book " + book.getName() + " to the library " + library.getName(), exception);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return result;
    }

    public int updateAvailableBooks(Book book, Library library, int availableCopies) throws Exception {
        PreparedStatement preparedStatement = null;
        int result;
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_AVAILABLE_BOOKS);
            preparedStatement.setInt(1, availableCopies);
            preparedStatement.setInt(2, book.getId());
            preparedStatement.setInt(3, library.getId());
            result = preparedStatement.executeUpdate();
        } catch (Exception exception) {
            throw new Exception("It was not possible to update the number of available copies of " + book.getName() + " at the library " + library.getName(), exception);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return result;
    }
}
