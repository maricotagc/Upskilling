//layer that connects the code to the repository (database) and has methods CRUD

package mm.example.Block7.repository;

import mm.example.Block7.exception.LibraryException;
import mm.example.Block7.model.Library;

import java.sql.*;

public class LibraryRepository extends AbstractRepository {
    private static final String SQL_INSERT = "INSERT INTO library (name, address) VALUES (?, ?)";
    private static final String SQL_SELECT_BY_ID = "SELECT id, name, address FROM library WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM library WHERE id = ?";
    private static final String SQL_UPDATE_BY_ID = "UPDATE library SET name = ?, address = ? WHERE id = ?";

    public LibraryRepository(Connection connection) {
        super(connection);
    }

    public int create(String libraryName, String libraryAddress) throws LibraryException {
        PreparedStatement preparedStatement = null;
        int result;

        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, libraryName);
            preparedStatement.setString(2, libraryAddress);
            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new LibraryException("It was not possible to add the new library: " + libraryName);
        } finally {
            close(preparedStatement);
        }
        return result;
    }

    public Library find(int libraryId) throws LibraryException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Library library = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
            preparedStatement.setInt(1, libraryId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                library = new Library();
                library.setId(resultSet.getInt("id"));
                library.setName(resultSet.getString("name"));
                library.setAddress(resultSet.getString("address"));
            }
        } catch (Exception e) {
            throw new LibraryException("It was not possible to find library with id = " + libraryId);
        } finally {
            close(resultSet);
           close(preparedStatement);
        }
        return library;
    }

    public boolean findById(int libraryId) throws LibraryException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int result = 0;

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
            preparedStatement.setInt(1, libraryId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt("count");
            }
        } catch (Exception e) {
            throw new LibraryException("It was not possible to find library with id = " + libraryId);
        } finally {
            close(resultSet);
            close(preparedStatement);
        }
        return result > 0;
    }

    public int remove(int libraryId) throws LibraryException {
        PreparedStatement preparedStatement = null;
        int result;

        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, libraryId);
            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new LibraryException("It was not possible to remove library with id = " + libraryId);
        } finally {
            close(preparedStatement);
        }
        return result;
    }

    public int update(int libraryId, String libraryName, String libraryAddress) throws LibraryException {
        PreparedStatement preparedStatement = null;
        int result;

        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_BY_ID);
            preparedStatement.setString(1, libraryName);
            preparedStatement.setString(2, libraryAddress);
            preparedStatement.setInt(3, libraryId);
            result = preparedStatement.executeUpdate();
        } catch (Exception exception) {
            throw new LibraryException("It was not possible to update library " + libraryName);
        } finally {
            close(preparedStatement);
        }
        return result;
    }
}