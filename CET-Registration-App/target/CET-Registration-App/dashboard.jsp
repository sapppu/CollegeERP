<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>CET Menu - Dashboard</title></head>
<body>
<%
String regNo = (String) session.getAttribute("regNo");
String studentName = (String) session.getAttribute("studentName");
String course = (String) session.getAttribute("course");
if (regNo == null) { response.sendRedirect("index.jsp?msg=Please+login+first"); return; }
%>
<h1>CET Menu</h1>
<hr>
<%
out.println("<p>Welcome, <b>" + studentName + "</b></p>");
out.println("<p>Registration No : <b>" + regNo + "</b></p>");
out.println("<p>Course : <b>" + course + "</b></p>");
%>
<hr>
<h2>Available Tests</h2>
<ul><li><a href="test.jsp">Start Test</a></li></ul>
<hr>
<a href="logout.jsp">Logout</a>
</body>
</html>
