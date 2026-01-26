//package com.sqltest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class ProjectTableApp {
    // JDBC URL, username, and password
    static final String DB_URL = "jdbc:postgresql://localhost:5432/testdb";
    static final String USER = "sappu";
    static final String PASS = "sappu0";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createGUI());
    }

    private static void createGUI() {
        // Create the JFrame for the GUI
        JFrame frame = new JFrame("PROJECT Table Viewer");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Create a panel for buttons and table display
        JPanel panel = new JPanel(new BorderLayout());

        // Create a button to load data from the PROJECT table
        JButton loadButton = new JButton("Load Project Data");
        panel.add(loadButton, BorderLayout.NORTH);

        // Create JTable to display project records
        String[] columns = {"Project ID", "Project Name", "Project Description", "Project Status"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add the panel to the frame
        frame.add(panel);
        frame.setVisible(true);

        // ActionListener for the Load button
        loadButton.addActionListener(e -> loadProjectData(tableModel));

        // Create the PROJECT table and insert sample data (only if the table is empty)
        createAndInsertProjectData();
    }

    // Method to create the PROJECT table and insert sample data
    private static void createAndInsertProjectData() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS PROJECT (" +
                "project_id SERIAL PRIMARY KEY, " +
                "project_name VARCHAR(100), " +
                "project_description TEXT, " +
                "project_status VARCHAR(50))";

        String checkDataSQL = "SELECT COUNT(*) FROM PROJECT";
        String insertSQL = "INSERT INTO PROJECT (project_name, project_description, project_status) VALUES " +
                "('Project A', 'This is the description for Project A', 'Ongoing')," +
                "('Project B', 'This is the description for Project B', 'Completed')," +
                "('Project C', 'This is the description for Project C', 'Not Started')";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            // Create table if not exists
            stmt.executeUpdate(createTableSQL);

            // Check if data already exists
            try (ResultSet rs = stmt.executeQuery(checkDataSQL)) {
                if (rs.next() && rs.getInt(1) == 0) {
                    stmt.executeUpdate(insertSQL);
                    System.out.println("Sample data inserted into PROJECT table.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to load data from the PROJECT table and display it in the JTable
    private static void loadProjectData(DefaultTableModel tableModel) {
        String selectSQL = "SELECT * FROM PROJECT";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {

            // Clear existing rows in the table model
            tableModel.setRowCount(0);

            // Iterate through the result set and add rows to the table model
            while (rs.next()) {
                int projectId = rs.getInt("project_id");
                String projectName = rs.getString("project_name");
                String projectDescription = rs.getString("project_description");
                String projectStatus = rs.getString("project_status");

                tableModel.addRow(new Object[]{projectId, projectName, projectDescription, projectStatus});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
