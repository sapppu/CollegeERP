<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Patient Details</title>
    <style>
        table {
            width: 70%;
            border-collapse: collapse;
            margin: 20px auto;
            font-family: Arial, sans-serif;
        }
        th, td {
            border: 1px solid black;
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>

<h2 style="text-align: center;">Patient Details</h2>

<%
    // Creating a Patient class
    class Patient {
        int pNo;
        String pName;
        String address;
        int age;
        String disease;

        public Patient(int pNo, String pName, String address, int age, String disease) {
            this.pNo = pNo;
            this.pName = pName;
            this.address = address;
            this.age = age;
            this.disease = disease;
        }
    }

    // Sample Data
    List<Patient> patients = new ArrayList<>();
    patients.add(new Patient(101, "John Doe", "New York", 45, "Diabetes"));
    patients.add(new Patient(102, "Jane Smith", "California", 32, "Flu"));
    patients.add(new Patient(103, "David Johnson", "Texas", 28, "Cold"));
    patients.add(new Patient(104, "Emily Brown", "Florida", 50, "Hypertension"));
%>

<table>
    <tr>
        <th>PNo</th>
        <th>PName</th>
        <th>Address</th>
        <th>Age</th>
        <th>Disease</th>
    </tr>
    <%
        for (Patient p : patients) {
    %>
    <tr>
        <td><%= p.pNo %></td>
        <td><%= p.pName %></td>
        <td><%= p.address %></td>
        <td><%= p.age %></td>
        <td><%= p.disease %></td>
    </tr>
    <%
        }
    %>
</table>

</body>
</html>
