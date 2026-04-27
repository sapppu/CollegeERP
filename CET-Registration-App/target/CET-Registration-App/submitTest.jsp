<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@ page import="java.sql.*, com.cet.DBConnection" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Test Result - CET</title></head>
<body>
<h1>CET Test Result</h1>
<hr>
<%
String regNo = (String) session.getAttribute("regNo");
String studentName = (String) session.getAttribute("studentName");
if (regNo == null) { response.sendRedirect("index.jsp?msg=Please+login+first"); return; }
String ans1Str = request.getParameter("ans1");
String ans2Str = request.getParameter("ans2");
int marks = 0;
int c1 = 25, c2 = 2;
boolean q1 = false, q2 = false;
try { if (Integer.parseInt(ans1Str.trim()) == c1) { marks++; q1 = true; } } catch (NumberFormatException e) {}
try { if (Integer.parseInt(ans2Str.trim()) == c2) { marks++; q2 = true; } } catch (NumberFormatException e) {}
Connection conn = DBConnection.getConnection();
PreparedStatement ps = conn.prepareStatement("UPDATE students SET marks=? WHERE reg_no=?");
ps.setInt(1, marks); ps.setString(2, regNo);
ps.executeUpdate(); ps.close(); conn.close();
session.setAttribute("marks", marks);
out.println("<p>Student: <b>" + studentName + "</b> | Reg No: <b>" + regNo + "</b></p>");
out.println("<hr><h2>Answer Summary</h2>");
out.println("<table border='1' cellpadding='5'>");
out.println("<tr><th>Q No</th><th>Question</th><th>Your Answer</th><th>Correct</th><th>Result</th></tr>");
out.println("<tr><td>1</td><td>a=12,b=13,res=a+b,res=?</td><td>" + ans1Str + "</td><td>" + c1 + "</td><td>" + (q1?"Correct":"Wrong") + "</td></tr>");
out.println("<tr><td>2</td><td>a=11,b=22,c=b/a,c=?</td><td>" + ans2Str + "</td><td>" + c2 + "</td><td>" + (q2?"Correct":"Wrong") + "</td></tr>");
out.println("</table><br><h2>Total Marks: " + marks + " / 2</h2>");
%>
<hr>
<a href="dashboard.jsp">Back to Dashboard</a> &nbsp;&nbsp; <a href="logout.jsp">Logout</a>
</body>
</html>
