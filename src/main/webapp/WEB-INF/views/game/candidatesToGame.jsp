<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%> <%@ taglib prefix = "c" uri =
        "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Candidates</title>
</head>
<body>
<h3>${game.description}</h3>
<p>Users are displayed by joined date</p>
<table>
    <thead>
    <td>Username</td>
    </thead>
    <c:forEach items="${candidates}" var="candidate">
        <tr>
            <td>${candidate.userName}</td>
            <td><a href = "/candidate/accept/${candidate.id}">Accept</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
