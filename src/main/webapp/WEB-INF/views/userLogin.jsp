<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%> <%@ taglib prefix = "c" uri =
        "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Sign in</title>
</head>
<body>
    <form method="post" action="login">
        userName: <input type="text" name = "userName"><br>
        Password: <input type = "password" name = "userPassword"><br>
        <input type="submit" value="Login"><br>
        <a href="/">Homepage</a>
    </form>
</body>
</html>
