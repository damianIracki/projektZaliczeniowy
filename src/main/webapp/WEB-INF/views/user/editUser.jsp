<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%> <%@ taglib prefix = "c" uri =
        "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Edit profile</title>
</head>
<body>
<h3>Edit your profile:</h3>
    <form:form method="post" modelAttribute="user">
        UserName: <form:input path="userName"/>
        <form:errors path="userName"/><br>
        Email: <form:input path="email"/>
        <form:errors path="email"/><br>
        <form:hidden path="password"/>
        <form:hidden path="repeatedPassword"/>
        Enter your password: <input name = "passwordTest" type="password"/><br>
        <input type="submit" value="save"/>
        <form:errors path="*"/>
    </form:form>
<a href="/user/desktop">Back to desktop</a>
</body>
</html>
