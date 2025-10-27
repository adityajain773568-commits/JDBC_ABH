package com.scaleupindia.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String DATABASE_URL = "database.url";
    private static final String DATABASE_USERNAME = "database.username";
    private static final String DATABASE_PASS = "database.password";
    private static final PropertiesConfig propertiesConfig = PropertiesConfig.getInstance();
    private DatabaseConfig(){

    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(propertiesConfig.getProperty(DATABASE_URL), propertiesConfig.getProperty(DATABASE_USERNAME), propertiesConfig.getProperty(DATABASE_PASS));
    }

}
