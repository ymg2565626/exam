<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>ログイン</title>
</head>
<body>

<h2 style="color: rgb(0%,50%,0%);">ログイン</h2>

<form action="${pageContext.request.contextPath}/loginExecute.jsp" method="post">
    ID：<input type="text" name="id"><br>
    パスワード：<input type="password" name="password"><br>
    <input type="submit" value="ログイン">
</form>

</body>
</html>
