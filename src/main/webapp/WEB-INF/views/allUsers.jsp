<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%> <%@ taglib prefix = "c" uri =
        "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>All Users</title>
</head>
<body>
    <c:forEach items="${users}" var="user">
        <p>
            ${user.userName}
            ${user.email}
            ${user.status}
        </p>
    </c:forEach>
</body>
</html>
