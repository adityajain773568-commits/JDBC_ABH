package com.scaleupindia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class Demo {
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/petistaan_jdbc";
    private static final String DATABASE_PASSWORD = "@*Aa+d-itya12345";

    static void main() throws SQLException {
        Connection connection = null;

        try {
            Class.forName(DATABASE_DRIVER);
                 connection = DriverManager.getConnection(DATABASE_URL,DATABASE_USERNAME ,DATABASE_PASSWORD);
            System.out.println("URL : " + connection.getMetaData().getURL());
            System.out.println("USERNAMe : " + connection.getMetaData().getUserName());
            System.out.println("Database product name : " + connection.getMetaData().getDatabaseProductName());
            System.out.println("Password : " + connection.getMetaData().getDatabaseProductVersion() );
            System.out.println("Connection established..");
            }
        catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        finally {
            if (Objects.nonNull(connection)){
                connection.close();

            }
        }

    }
}
