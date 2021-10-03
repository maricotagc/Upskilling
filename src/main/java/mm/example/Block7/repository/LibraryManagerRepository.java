package mm.example.Block7.repository;

import mm.example.Block7.model.Book;
import mm.example.Block7.model.Library;
import mm.example.Block7.model.LibraryManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LibraryManagerRepository {
    private static final String SQL_ADD_BOOK_TO_LIBRARY = "INSERT INTO book_library (book_id, library_id, total_copies, available_copies) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE_AVAILABLE_BOOKS = "UPDATE book_library SET available_copies = ? WHERE book_id = ? AND library_id = ?";
    private static final String SQL_SHOW_ALL_AVAILABLE_BOOKS = "SELECT book_id, library_id, available_copies FROM book_library WHERE available_copies > 0;";
    private static final String SQL_SHOW_AVAILABLE_BOOKS_BY_IDS = "SELECT available_copies FROM book_library where book_id = ? AND library_id = ?;";
    private static final String SQL_REMOVE_ONE_AVAILABLE_BOOK_BY_ID = "UPDATE book_library SET available_copies = ? - 1 WHERE book_id = ? AND library_id = ?";
    private static final String SQL_ADD_ONE_AVAILABLE_BOOK_BY_ID = "UPDATE book_library SET available_copies = ? + 1 WHERE book_id = ? AND library_id = ?";

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

    public int updateAvailableBooksById(int bookId, Library library, int availableCopies) throws Exception {
        PreparedStatement preparedStatement = null;
        int result;

        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_AVAILABLE_BOOKS);
            preparedStatement.setInt(1, availableCopies);
            preparedStatement.setInt(2, bookId);
            preparedStatement.setInt(3, library.getId());
            result = preparedStatement.executeUpdate();
        } catch (Exception exception) {
            throw new Exception("It was not possible to update the number of available copies of bbok with ID = " + bookId + " at the library " + library.getName(), exception);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return result;
    }

    public List<LibraryManager> showAllAvailableBooks() throws Exception {
        PreparedStatement preparedStatement = null;
        List<LibraryManager> libraryManagerList = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_SHOW_ALL_AVAILABLE_BOOKS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                LibraryManager libraryManager = new LibraryManager();
                libraryManager.setBookId(resultSet.getInt("book_id"));
                libraryManager.setLibraryId(resultSet.getInt("library_id"));
                libraryManager.setAvailableCopies(resultSet.getInt("available_copies"));
                libraryManagerList.add(libraryManager);
            }
        } catch (Exception e) {
            throw new Exception("It was not possible to retrieve all available books from the database", e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return libraryManagerList;
    }

    public int showAvailableBooksById(int bookId, int libraryId) throws Exception {
        PreparedStatement preparedStatement;
        int totalAvailableCopies = 0;

        try {
            preparedStatement = connection.prepareStatement(SQL_SHOW_AVAILABLE_BOOKS_BY_IDS);
            preparedStatement.setInt(1, bookId);
            preparedStatement.setInt(2, libraryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                totalAvailableCopies = resultSet.getInt("available_copies");
            }
        } catch (Exception e) {
            throw new Exception("It was not possible to check how many available books exist in the database.", e);
        }
        return totalAvailableCopies;
    }

    public String rentBookById(int bookId, int libraryId) throws Exception {
        PreparedStatement preparedStatement;
        int availableBooks = showAvailableBooksById(bookId, libraryId);
        String result = null;

        if (availableBooks > 0) {
            result = "Book id " + " was successfully rented.";
            preparedStatement = connection.prepareStatement(SQL_REMOVE_ONE_AVAILABLE_BOOK_BY_ID);
            preparedStatement.setInt(1, availableBooks);
            preparedStatement.setInt(2, bookId);
            preparedStatement.setInt(3, libraryId);
            preparedStatement.executeUpdate();
        } else {
            result = "Book id " + "was not successfully rented.";
        }
        return result;
    }

    public String refundBookById(int bookId, int libraryId) throws Exception {
        PreparedStatement preparedStatement;
        int availableBooks = showAvailableBooksById(bookId, libraryId);
        String result;

        if (availableBooks > 0) {
            result = "Book id " + " was successfully refunded.";
            preparedStatement = connection.prepareStatement(SQL_ADD_ONE_AVAILABLE_BOOK_BY_ID);
            preparedStatement.setInt(1, availableBooks);
            preparedStatement.setInt(2, bookId);
            preparedStatement.setInt(3, libraryId);
            preparedStatement.executeUpdate();
        } else {
            result = "Book id " + "was not successfully refunded.";
        }
        return result;
    }

}