<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%> <%@ taglib prefix = "c" uri =
        "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Are you sure</title>
</head>
<body>
<h3>${game.description}</h3>
    <h3>Are you sure to cancel this game?</h3>
<a href="/game/cancel/${game.id}/accept">Yes</a>   <a href="/game/myGames">No</a>
</body>
</html>
