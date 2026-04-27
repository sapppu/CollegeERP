<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <% // --- Demonstrates: session implicit object (invalidating session on logout) --- session.invalidate(); // ---
        Demonstrates: response implicit object (redirect to home) ---
        response.sendRedirect("index.jsp?msg=You+have+been+logged+out+successfully"); %>