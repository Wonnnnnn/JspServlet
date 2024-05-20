<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h2>Login</h2>
<hr>
<div>
    <form action="login">
        <label for="id" class="form-label">아이디</label>
        <input type="text" id="id" name="id" class="form-control">
        <label for="pw" class="form-label">비밀번호</label>
        <input type="password" id="pw" name="pw" class="form-control">
        <button type="submit">로그인</button>
    </form>
</div>
</body>
</html>