<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 04.03.2023
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<div>
    <form action="/register" method="post" style="width: 100%; margin-left: auto; margin-right: auto">
        <div>
            <label for="username">Username</label>
            <input type="text" id="username" placeholder="New username" name="username">
        </div>
        <div>
            <label for="password">Password</label>
            <input type="password" id="password" placeholder="New password" name="password">
        </div>
        <div>
            <button type="submit">Register</button>
            <a href="/login">Already have account? Log In</a>
        </div>
        <div>
            <h3 style="color: red"><%=request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : ""%></h3>
        </div>
    </form>
</div>
</body>
</html>
