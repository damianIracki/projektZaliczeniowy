<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%> <%@ taglib prefix = "c" uri =
        "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Banned}</title>
</head>
<body>
<c:choose>
    <c:when test="${user.status == '8'}">
        <h3 style="color: red">User ${user.userName} was BANNED!</h3>
    </c:when>
    <c:when test="${user.status == '0'}">
        <h3 style="color:green" >User ${user.userName} was UNBANNED!</h3>
    </c:when>
    <c:otherwise>
        <h1>UNKNOWN ACTION</h1>
    </c:otherwise>
</c:choose>
<a href="/admin/allUsers ">Go back</a>
</body>
</html>
