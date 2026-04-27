package com.cet;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/cet_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "sappu0";

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
}
