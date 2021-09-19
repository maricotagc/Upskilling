package mm.example.Block7.repository;

import mm.example.Block7.model.Library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LibraryRepository {
    private static final String SQL_INSERT = "INSERT INTO library (id, name, address) VALUES (?, ?, ?)";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM library WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM library WHERE id = ?";
    private static final String SQL_UPDATE_BY_ID = "UPDATE library SET name = ?, address = ? WHERE id = ?";
    private static final String SQL_DELETE_ALL = "DELETE FROM library";

    private final Connection connection;

    public LibraryRepository(Connection connection) {
        this.connection = connection;
    }

    public int add(int LibraryId, String libraryName, String libraryAddress) throws Exception {
        PreparedStatement preparedStatement = null;
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setInt(1, LibraryId);
            preparedStatement.setString(2, libraryName);
            preparedStatement.setString(3, libraryAddress);
            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new Exception("It was not possible to add the new library: " + libraryName, e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return result;
    }

    public Library findById(int libraryId) throws Exception {
        PreparedStatement preparedStatement = null;
        Library library = new Library();
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
            preparedStatement.setInt(1, libraryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                library.setId(resultSet.getInt("id"));
                library.setName(resultSet.getString("name"));
                library.setAddress(resultSet.getString("address"));
            }
        } catch (Exception e) {
            throw new Exception("It was not possible to find library with id = " + libraryId, e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return library;
    }

    public int remove(int libraryId) throws Exception {
        PreparedStatement preparedStatement = null;
        int result;
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, libraryId);
            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new Exception("It was not possible to remove library with id = " + libraryId, e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
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
            throw new Exception("It was not possible to remove all libraries from the database.", e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return result;
    }

    public int update(int libraryId, String libraryName, String libraryAddress) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_BY_ID);
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_BY_ID);
            preparedStatement.setString(1, libraryName);
            preparedStatement.setString(2, libraryAddress);
            preparedStatement.setInt(3, libraryId);
            result = preparedStatement.executeUpdate();
        } catch (Exception exception) {
            throw new Exception("It was not possible to update library " + libraryName, exception);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            return result;
        }
    }
}