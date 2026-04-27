<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CET Registration System</title>
</head>
<body>

<h1>CET Registration System</h1>
<hr>

<!-- Navigation Buttons -->
<form action="register.jsp" method="get" style="display:inline">
    <input type="submit" value="New Registration">
</form>
&nbsp;&nbsp;
<form action="adminLogin.jsp" method="get" style="display:inline">
    <input type="submit" value="Admin Login">
</form>

<hr>

<!-- Student Login Form -->
<h2>Student Login</h2>

<%
    // Display any error/info messages passed via request parameter
    // Demonstrates: request implicit object
    String msg = request.getParameter("msg");
    if (msg != null && !msg.isEmpty()) {
        out.println("<p><b>[Message]: " + msg + "</b></p>");
    }
%>

<form action="loginProcess.jsp" method="post">
    <table>
        <tr>
            <td>Registration Number:</td>
            <td><input type="text" name="regNo" placeholder="e.g. CET1001" required></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password" required></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Login">
                &nbsp;
                <input type="button" value="Home" onclick="location.reload()">
            </td>
        </tr>
    </table>
</form>

<hr>
<p><small>Note: Your Registration Number is also your default password (e.g. CET1001).</small></p>

</body>
</html>
