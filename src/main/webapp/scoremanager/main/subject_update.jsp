<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>科目変更</title>
</head>
<body>

<h2>科目変更</h2>

<form action="SubjectUpdateExecute.action" method="post">

  <p>
    学校コード：
    <input type="text" value="${subject.school.cd}" readonly>
  </p>

  <p>
    科目コード：
    <input type="text" name="cd" value="${subject.cd}" readonly>
  </p>

  <p>
    科目名：
    <input type="text" name="name" value="${subject.name}" maxlength="20">
  </p>

  <input type="submit" value="変更">

</form>

<p>
  <a href="SubjectList.action">戻る</a>
</p>

</body>
</html>
