package by.expertsoft.contacts.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

    public ConnectDB() {
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        String db_connect_string = "jdbc:sqlserver://localhost;databaseName=contacts;user=adm;password=1234567;";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection connection = DriverManager.getConnection(db_connect_string);
        return connection;
    }   

}
