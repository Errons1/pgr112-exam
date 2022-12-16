package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySQL {

    public Connection connection() {
        String url = "jdbc:mysql://127.0.0.1:3306/quizdb";
        String username = "root";
        String password = "root";

        /*
        *   Check if class is available first
        *   Makes connection with MySQL and turn of AutoCommit so rollback and manual commit is available
        *   return class Connection
        * */
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
            return connection;

//            If username or password to MySQL is wrong
        } catch (SQLException e) {
            System.out.println("Invalid login.");
            e.printStackTrace();

//         The class "com.mysql.cj.jdbc.Driver" not found!
        } catch (ClassNotFoundException e) {
            System.out.println("Driver for MySQL JDBC 8.0 not found!");
            e.printStackTrace();
        }

        return null;

    }
}
