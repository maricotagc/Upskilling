package mm.example.Block7.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String URL = "jdbc:mysql://localhost:3306/bookshop";
    private static final String USER = "root";
    private static final String PASSWORD = "@Brasil2010";

    private Connection connection;

    public ConnectionManager() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
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
