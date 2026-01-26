import java.sql.*;
import java.util.Scanner;

public class EmployeeMenuApp {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/testdb";
    static final String USER = "sappu";
    static final String PASS = "sappu0";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Insert Employee");
            System.out.println("2. Update Employee Salary");
            System.out.println("3. Display Employees");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    insertEmployee(scanner);
                    break;
                case 2:
                    updateEmployee(scanner);
                    break;
                case 3:
                    displayEmployees();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }

    public static void insertEmployee(Scanner scanner) {
        System.out.print("Enter Employee Number (ENo): ");
        int eno = scanner.nextInt();
        scanner.nextLine(); // Consume newline left by nextInt

        System.out.print("Enter Employee Name (EName): ");
        String ename = scanner.nextLine();

        System.out.print("Enter Salary: ");
        double salary = scanner.nextDouble();

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String insertSQL = "INSERT INTO employees (ENo, EName, Salary) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(insertSQL);


            stmt.setInt(1, eno);
            stmt.setString(2, ename);
            stmt.setDouble(3, salary);


            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee record inserted successfully!");
            } else {
                System.out.println("Failed to insert employee record.");
            }
        } catch (SQLException e) {
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

    public static void updateEmployee(Scanner scanner) {
        System.out.print("Enter Employee Number (ENo) to update: ");
        int eno = scanner.nextInt();

        System.out.print("Enter new Salary: ");
        double newSalary = scanner.nextDouble();

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // SQL query to update employee salary
            String updateSQL = "UPDATE employees SET Salary = ? WHERE ENo = ?";
            stmt = conn.prepareStatement(updateSQL);

            // Set parameters
            stmt.setDouble(1, newSalary);
            stmt.setInt(2, eno);

            // Execute the update query
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee salary updated successfully!");
            } else {
                System.out.println("No employee found with the given ENo.");
            }
        } catch (SQLException e) {
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

    // Method to display all employee records from the employees table
    public static void displayEmployees() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            // SQL query to fetch all employee records
            String selectSQL = "SELECT * FROM employees";
            rs = stmt.executeQuery(selectSQL);

            // Display the results in tabular format
            System.out.println("\nEmployee Records:");
            System.out.println("ENo\tEName\t\tSalary");
            System.out.println("-----------------------------------");

            while (rs.next()) {
                int eno = rs.getInt("ENo");
                String ename = rs.getString("EName");
                double salary = rs.getDouble("Salary");
                System.out.println(eno + "\t" + ename + "\t\t" + salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
