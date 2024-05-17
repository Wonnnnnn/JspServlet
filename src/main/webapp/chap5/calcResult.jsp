<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>계산기 controller</title>
    <% Long result =(Long)request.getAttribute("result"); %>
</head>
<body>
    <h3>계산 Controller 결과</h3>
    <hr>
<%--    Result : ${resultEL}--%>
    Result : <%= result %>
</body>
</html>