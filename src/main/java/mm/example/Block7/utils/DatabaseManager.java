package mm.example.Block7.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseManager {
    private static final String URL = "jdbc:sqlite:test.db";

    private Connection connection;

    public DatabaseManager() throws Exception {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(URL);

        } catch (Exception e) {
            throw new Exception("It was not possible to connect to the bookshop database", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}