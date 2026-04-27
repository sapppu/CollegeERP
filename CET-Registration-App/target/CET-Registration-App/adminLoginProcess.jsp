<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp" %>
<%
String username = request.getParameter("username");
String password = request.getParameter("password");
if ("admin".equals(username) && "admin123".equals(password)) {
    session.setAttribute("adminLoggedIn", Boolean.TRUE);
    session.setAttribute("userType", "admin");
    response.sendRedirect("adminDashboard.jsp");
} else {
    response.sendRedirect("adminLogin.jsp?msg=Invalid+admin+credentials");
}
%>
