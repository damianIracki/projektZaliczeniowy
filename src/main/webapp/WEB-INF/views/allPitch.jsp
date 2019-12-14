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
    <c:forEach items="${pitches}" var = "pitch">
        <p>${pitch.name}    ${pitch.address}</p>
    </c:forEach>
</body>
</html>
