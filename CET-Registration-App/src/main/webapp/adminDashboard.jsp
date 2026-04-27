<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@ page import="java.sql.*, com.cet.DBConnection" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Admin Dashboard - CET</title></head>
<body>
<h1>Admin Dashboard</h1>
<h2>All Student Records</h2>
<hr>
<%
Boolean adminLoggedIn = (Boolean) session.getAttribute("adminLoggedIn");
if (adminLoggedIn == null || !adminLoggedIn) { response.sendRedirect("adminLogin.jsp?msg=Please+login+as+admin"); return; }
Connection conn = DBConnection.getConnection();
Statement st = conn.createStatement();
ResultSet rs = st.executeQuery("SELECT reg_no, name, course, dob, marks FROM students ORDER BY id ASC");
out.println("<table border='1' cellpadding='5'>");
out.println("<tr><th>Sr. No</th><th>Reg No</th><th>Name</th><th>Course</th><th>DOB</th><th>Marks</th></tr>");
int sr = 1;
boolean found = false;
while (rs.next()) {
    found = true;
    String m = rs.getString("marks");
    out.println("<tr><td>" + sr++ + "</td><td>" + rs.getString("reg_no") + "</td><td>" + rs.getString("name") + "</td><td>" + rs.getString("course") + "</td><td>" + rs.getString("dob") + "</td><td>" + (m==null?"Not Attempted":m+" / 2") + "</td></tr>");
}
if (!found) out.println("<tr><td colspan='6'>No records found.</td></tr>");
out.println("</table>");
rs.close(); st.close(); conn.close();
%>
<br><a href="logout.jsp">Logout</a>
</body>
</html>
