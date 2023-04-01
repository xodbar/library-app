<%@ page import="kz.iitu.libraryapp.core.student.Student" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 25.02.2023
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students</title>
</head>
<body>
<h1 style="text-align: center">Students List</h1>
<div style="margin-top: 20px; margin-bottom: 25px" align="center">
    <form action="/students" method="post">
        <input type="text" name="name" placeholder="Student name" width="100px">
        <input type="text" name="surname" placeholder="Student surname" width="100px">
        <input type="text" name="group" placeholder="Student group" width="100px">
        <button type="submit" style="width: 100px">Submit</button>
    </form>
</div>
<table border ="1" style="width: 80%" align="center">
    <tr bgcolor="00FF7F">
        <th><b>Student ID</b></th>
        <th><b>Student Name</b></th>
        <th><b>Student Surname</b></th>
        <th><b>Student Group</b></th>
    </tr>
    <%List<Student> students =
            (List<Student>)request.getAttribute("students");
        for(Student s : students){%>
    <tr>
        <td><%=s.getId()%></td>
        <td><%=s.getName()%></td>
        <td><%=s.getSurname()%></td>
        <td><%=s.getGroup()%></td>
    </tr>
    <%}%>
</table>
<div>
    <ul style="margin-left: auto; margin-right: auto">
        <li style="display: inline; margin-right: 5px""><a href="books">BOOKS</a></li>
        <li style="display: inline; margin-right: 5px"">STUDENTS</li>
        <li style="display: inline; margin-right: 5px""><a href="leasing">LEASING</a></li>
    </ul>
</div>
</body>
</html>
