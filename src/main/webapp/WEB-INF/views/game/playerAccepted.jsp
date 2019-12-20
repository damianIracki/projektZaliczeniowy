<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%> <%@ taglib prefix = "c" uri =
        "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Player Accepted</title>
</head>
<body>
    <h3>Player ${player.userName} was added to game</h3>
    <a href = "/game/candidates/${game.id}">Back to candidate list</a>
</body>
</html>
