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
        Name: <form:input path="name"/>
        Address: <form:input path="address"/>
        <input type="submit" value="save"/>
    </form:form>
</body>
</html>
