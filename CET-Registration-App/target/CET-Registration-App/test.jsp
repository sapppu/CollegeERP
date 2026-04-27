<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>CET Test</title></head>
<body>
<%
String regNo = (String) session.getAttribute("regNo");
if (regNo == null) { response.sendRedirect("index.jsp?msg=Please+login+to+take+the+test"); return; }
%>
<h1>CET Test</h1>
<hr>
<p>Attempt all questions. Each correct answer carries 1 mark.</p>
<form action="submitTest.jsp" method="post">
<h3>Question 1</h3>
<p>a = 12, &nbsp; b = 13, &nbsp; res = a + b</p>
<p><b>res = ?</b></p>
<input type="text" name="ans1" size="10" placeholder="Enter answer" required>
<hr>
<h3>Question 2</h3>
<p>a = 11, &nbsp; b = 22, &nbsp; c = b / a</p>
<p><b>c = ?</b></p>
<input type="text" name="ans2" size="10" placeholder="Enter answer" required>
<hr>
<input type="submit" value="Submit Test">
&nbsp;
<input type="button" value="Back to Dashboard" onclick="location.href='dashboard.jsp'">
</form>
</body>
</html>
