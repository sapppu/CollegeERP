import java.sql.*;

public class TeacherDatabase {
    // Method to insert records into Teacher table
    public static void insertTeachers() throws SQLException {
        // PostgreSQL database connection details
        String url = "jdbc:postgresql://localhost:5432/testdb"; // Replace with your DB name
        String user = "sappu"; // Replace with your DB usernameata
        String password = "sappu0"; // Replace with your DB password
        Connection conn = DriverManager.getConnection(url, user, password);

        // SQL query to insert teacher details
        String insertQuery = "INSERT INTO Teacher (TNo, TName, Subject) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(insertQuery);

        // Insert 5 records into Teacher table
        pstmt.setInt(1, 106); pstmt.setString(2, "John Doe"); pstmt.setString(3, "JAVA");
        pstmt.executeUpdate();
        pstmt.setInt(1, 107); pstmt.setString(2, "Jane Smith"); pstmt.setString(3, "Python");
        pstmt.executeUpdate();
        pstmt.setInt(1, 108); pstmt.setString(2, "Alice Brown"); pstmt.setString(3, "JAVA");
        pstmt.executeUpdate();
        pstmt.setInt(1, 109); pstmt.setString(2, "Bob White"); pstmt.setString(3, "C++");
        pstmt.executeUpdate();
        pstmt.setInt(1, 1010); pstmt.setString(2, "Charlie Green"); pstmt.setString(3, "JAVA");
        pstmt.executeUpdate();

        System.out.println("Teacher records inserted successfully.");
        pstmt.close();
        conn.close();
    }

    // Method to display teachers teaching JAVA
    public static void displayJavaTeachers() throws SQLException {
        // PostgreSQL database connection details
        String url = "jdbc:postgresql://localhost:5432/testdb"; // Replace with your DB name
        String user = "sappu"; // Replace with your DB username
        String password = "sappu0"; // Replace with your DB password
        Connection conn = DriverManager.getConnection(url, user, password);

        // SQL query to get teachers who teach JAVA
        String selectQuery = "SELECT * FROM Teacher WHERE Subject = ?";

        PreparedStatement pstmt = conn.prepareStatement(selectQuery);
        pstmt.setString(1, "JAVA"); // Set parameter for JAVA subject
        ResultSet rs = pstmt.executeQuery();

        System.out.println("Teachers teaching JAVA:");
        while (rs.next()) {
            int tNo = rs.getInt("TNo");
            String tName = rs.getString("TName");
            String subject = rs.getString("Subject");
            System.out.println("TNo: " + tNo + ", TName: " + tName + ", Subject: " + subject);
        }

        rs.close();
        pstmt.close();
        conn.close();
    }

    public static void main(String[] args) {
        try {
            // Insert teacher records into the database
            insertTeachers();
            // Display teachers who are teaching JAVA
            displayJavaTeachers();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}
