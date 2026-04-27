<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Admin Login - CET</title></head>
<body>
<h1>Admin Login</h1>
<hr>
<%
String msg = request.getParameter("msg");
if (msg != null && !msg.isEmpty()) { out.println("<p><b>" + msg + "</b></p>"); }
%>
<form action="adminLoginProcess.jsp" method="post">
<table>
<tr><td>Username:</td><td><input type="text" name="username" required></td></tr>
<tr><td>Password:</td><td><input type="password" name="password" required></td></tr>
<tr><td><input type="submit" value="Login"> &nbsp; <input type="button" value="Home" onclick="location.href='index.jsp'"></td></tr>
</table>
</form>
<hr>
<p><small>Admin Credentials: username = <b>admin</b> | password = <b>admin123</b></small></p>
</body>
</html>
