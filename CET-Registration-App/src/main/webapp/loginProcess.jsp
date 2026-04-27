<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@ page import="java.sql.*, com.cet.DBConnection" %>
<%
String regNo = request.getParameter("regNo");
String password = request.getParameter("password");
if (regNo == null || password == null || regNo.trim().isEmpty() || password.trim().isEmpty()) {
    response.sendRedirect("index.jsp?msg=Please+enter+Registration+Number+and+Password");
    return;
}
Connection conn = DBConnection.getConnection();
PreparedStatement ps = conn.prepareStatement("SELECT * FROM students WHERE reg_no = ? AND password = ?");
ps.setString(1, regNo.trim());
ps.setString(2, password.trim());
ResultSet rs = ps.executeQuery();
if (rs.next()) {
    session.setAttribute("regNo", rs.getString("reg_no"));
    session.setAttribute("studentName", rs.getString("name"));
    session.setAttribute("course", rs.getString("course"));
    session.setAttribute("dob", rs.getString("dob"));
    session.setAttribute("userType", "student");
    rs.close(); ps.close(); conn.close();
    response.sendRedirect("dashboard.jsp");
} else {
    rs.close(); ps.close(); conn.close();
    response.sendRedirect("index.jsp?msg=Invalid+Registration+Number+or+Password");
}
%>
