<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>学生登録</title>
</head>
<body>

<h2>学生登録</h2>

<form action="StudentCreateExecute.do" method="post">

学籍番号：<input type="text" name="no"><br>
名前：<input type="text" name="name"><br>
入学年：<input type="text" name="entYear"><br>

クラス：
<select name="classNum">
<c:forEach var="c" items="${classNumList}">
    <option value="${c.classNum}">${c.classNum}</option>
</c:forEach>
</select>
<br>

在学：
<select name="isAttend">
    <option value="true">在学</option>
    <option value="false">卒業</option>
</select>
<br>

<input type="submit" value="登録">

</form>

<a href="menu.jsp">メニューに戻る</a>

</body>
</html>