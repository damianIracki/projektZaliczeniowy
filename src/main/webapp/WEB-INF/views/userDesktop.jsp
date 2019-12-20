<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%> <%@ taglib prefix = "c" uri =
        "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Hello ${user.userName}</title>
</head>
<body>
<h1>Welcome ${user.userName}</h1>
<a href="/game/create">Create Game</a>
<a href="/game/gamesToJoin">Joint to the Game</a>
<a href="/game/myGames">My Games</a>
<a href="/game/acceptedGames">Accepted Games</a><br><br>

<a href="/user/edit">Edit Profile</a>
<a href="/user/changePassword">Change Password</a>
<a href="/logout">Logout</a><br>

<h3>Five earliest games</h3>
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
    <c:forEach items="${games}" var = "game">
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
</table>
</body>
</html>
