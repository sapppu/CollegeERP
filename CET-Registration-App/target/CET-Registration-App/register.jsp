<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Student Registration - CET</title>
    </head>

    <body>

        <h1>Student Registration</h1>
        <hr>

        <form action="registerProcess.jsp" method="post">
            <table>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="name" size="30" required></td>
                </tr>
                <tr>
                    <td>Course:</td>
                    <td>
                        <select name="course">
                            <option value="BSc CS">BSc CS</option>
                            <option value="BSc IT">BSc IT</option>
                            <option value="BCA">BCA</option>
                            <option value="MCA">MCA</option>
                            <option value="BE Computer">BE Computer</option>
                            <option value="ME Computer">ME Computer</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Date of Birth:</td>
                    <td><input type="date" name="dob" required></td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="Register">
                        &nbsp;
                        <input type="button" value="Home" onclick="location.href='index.jsp'">
                    </td>
                </tr>
            </table>
        </form>

    </body>

    </html>