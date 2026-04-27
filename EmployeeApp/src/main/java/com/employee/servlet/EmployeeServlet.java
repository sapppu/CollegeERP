package com.employee.servlet;

import com.employee.dao.EmployeeDAO;
import com.employee.model.Employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class EmployeeServlet extends HttpServlet {

    private final EmployeeDAO dao = new EmployeeDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Employee emp = null;
        String idParam = request.getParameter("id");

        if (idParam != null && !idParam.isEmpty()) {
            try {
                emp = dao.findById(Integer.parseInt(idParam));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String id = (emp != null) ? String.valueOf(emp.getId()) : "";
        String name = (emp != null) ? emp.getEmployeeName() : "";
        String address = (emp != null) ? emp.getEmployeeAddress() : "";
        String dob = (emp != null) ? emp.getEmployeeDOB() : "";
        String salary = (emp != null) ? String.valueOf(emp.getEmployeeSalary()) : "";
        String heading = (emp != null) ? "Edit Employee" : "New Employee Registration";

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>Employee Registration</title></head>");
        out.println("<body>");
        out.println("<h2>" + heading + "</h2>");

        out.println("<form method='post' action='EmployeeServlet'>");
        out.println("  <input type='hidden' name='id' value='" + id + "'/>");

        out.println("  <table border='1' cellpadding='5' cellspacing='0'>");

        out.println("    <tr>");
        out.println("      <td>EmployeeName</td>");
        out.println("      <td><input type='text' name='employeeName' value='" + name + "' required/></td>");
        out.println("    </tr>");

        out.println("    <tr>");
        out.println("      <td>EmployeeAddress</td>");
        out.println("      <td><input type='text' name='employeeAddress' value='" + address + "'/></td>");
        out.println("    </tr>");

        out.println("    <tr>");
        out.println("      <td>EmployeeDOB</td>");
        out.println("      <td><input type='date' name='employeeDOB' value='" + dob + "'/></td>");
        out.println("    </tr>");

        out.println("    <tr>");
        out.println("      <td>EmployeeSalary</td>");
        out.println("      <td><input type='number' step='0.01' name='employeeSalary' value='" + salary + "'/></td>");
        out.println("    </tr>");

        out.println("    <tr>");
        out.println("      <td colspan='2'>");
        out.println("        <input type='submit' value='Register'/>");
        out.println("        &nbsp;");
        out.println("        <input type='reset' value='Cancel'/>");
        out.println("      </td>");
        out.println("    </tr>");

        out.println("  </table>");
        out.println("</form>");

        out.println("<br/><a href='EmployeeListServlet'>EmployeeList</a>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        String name = request.getParameter("employeeName");
        String address = request.getParameter("employeeAddress");
        String dob = request.getParameter("employeeDOB");
        String salaryStr = request.getParameter("employeeSalary");

        double salary = 0;
        if (salaryStr != null && !salaryStr.isEmpty()) {
            salary = Double.parseDouble(salaryStr);
        }

        Employee emp = new Employee();
        emp.setEmployeeName(name);
        emp.setEmployeeAddress(address);
        emp.setEmployeeDOB(dob);
        emp.setEmployeeSalary(salary);

        try {
            if (idParam != null && !idParam.isEmpty()) {
                emp.setId(Integer.parseInt(idParam));
                dao.update(emp);
            } else {
                dao.save(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("EmployeeListServlet");
    }
}
