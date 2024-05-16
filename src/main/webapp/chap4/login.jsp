<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h2>login</h2>
    <hr>
    <form action="/login" method="post">
        id : <input type="text" name="id" size="10px">
        pw : <input type="password" name="pw" size="10px">
        <input type="submit" value="login">
    </form>
</body>
</html>