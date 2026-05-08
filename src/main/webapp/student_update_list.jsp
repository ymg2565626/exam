<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<h2>変更する学生を選択</h2>

<table border="1">
<tr>
    <th>学籍番号</th>
    <th>名前</th>
    <th>操作</th>
</tr>

<c:forEach var="s" items="${studentList}">
<tr>
    <td>${s.no}</td>
    <td>${s.name}</td>
    <td>
        <a href="StudentUpdate.do?no=${s.no}">選択</a>
    </td>
</tr>
</c:forEach>

</table>

<br>
<a href="menu.jsp">メニューに戻る</a>
