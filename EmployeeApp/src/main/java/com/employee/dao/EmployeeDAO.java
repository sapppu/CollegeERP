package com.employee.dao;

import com.employee.db.DBConnection;
import com.employee.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public void save(Employee emp) throws SQLException {
        String sql = "INSERT INTO employees (employee_name, employee_address, employee_dob, employee_salary) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, emp.getEmployeeName());
            ps.setString(2, emp.getEmployeeAddress());
            ps.setString(3, emp.getEmployeeDOB());
            ps.setDouble(4, emp.getEmployeeSalary());
            ps.executeUpdate();
        }
    }

    public void update(Employee emp) throws SQLException {
        String sql = "UPDATE employees SET employee_name=?, employee_address=?, employee_dob=?, employee_salary=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, emp.getEmployeeName());
            ps.setString(2, emp.getEmployeeAddress());
            ps.setString(3, emp.getEmployeeDOB());
            ps.setDouble(4, emp.getEmployeeSalary());
            ps.setInt(5, emp.getId());
            ps.executeUpdate();
        }
    }

    public List<Employee> findAll() throws SQLException {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try (Connection con = DBConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Employee(
                        rs.getInt("id"),
                        rs.getString("employee_name"),
                        rs.getString("employee_address"),
                        rs.getString("employee_dob"),
                        rs.getDouble("employee_salary")));
            }
        }
        return list;
    }

    public Employee findById(int id) throws SQLException {
        String sql = "SELECT * FROM employees WHERE id=?";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Employee(
                            rs.getInt("id"),
                            rs.getString("employee_name"),
                            rs.getString("employee_address"),
                            rs.getString("employee_dob"),
                            rs.getDouble("employee_salary"));
                }
            }
        }
        return null;
    }
}
