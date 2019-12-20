<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%> <%@ taglib prefix = "c" uri =
        "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Pitch list</title>
</head>
<body>
<table>
    <thead>
    <td>Pitch name</td>
    <td>Pitch address</td>
    <td>Pitch type</td>
    </thead>
    <c:forEach items="${pitches}" var = "pitch">
        <tr>
            <td>${pitch.name}</td>
            <td>${pitch.address}</td>
            <td>${pitch.type}</td>
        </tr>
    </c:forEach>
</table>
<a href="/user/desktop">Back to desktop</a>
</body>
</html>
