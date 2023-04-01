<%@ page import="java.util.List" %>
<%@ page import="kz.iitu.libraryapp.core.book.Book" %><%--
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
    <form action="/books" method="post">
        <input type="text" name="title" placeholder="Book title" width="100px">
        <input type="text" name="author" placeholder="Book author" width="100px">
        <input type="text" name="isbn" placeholder="Book ISBN" width="100px">
        <input type="text" name="year" placeholder="Book year (ex. 2002)" width="100px">
        <input type="text" name="quantity" placeholder="Book quantity (ex. 5)" width="100px">
        <button type="submit" style="width: 100px">Submit</button>
    </form>
</div>
<div style="margin-top: 20px; margin-bottom: 25px" align="center">
    <form action="/books" method="get">
        <input type="text" name="isbnFilter" placeholder="Filter by ISBN" width="100px">
        <input type="text" name="authorFilter" placeholder="Filter by author" width="100px">
        <button type="submit" style="width: 100px">Filter</button>
        <a href="/books">Reset filters</a>
    </form>
</div>
<table border ="1" style="width: 80%" align="center">
    <tr bgcolor="00FF7F">
        <th><b>Book ID</b></th>
        <th><b>Book Title</b></th>
        <th><b>Book Author</b></th>
        <th><b>Book ISBN</b></th>
        <th><b>Book Year</b></th>
        <th><b>Book Quantity</b></th>
    </tr>
    <%List<Book> books =
            (List<Book>)request.getAttribute("books");
        for(Book b : books){%>
    <tr>
        <td><%=b.getId()%></td>
        <td><%=b.getTitle()%></td>
        <td><%=b.getAuthor()%></td>
        <td><%=b.getIsbn()%></td>
        <td><%=b.getYear()%></td>
        <td><%=b.getQuantity()%></td>
    </tr>
    <%}%>
</table>
<div>
    <ul style="margin-left: auto; margin-right: auto">
        <li style="display: inline; margin-right: 5px">BOOKS</li>
        <li style="display: inline; margin-right: 5px""><a href="students">STUDENTS</a></li>
        <li style="display: inline; margin-right: 5px""><a href="leasing">LEASING</a></li>
    </ul>
</div>
</body>
</html>
