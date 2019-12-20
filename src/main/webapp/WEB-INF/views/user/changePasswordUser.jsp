<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%> <%@ taglib prefix = "c" uri =
        "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Change password</title>
</head>
<body>
<h3>Change your password</h3>
    <form:form modelAttribute="user" method="post">
        Old password: <input type="password" name="oldPassword"><br>
        New password: <form:password path="password"/><br>
        Confirm password: <form:password path="repeatedPassword"/><br>
        <form:hidden path="userName"/>
        <form:hidden path="email"/>
        <input type="submit" value="Save">
        <form:errors path="*"/>
    </form:form>
<a href="/user/desktop">Back to desktop</a>
</body>
</html>
