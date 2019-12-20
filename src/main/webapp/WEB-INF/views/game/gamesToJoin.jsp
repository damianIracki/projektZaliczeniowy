<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%> <%@ taglib prefix = "c" uri =
        "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Games to Join</title>
</head>
<body>
<h3>Games are sorted by start date</h3>
<h3>Game to joined</h3>
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
    <c:forEach items="${gamesToJoined}" var = "game">
        <tr>
            <td>${game.gameDate}</td>
            <td>${game.startTime}</td>
            <td>${game.gameTime}</td>
            <td>${game.maxPlayer}</td>
            <td>${game.creator.userName}</td>
            <td><a href="/pitch/${game.pitch.id}">${game.pitch.name}</a></td>
            <td>${game.pricePerPlayer}</td>
            <td>${game.description}</td>
            <c:choose>
                <c:when test="${game.available=='true'}">
                    <td><a href="/game/join/${game.id}">JOIN</a></td>
                </c:when>
                <c:otherwise><td>FULL</td></c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
</table>

<h3>Game already joined</h3>
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
    <c:forEach items="${gamesAlreadyJoined}" var = "game">
        <tr>
            <td>${game.gameDate}</td>
            <td>${game.startTime}</td>
            <td>${game.gameTime}</td>
            <td>${game.maxPlayer}</td>
            <td>${game.creator.userName}</td>
            <td><a href="/pitch/${game.pitch.id}">${game.pitch.name}</a></td>
            <td>${game.pricePerPlayer}</td>
            <td>${game.description}</td>
        </tr>
    </c:forEach>
</table><br>

<a href="/user/desktop">Back to desktop</a>
</body>
</html>
