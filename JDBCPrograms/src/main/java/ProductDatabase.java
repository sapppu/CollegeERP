import java.sql.*;

public class ProductDatabase {
    // JDBC URL, username, and password
    static final String DB_URL = "jdbc:postgresql://localhost:5432/testdb";
    static final String USER = "sappu";
    static final String PASS = "sappu0";

    public static void main(String[] args) {
        // Creating Product Table
        createProductTable();
        // Inserting Product Records
        insertProductRecords();
        // Displaying Product Records
        displayAllProducts();
    }

    // Method to create Product table
    public static void createProductTable() {
        Connection conn = null;
        Statement stmt = null;
        try {
            // Establish connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            // SQL query to create Product table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS Product ("
                    + "Pid INT PRIMARY KEY, "
                    + "Pname VARCHAR(100), "
                    + "Price DECIMAL(10, 2))";
            // Execute the query
            stmt.executeUpdate(createTableSQL);
            System.out.println("Product table created successfully!");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to insert records into the Product table
    public static void insertProductRecords() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            // Establish connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // SQL query to insert product records
            String insertSQL = "INSERT INTO Product (Pid, Pname, Price) VALUES (?, ?, ?)";
            // Prepare statement
            pstmt = conn.prepareStatement(insertSQL);

            // Inserting 5 product records
            Object[][] products = {
                    {101, "Laptop", 50000.00},
                    {102, "Smartphone", 30000.00},
                    {103, "Headphones", 1500.00},
                    {104, "Smartwatch", 8000.00},
                    {105, "Tablet", 25000.00}
            };

            for (Object[] product : products) {
                pstmt.setInt(1, (int) product[0]);
                pstmt.setString(2, (String) product[1]);
                pstmt.setDouble(3, (double) product[2]);
                pstmt.executeUpdate();
            }

            System.out.println("Product records inserted successfully!");
        } catch (SQLException e) {
            System.out.println("Error inserting records: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to display all product records
    public static void displayAllProducts() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // Establish connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            // SQL query to get all products
            String selectSQL = "SELECT * FROM Product";
            // Execute query and get result set
            rs = stmt.executeQuery(selectSQL);
            // Displaying product records
            System.out.println("Product Records:");
            while (rs.next()) {
                int pid = rs.getInt("Pid");
                String pname = rs.getString("Pname");
                double price = rs.getDouble("Price");
                System.out.println("Pid: " + pid + ", Pname: " + pname + ", Price: " + price);
            }
        } catch (SQLException e) {
            System.out.println("Error displaying records: " + e.getMessage());
        } finally {
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
