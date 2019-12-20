<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%> <%@ taglib prefix = "c" uri =
        "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add User</title>
    <style>
        .error{
            color: orange;
        }
    </style>
</head>
<body>
<h3>Create new user</h3>
    <form:form method="post" modelAttribute="user">
        UserName: <form:input path="userName"/>
        <form:errors cssClass="error" path="userName"/><br>
        Password: <form:password path="password"/>
        <form:errors cssClass="error" path="password"/><br>
        ConfirmPassword: <form:password path="repeatedPassword"/>
        <form:errors cssClass="error"/><br>
        Email: <form:input path="email"/>
        <form:errors cssClass="error" path="email"/><br>
        <input type="submit" value="save"/>
    </form:form>
Do you already have an account? <a href="/login">Sign in</a><br>
<a href="/">Back to homepage</a>
</body>
</html>
