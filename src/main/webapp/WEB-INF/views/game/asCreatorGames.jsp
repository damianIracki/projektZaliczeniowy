<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%> <%@ taglib prefix = "c" uri =
        "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>My Games</title>
</head>
<body>
<table>
    <thead>
    <td>Game Date</td>
    <td>Start Time</td>
    <td>Game Time</td>
    <td>max number of players</td>
    <td>Creator</td>
    <td>Pitch</td>
    <td>Price per player</td>
    <td>Description</td>
    </thead>
    <c:forEach items="${myGames}" var = "game">
        <tr>
            <td>${game.gameDate}</td>
            <td>${game.startTime}</td>
            <td>${game.gameTime}</td>
            <td>${game.maxPlayer}</td>
            <td>${game.creator.userName}</td>
            <td><a href="/pitch/${game.pitch.id}">${game.pitch.name}</a></td>
            <td>${game.pricePerPlayer}</td>
            <td>${game.description}</td>
            <td><a href="/game/candidates/${game.id}">Candidates</a> </td>
            <td><a href="/game/players/${game.id}">Players</a></td>
            <c:choose>
                <c:when test="${game.available == 'false'}">
                    <td>FULL</td>
                </c:when>
                <c:otherwise><td>OPEN</td></c:otherwise>
            </c:choose>
            <td><a href="/game/cancel/${game.id}">Cancel</a> </td>
        </tr>
    </c:forEach>
</table>
<a href="/user/desktop">Back to desktop</a>
</body>
</html>
