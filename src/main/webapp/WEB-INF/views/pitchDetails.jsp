<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%> <%@ taglib prefix = "c" uri =
        "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>${pitch.name} Details</title>
</head>
<body>
    <p>Name: ${pitch.name}</p>
    <p>Address: ${pitch.address}</p>
    <p>Type: ${pitch.type}</p>
<a href="/user/desktop">To User Desktop</a>
<a href="/user/desktop">To Game List</a>
</body>
</html>
