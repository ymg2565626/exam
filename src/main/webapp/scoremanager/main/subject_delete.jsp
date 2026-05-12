<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>科目削除</title>
</head>
<body>

<h2>科目削除</h2>

<p>この科目を削除してもよろしいですか？</p>

<form action="SubjectDeleteExecute.action" method="post">

  学校コード：
  <input type="text" value="${subject.school.cd}" readonly>
  <br><br>

  科目コード：
  <input type="text" name="cd" value="${subject.cd}" readonly>
  <br><br>

  科目名：
  <input type="text" value="${subject.name}" readonly>
  <br><br>

  <input type="submit" value="削除">

</form>

<br>

<a href="SubjectList.action">戻る</a>

</body>
</html>
