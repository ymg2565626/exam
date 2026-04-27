<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>学生一覧</title>
</head>
<body>

<h2>学生一覧</h2>

<table border="1">
<tr>
    <th>学籍番号</th>
    <th>名前</th>
    <th>入学年</th>
    <th>クラス</th>
</tr>

<c:forEach var="s" items="${studentList}">
<tr>
    <td>${s.no}</td>
    <td>${s.name}</td>
    <td>${s.entYear}</td>
    <td>${s.classNum}</td>
</tr>
</c:forEach>


</table>

<a href="menu.jsp">メニューに戻る</a>

</body>
</html>