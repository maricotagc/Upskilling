package mm.example.Block7;

import java.sql.*;

public class ConnectionMysql {

    public Connection createDBConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshop", "root", "@Brasil2010");
    }
}
