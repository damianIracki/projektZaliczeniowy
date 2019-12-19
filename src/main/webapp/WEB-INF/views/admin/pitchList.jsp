<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%> <%@ taglib prefix = "c" uri =
        "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>PitchList</title>
</head>
<body>
<table>
    <thead>
    <td>Name</td>
    <td>Address</td>
    <td>Type</td>
    </thead>
    <c:forEach items="${pitches}" var = "pitch">
        <tr>
            <td>${pitch.name}</td>
            <td>${pitch.address}</td>
            <td>${pitch.type}</td>
            <td><a href="/admin/deletePitch/${pitch.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
