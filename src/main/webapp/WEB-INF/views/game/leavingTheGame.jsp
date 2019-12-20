<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%> <%@ taglib prefix = "c" uri =
        "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>LeavingSuccessfully</title>
</head>
<body>
    You successfully leave the game ${game.description}!<br>
<a href="/game/acceptedGames">Back to game list</a>
</body>
</html>
