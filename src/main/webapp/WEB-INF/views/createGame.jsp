<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%> <%@ taglib prefix = "c" uri =
        "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Create game</title>

</head>
<body>
    <h3>Create game</h3>

<form:form method="post" modelAttribute="game">
    Short description: <form:textarea path="description"/>
    <input type="submit" value="Create"/>
</form:form>

</body>
</html>
