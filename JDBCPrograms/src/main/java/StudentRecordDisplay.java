import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentRecordDisplay {
    // JDBC URL, username, and password
    static final String DB_URL = "jdbc:postgresql://localhost:5432/testdb";
    static final String USER = "sappu";
    static final String PASS = "sappu0";

    public static void main(String[] args) {
        // Create the JFrame for the GUI
        JFrame frame = new JFrame("Student Record Display");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the panel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        // Create labels and text fields for displaying the student record
        JLabel rnoLabel = new JLabel("Enter Roll No:");
        JLabel snameLabel = new JLabel("Student Name:");
        JLabel perLabel = new JLabel("Percentage:");

        JTextField rnoField = new JTextField(); // Allow user input
        JTextField snameField = new JTextField();
        JTextField perField = new JTextField();

        // Make name and percentage fields non-editable
        snameField.setEditable(false);
        perField.setEditable(false);

        // Create a button to fetch and display the student record
        JButton displayButton = new JButton("Fetch Record");

        // Add labels, text fields, and button to the panel
        panel.add(rnoLabel);
        panel.add(rnoField);
        panel.add(snameLabel);
        panel.add(snameField);
        panel.add(perLabel);
        panel.add(perField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(displayButton);

        // Add the panel to the frame
        frame.add(panel);
        frame.setVisible(true);

        // Add an action listener to the displayButton
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get user input roll number
                String rollNoText = rnoField.getText().trim();
                if (rollNoText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a Roll Number.");
                    return;
                }
                try {
                    int rollNo = Integer.parseInt(rollNoText);
                    displayStudentRecord(rollNo, snameField, perField);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Roll Number. Please enter a number.");
                }
            }
        });
    }

    // Method to display the student record from the database
    public static void displayStudentRecord(int rollNo, JTextField snameField, JTextField perField) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // Establish connection to the database
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Prepare SQL query to fetch the record by Roll Number
            String query = "SELECT * FROM Student WHERE RNo = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, rollNo);
            rs = pstmt.executeQuery();

            // If the record exists, display it in the text fields
            if (rs.next()) {
                String sname = rs.getString("SName");
                double per = rs.getDouble("Per");

                // Set the values to the corresponding text fields
                snameField.setText(sname);
                perField.setText(String.valueOf(per));
            } else {
                // If no records found
                JOptionPane.showMessageDialog(null, "No student found with Roll No: " + rollNo);
                snameField.setText("");
                perField.setText("");
            }
        } catch (SQLException e) {
            // Handle database errors
            JOptionPane.showMessageDialog(null, "Error fetching record: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                // Close resources
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
