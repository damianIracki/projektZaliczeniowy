<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%> <%@ taglib prefix = "c" uri =
        "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>HomePage</title>
    <style>
        td{
            padding: 5px;
        }
    </style>
</head>
<body>
    <h1>Welcome to the application</h1>
    <a href="/add">Sign up</a>
    <a href="/login">Sign in</a><br><br>
    <a href="/pitch/list">Pitch list</a><br>

    <h3>Five earliest games</h3>
    <table border="1" style="border-collapse: collapse;padding: 5px;">
        <thead>
        <td>Game Date</td>
        <td>Start Time</td>
        <td>Game Time</td>
        <td>max number of players</td>
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
            <td>${game.pitch.name}</td>
            <td>${game.pricePerPlayer}</td>
            <td>${game.description}</td>
            <c:choose>
                <c:when test="${game.available=='true'}">
                    <td>OPEN (need sign in to join)</td>
                </c:when>
                <c:otherwise><td>FULL</td></c:otherwise>
            </c:choose>
        </tr>
        </c:forEach>
</body>
</html>
