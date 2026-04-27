<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Error - CET</title></head>
<body>
<h1>An Error Occurred</h1>
<hr>
<%
if (exception != null) {
    out.println("<p><b>Error Type:</b> " + exception.getClass().getName() + "</p>");
    out.println("<p><b>Error Message:</b> " + exception.getMessage() + "</p>");
    out.println("<h3>Stack Trace:</h3><pre>");
    StackTraceElement[] frames = exception.getStackTrace();
    int limit = Math.min(5, frames.length);
    for (int i = 0; i < limit; i++) { out.println("  at " + frames[i].toString()); }
    out.println("</pre>");
} else {
    out.println("<p>An unknown error occurred or error page accessed directly.</p>");
}
%>
<hr>
<a href="index.jsp">Go to Home Page</a>
</body>
</html>
