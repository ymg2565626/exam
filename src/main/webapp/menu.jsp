<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="bean.Teacher" %>
<%
    Teacher teacher = (Teacher) session.getAttribute("user");

    if (teacher == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
<head>
    <title>メニュー</title>
</head>
<body>

<h2>メニュー画面</h2>

<p style="color: rgb(0%,50%,0%);">
ログイン中 : <%= teacher.getName() %>
</p>

<hr>

<h3>学生管理</h3>
<ul>
    <li><a href="StudentList.do">学生一覧</a></li>
    <li><a href="StudentCreate.do">学生登録</a></li>
    <li><a href="StudentUpdate.do">学生変更</a></li>
</ul>

<hr>

<form action="logout.do" method="post">
    <input type="submit" value="ログアウト">
</form>

</body>
</html>