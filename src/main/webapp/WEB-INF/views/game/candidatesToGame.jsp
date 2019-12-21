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
            <c:choose>
                <c:when test="${game.available == 'true'}">
                    <td><a href = "/game/candidate/accept/${candidate.id}">Accept</a> ${game.available}</td>
                </c:when>
                <c:otherwise>
                    <td>FULL</td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
</table>
<a href="/game/myGames">Back to myGames</a>
</body>
</html>
