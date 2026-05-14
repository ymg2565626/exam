<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>成績変更</title>
</head>
<body>

<h2>成績変更</h2>

<form action="test/update" method="post">
	学生番号:
    <input type="text" name="no"><br><br>
	得点:
    <input type="text" name="point"><br><br>

    <input type="submit" value="更新">

</form>

</body>
</html>
