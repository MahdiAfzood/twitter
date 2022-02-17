package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingleConnection {
    private static SingleConnection single_connection;
    private Connection connection;

    private SingleConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres","postgres","aliila");
    }

    public static SingleConnection getInstance() throws SQLException, ClassNotFoundException {
        if (single_connection==null){
            single_connection = new SingleConnection();
        }
        return single_connection;
    }

    public Connection getConnection() {
        return connection;
    }

}
