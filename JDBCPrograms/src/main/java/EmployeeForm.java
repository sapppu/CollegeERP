//package com.sqltest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class EmployeeForm {
    // JDBC URL, username, and password
    static final String DB_URL = "jdbc:postgresql://localhost:5432/testdb";
    static final String USER = "sappu";
    static final String PASS = "sappu0";

    public static void main(String[] args) {
        // Create the frame for the form
        JFrame frame = new JFrame("Employee Form");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold the form components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        // Create the labels and text fields for Employee details
        JLabel enoLabel = new JLabel("Employee No:");
        JLabel enameLabel = new JLabel("Employee Name:");
        JLabel designationLabel = new JLabel("Designation:");
        JLabel salaryLabel = new JLabel("Salary:");
        JTextField enoField = new JTextField();
        JTextField enameField = new JTextField();
        JTextField designationField = new JTextField();
        JTextField salaryField = new JTextField();

        // Add the labels and text fields to the panel
        panel.add(enoLabel);
        panel.add(enoField);
        panel.add(enameLabel);
        panel.add(enameField);
        panel.add(designationLabel);
        panel.add(designationField);
        panel.add(salaryLabel);
        panel.add(salaryField);

        // Create the "Save" button and add an action listener to it
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the input values
                int eno = Integer.parseInt(enoField.getText());
                String ename = enameField.getText();
                String designation = designationField.getText();
                double salary = Double.parseDouble(salaryField.getText());

                // Save the employee data to the database
                saveEmployeeToDatabase(eno, ename, designation, salary);
            }
        });

        // Add the "Save" button to the panel
        panel.add(saveButton);

        // Add the panel to the frame and set it visible
        frame.add(panel);
        frame.setVisible(true);
    }

    // Method to save employee details into the database
    public static void saveEmployeeToDatabase(int eno, String ename, String designation, double salary) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            // Establish connection to the database
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // SQL query to insert employee data into the Employee table
            String sql = "INSERT INTO Employee (Eno, EName, Designation, Salary) VALUES (?, ?, ?, ?)";

            // Prepare the statement
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, eno);
            pstmt.setString(2, ename);
            pstmt.setString(3, designation);
            pstmt.setDouble(4, salary);

            // Execute the update
            pstmt.executeUpdate();

            // Show a message on successful insertion
            JOptionPane.showMessageDialog(null, "Employee details saved successfully!");
        } catch (SQLException e) {
            // Handle database errors
            JOptionPane.showMessageDialog(null, "Error saving employee details: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
