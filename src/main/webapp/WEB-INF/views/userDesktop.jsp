<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%> <%@ taglib prefix = "c" uri =
        "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Hello ${user.userName}</title>
</head>
<body>
<h1>Welcome ${user.userName}</h1>
<a href="/user/edit">Edit Profile</a>
<a href="/user/logout">Logout</a>
<a href="/user/changePassword">Change Password</a>
</body>
</html>
