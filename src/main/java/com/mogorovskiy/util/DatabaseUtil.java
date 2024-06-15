package com.mogorovskiy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    private static final String URL = "jdbc:sqlite:./quiz.db";

    public static Connection connect() {
        Connection conn;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to SQLite database: " + e);
        }
        return conn;
    }
}
