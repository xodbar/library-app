<%@ page import="java.util.List" %>
<%@ page import="kz.iitu.libraryapp.core.book.Book" %>
<%@ page import="kz.iitu.libraryapp.core.student.Student" %>
<%@ page import="kz.iitu.libraryapp.core.BookLeasingDTO" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 25.02.2023
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books</title>
</head>
<body>
<h1 style="text-align: center">Books List</h1>
<div style="margin-top: 20px; margin-bottom: 25px" align="center">
    <form action="/leasing" method="post">
        <select name="studentId" id="studentId" style="width: 100px;">
            <%List<Student> students =
                    (List<Student>)request.getAttribute("students");
                for(Student s : students){%>
            <option value="<%=s.getId()%>"><%=s.getSurname() + " " + s.getName() + " (" + s.getGroup() + ")"%></option>
            <%}%>
        </select>
        <select name="bookId" id="bookId" style="width: 100px;">
            <%List<Book> books =
                    (List<Book>)request.getAttribute("availableBooks");
                for(Book b : books){%>
            <option value="<%=b.getId()%>"><%=b.getTitle() + " (" + b.getAuthor() + ")"%></option>
            <%}%>
        </select>
        <button type="submit" style="width: 100px">Submit</button>
    </form>
</div>
<div>
    <h3 style="color: red"><%=request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : ""%></h3>
</div>
<table border ="1" style="width: 80%" align="center">
    <tr bgcolor="00FF7F">
        <th><b>Leasing ID</b></th>
        <th><b>Leasing Student</b></th>
        <th><b>Leasing Book</b></th>
    </tr>
    <%List<BookLeasingDTO> leasingList =
            (List<BookLeasingDTO>)request.getAttribute("leasingList");
        for(BookLeasingDTO l : leasingList){%>
    <tr>
        <td><%=l.getLeasingId()%></td>
        <td><%=l.getStudentFullName()%></td>
        <td><%=l.getBookName()%></td>
        <td>
            <form action="/removeLeasing" method="post">
                <input type="hidden" name="leasingId" value="<%=l.getLeasingId()%>">
                <button type="submit">RETURN BOOK</button>
            </form>
        </td>
    </tr>
    <%}%>
</table>
<div>
    <ul style="margin-left: auto; margin-right: auto">
        <li style="display: inline; margin-right: 5px"><a href="books">BOOKS</a></li>
        <li style="display: inline; margin-right: 5px"><a href="students">STUDENTS</a></li>
        <li style="display: inline; margin-right: 5px">LEASING</li>
    </ul>
</div>
</body>
</html>
