<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <%! String[] members= {"a","b","c","d"};
        int num1 = 10;
        int calc(int num2) {
            return num1+num2;
        }
    %>
</head>
<body>
    <h3>1. 주석</h3>
    <!-- html 주석: 화면에서는 안보이고, 소스보기에서는 보인다-->
    <%-- jsp 주석 : 화면과 소스보기에서 둘다 볼 수 없다 --%>

    <h3>2. 변수와 함수 선언</h3>
    계산 결과 calc(10) <%=calc(10)%>

    <h3>3. hello.jsp 가져오기</h3>
    <%@ include file="../hello.jsp" %>

    <h3>4. 스크립트릿(배열 데이터 출력하기)</h3>
    <ul>
        <%  for(String member : members) { %>
            <li><%= member%></li>
        <% } %>
    </ul>
</body>
</html>