<%@ page contentType="text/html;charset=UTF-8" %>

<h2>学生変更</h2>

<form action="StudentUpdateExecute.jsp" method="post">

    学籍番号：<input type="text" name="no" value="${student.no}" readonly><br>

    名前：<input type="text" name="name" value="${student.name}"><br>

    入学年：<input type="text" name="entYear" value="${student.entYear}"><br>

    クラス：<input type="text" name="classNum" value="${student.classNum}"><br>

    在学：
    <select name="isAttend">
        <option value="true" ${student.attend ? "selected" : ""}>在学</option>
        <option value="false" ${!student.attend ? "selected" : ""}>卒業</option>
    </select><br>

    <input type="submit" value="更新">

</form>

<br>

<a href="StudentList.jsp">一覧に戻る</a>
