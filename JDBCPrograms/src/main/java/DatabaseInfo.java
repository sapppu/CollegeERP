import java.sql.*;

public class DatabaseInfo {
    public static void main(String[] args) {
        // PostgreSQL database connection details
        String url = "jdbc:postgresql://localhost:5432/testdb"; // Your database name (dpu)
        String user = "sappu"; // Your PostgreSQL username
        String password = "sappu0"; // Your PostgreSQL password
        Connection conn = null;
        DatabaseMetaData metaData = null;

        try {
            // Establish the database connection
            conn = DriverManager.getConnection(url, user, password);
            // Get DatabaseMetaData object
            metaData = conn.getMetaData();

            // Display database information
            System.out.println("Database Information:");
            System.out.println("Database Product Name: " + metaData.getDatabaseProductName());
            System.out.println("Database Product Version: " + metaData.getDatabaseProductVersion());
            System.out.println("Driver Name: " + metaData.getDriverName());
            System.out.println("Driver Version: " + metaData.getDriverVersion());
            System.out.println("Max Connections: " + metaData.getMaxConnections());

            // Get the list of all tables in the database
            System.out.println("\nList of Tables:");
            ResultSet rs = metaData.getTables(null, null, "%", new String[] {"TABLE"});
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                System.out.println(tableName);
            }
            rs.close(); // Close the result set
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close(); // Close the connection
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
