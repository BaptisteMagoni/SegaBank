package net.segabank.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    private static Connection connection;
    private static final String PROPS_FILE = "./resources/db.properties";

    private ConnectionManager(){}

    public static Connection getConnection() throws SQLException, IOException, ClassNotFoundException {
        if(connection == null || !connection.isValid(4)){
            Properties props = new Properties();
            try (FileInputStream fis = new FileInputStream(PROPS_FILE)){
                props.load(fis);
            }
            String driverClass = props.getProperty("jdbc.class.driver");
            String dbURL = props.getProperty("jdbc.db.url");
            String dbLogin = props.getProperty("jdbc.db.login");
            String dbPassword = props.getProperty("jdbc.db.password");
            Class.forName(driverClass);
            connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword);
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if(connection != null && connection.isValid(2))
            connection.close();
    }

}
