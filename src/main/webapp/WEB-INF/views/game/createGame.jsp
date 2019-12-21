<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%> <%@ taglib prefix = "c" uri =
        "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Create game</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $( function() {
            $( "#datepicker" ).datepicker();
        } );
    </script>
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
    <script>
        $(document).ready(function(){
            $('#timepicker').timepicker({
                timeFormat: 'HH:mm',
                interval: 15,
                minTime: '0',
                maxTime: '23:45',
                defaultTime: '0',
                startTime: '0',
                dynamic: true,
                dropdown: true,
                scrollbar: true
            });
        });
    </script>
</head>
<body>
    <h3>Create game</h3>

<form:form method="post" modelAttribute="game">
    Short description: <form:textarea path="description"/><br>
    <form:errors path="description"/>
    Date: <form:input path="gameDate" id="datepicker"/><br>
    <form:errors path="gameDate"/>
    Start time: <form:input path="startTime" id="timepicker"/><br>
    <form:errors path="startTime"/>
    GameTime: <form:input path="gameTime"/><br>
    <form:errors path="gameTime"/>
    Price per player: <form:input path="pricePerPlayer"/><br>
    <form:errors path="pricePerPlayer"/>
    Max number of player: <form:input path="maxPlayers"/><br>
    <form:errors path="maxPlayers"/>
    Pitch: <form:select path="pitch" items="${pitches}"/> <a href="/pitch/add">Add new Pitch</a> <br>
    <form:errors path="pitch"/>
    <input type="submit" value="Create"/>
</form:form>
<a href="/user/desktop">To User Desktop</a>
</body>
</html>
