<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="bean.Teacher" %>

<html>
<head>
    <title>ログイン結果</title>
</head>
<body>

<%
    Teacher teacher = (Teacher)request.getAttribute("teacher");

    if (teacher != null) {
%>
        <h2>ログイン成功</h2>
        名前：<%= teacher.getName() %><br>
        ID：<%= teacher.getId() %>
<%
    } else {
%>
        <h2>ログイン失敗</h2>
        IDまたはパスワードが違います
<%
    }
%>

</body>
</html>