<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>News List</title>
    <%--    부트 스트랩, css , javascript library --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
            crossorigin="anonymous"></script>
</head>
<body>
<div>
    <h2>News List</h2>
    <hr>
    <table>
        <thead>
        <div container w-75 mt-5 mx-auto>
            <tr>
                <th>id</th>
                <th>title</th>
                <th>time</th>
                <th>delete</th>
            </tr>
        </div>
        </thead>
        <tbody>
        <c:forEach var="news" items="${newsList}" varStatus="status">
            <tr>
                <td class="list-group-item list-group-action d-flex justify-content-between align-item-center"> ${status.count}</td>
                <td><a href="/news.nhn?action=getNews&aid=${news.aid}" class="text-decoration-none">${news.title}</a>
                </td>
                <td>${news.date}</td>
                <td><a href="/news.nhn?action=deleteNews&aid=${news.aid}"><span
                        class="badge bg-secondary">&times;</span></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<hr>
<c:if test="${error != null}">
    <div class="alert alert-danger alert-dismissible fade show mt-3">
        error 발생 : ${error}
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>
</c:if>
<button class="btn btn-outline-info mb-3" type="button" data-bs-toggle="collapse" data-bs-target="#addForm"
        aria-expanded="false" aria-controls="addForm">뉴스 등록
</button>
<div class="collapse" id="addForm">
    <div class="card card-body">
        <form action="/news.nhn?action=addNews" enctype="multipart/form-data" method="post">
            <label for="title" class="form-label">제목</label>
            <input type="text" id="title" name="title" class="form-control">
            <label for="img" class="form-label">이미지</label>
            <input type="file" id="img" name="img" class="form-control">
            <label for="content" class="form-label">기사내용</label>
            <textarea name="content" id="content" cols="30" rows="10" class="form-control"></textarea>
            <button type="submit" class="btn btn-success mt-3">저장</button>
        </form>
    </div>
</div>
</body>
</html>