<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h2>login successful</h2>
    <hr>
    <%! String id; %>
    <% id = session.getAttribute("id").toString(); %>
    <% if(id.equals("")) { %>
        <p>please write down your id"</p>
    <% } else { %>
    <p>hello <%=id %> </p>
    <% } %>
</body>
</html>