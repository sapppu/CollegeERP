package JDBC_Collable;

import java.sql.*;

public class CallableExample {
    public static void main(String[] args) {

        //String url = "jdbc:mysql://localhost:3306/socs";
        //String user = "root";
        //String password = "123";

        Connection conn = null;
        CallableStatement stmt = null;

        try {
            // Load driver (optional in modern JDBC)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SOCS","root","root123");

            // Prepare callable statement
            stmt = conn.prepareCall("{call update_age(?, ?, ?)}");

            // Set IN parameters
            stmt.setInt(1, 333);     // employee id
            stmt.setInt(2, 15); // increment amount

            // Register OUT parameter
            stmt.registerOutParameter(3, Types.INTEGER);

            // Execute procedure
            stmt.execute();

            // Get OUT parameter
            double updatedAge = stmt.getInt(3);

            System.out.println("Updated Age: " + updatedAge);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}