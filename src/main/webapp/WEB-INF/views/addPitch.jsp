<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%> <%@ taglib prefix = "c" uri =
        "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add pitch</title>
</head>
<body>
    <form:form method="post" modelAttribute="pitch">
        Name: <form:input path="name"/><br>
        Address: <form:input path="address"/><br>
        Type: <form:select path="type">
            <form:option value="baloon"/>
            <form:option value="open"/>
            <form:option value="hall"/>
        </form:select><br>
        <input type="submit" value="save"/><br>
    </form:form>
<a href="/user/desktop">Back to desktop</a>
</body>
</html>
