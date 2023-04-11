package com.clevertec.strezhik.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {

    public Connection getConnection() throws SQLException {
        try {
            ResourcesReader resource = ResourcesReader.getResourcesReader();
            String url = resource.getUrl();
            String user = resource.getUser();
            String password = resource.getPassword();
            String driver = resource.getDriver();
            Class.forName(driver);
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
