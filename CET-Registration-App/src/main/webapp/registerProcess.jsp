<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@ page import="java.sql.*, com.cet.DBConnection" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Registration Result - CET</title></head>
<body>
<h1>CET Registration</h1>
<hr>
<%
String name = request.getParameter("name");
String course = request.getParameter("course");
String dob = request.getParameter("dob");
if (name == null || name.trim().isEmpty() || course == null || dob == null || dob.trim().isEmpty()) {
    out.println("<p>Error: All fields are required. <a href='register.jsp'>Go Back</a></p>");
    return;
}
String regNo = null;
synchronized (application) {
    Integer counter = (Integer) application.getAttribute("cetRegCounter");
    if (counter == null) {
        Connection ic = DBConnection.getConnection();
        Statement is = ic.createStatement();
        ResultSet ir = is.executeQuery("SELECT COUNT(*) FROM students");
        ir.next();
        counter = 1001 + ir.getInt(1);
        ir.close(); is.close(); ic.close();
    }
    regNo = "CET" + counter;
    application.setAttribute("cetRegCounter", counter + 1);
}
String password = regNo;
Connection conn = DBConnection.getConnection();
PreparedStatement ps = conn.prepareStatement("INSERT INTO students (reg_no, name, course, dob, password) VALUES (?, ?, ?, ?, ?)");
ps.setString(1, regNo);
ps.setString(2, name.trim());
ps.setString(3, course);
ps.setString(4, dob);
ps.setString(5, password);
ps.executeUpdate();
ps.close(); conn.close();
out.println("<h2>Registration Successful!</h2>");
out.println("<hr>");
out.println("<table border='1' cellpadding='5'>");
out.println("<tr><th>Field</th><th>Details</th></tr>");
out.println("<tr><td>Name</td><td>" + name.trim() + "</td></tr>");
out.println("<tr><td>Course</td><td>" + course + "</td></tr>");
out.println("<tr><td>Date of Birth</td><td>" + dob + "</td></tr>");
out.println("<tr><td><b>Registration Number</b></td><td><b>" + regNo + "</b></td></tr>");
out.println("<tr><td><b>Password</b></td><td><b>" + password + "</b></td></tr>");
out.println("</table>");
out.println("<br><p>Note your <b>Registration Number</b> and <b>Password</b> for login.</p>");
%>
<a href="index.jsp">Go to Login Page</a>
</body>
</html>
