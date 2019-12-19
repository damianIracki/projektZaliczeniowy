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

    <table>
        <thead>
        <td>Username</td>
        <td>Email</td>
        <td>Status</td>
        </thead>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.userName}</td>
                <td>${user.email}</td>
                <c:choose>
                    <c:when test="${user.status == 0}">
                        <td>USER</td>
                        <td><a href = "/admin/ban/${user.id}">BAN</a></td>
                    </c:when>
                    <c:when test="${user.status == '9'}">
                        <td>ADMIN</td>
                        <td>-----</td>
                    </c:when>
                    <c:when test="${user.status == '8'}">
                        <td>BANNED</td>
                        <td><a href="/admin/unban/${user.id}">UNBAN</a> </td>
                    </c:when>
                </c:choose>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
