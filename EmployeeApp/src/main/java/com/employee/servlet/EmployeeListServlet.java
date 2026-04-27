package com.employee.servlet;

import com.employee.dao.EmployeeDAO;
import com.employee.model.Employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class EmployeeListServlet extends HttpServlet {

    private final EmployeeDAO dao = new EmployeeDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        List<Employee> employees = null;
        try {
            employees = dao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>Employee List</title></head>");
        out.println("<body>");
        out.println("<h2>Employee List</h2>");
        out.println("<a href='EmployeeServlet'>Home</a>");
        out.println("<br><br>");

        if (employees == null || employees.isEmpty()) {
            out.println("<p>No employees found. <a href='EmployeeServlet'>Register one</a>.</p>");
        } else {
            out.println("<table border='1' cellpadding='5' cellspacing='0'>");
            out.println("  <tr>");
            out.println("    <th>ID</th>");
            out.println("    <th>EmployeeName</th>");
            out.println("    <th>EmployeeAddress</th>");
            out.println("    <th>EmployeeDOB</th>");
            out.println("    <th>EmployeeSalary</th>");
            out.println("    <th>Action</th>");
            out.println("  </tr>");

            for (Employee e : employees) {
                out.println("  <tr>");
                out.println("    <td>" + e.getId() + "</td>");
                out.println("    <td>" + e.getEmployeeName() + "</td>");
                out.println("    <td>" + e.getEmployeeAddress() + "</td>");
                out.println("    <td>" + e.getEmployeeDOB() + "</td>");
                out.println("    <td>" + e.getEmployeeSalary() + "</td>");
                out.println("    <td><a href='EmployeeServlet?id=" + e.getId() + "'>Edit</a></td>");
                out.println("  </tr>");
            }

            out.println("</table>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}
