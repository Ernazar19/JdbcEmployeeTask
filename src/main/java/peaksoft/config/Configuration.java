package peaksoft.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Configuration {
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String userName = "postgres";
    private static final String password = "1234";

    public static Connection getConnection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(
                    url,
                    userName,
                    password

            );
            System.out.println("successfully connected...");
        }catch(SQLException e ){
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
