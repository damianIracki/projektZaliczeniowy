<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%> <%@ taglib prefix = "c" uri =
        "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Players</title>
</head>
<body>
<h3>${game.description}</h3>
<ol>
    <c:forEach items="${players}" var="player">
            <li>${player.userName}</li>
    </c:forEach>
</ol>
<a href="/game/myGames">Back to myGames</a>
</body>
</html>
