//package com.sqltest;

import java.sql.*;

public class DonarTableMetaData {
    // JDBC URL, username, and password
    static final String DB_URL = "jdbc:postgresql://localhost:5432/testdb";
    static final String USER = "sappu";
    static final String PASS = "sappu0";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData rsMetaData = null;

        try {
            // Establish connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Create a statement
            stmt = conn.createStatement();

            // Execute the query to select all data from the DONAR table
            rs = stmt.executeQuery("SELECT * FROM DONOR");

            // Get ResultSetMetaData object
            rsMetaData = rs.getMetaData();

            // Get the number of columns in the DONAR table
            int columnCount = rsMetaData.getColumnCount();

            // Display column information
            System.out.println("Column Information for the DONAR Table:");
            System.out.println("-----------------------------------------");

            for (int i = 1; i <= columnCount; i++) {
                String columnName = rsMetaData.getColumnName(i);
                int columnType = rsMetaData.getColumnType(i);
                String columnTypeName = rsMetaData.getColumnTypeName(i);
                int columnSize = rsMetaData.getColumnDisplaySize(i);
                String nullable = rsMetaData.isNullable(i) == ResultSetMetaData.columnNullable
                        ? "Nullable"
                        : "Not Nullable";

                // Display each column's details
                System.out.println("Column " + i + ":");
                System.out.println(" Name: " + columnName);
                System.out.println(" Type: " + columnTypeName + " (" + columnType + ")");
                System.out.println(" Size: " + columnSize);
                System.out.println(" Nullable: " + nullable);
                System.out.println("-----------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
