<%--科目別成績一覧JSP --%>
<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="mx-3">

	<div class="mb-2">
		科目：${subject.name}
	</div>

	<table class="table table-hover mb-0">

		<tr>
			<th>入学年度</th>
			<th>クラス</th>
			<th>学生番号</th>
			<th>氏名</th>
			<th>1回</th>
			<th>2回</th>
		</tr>

		<c:forEach var="student" items="${students}">

			<tr>

				<td>${student.entYear}</td>
				<td>${student.classNum}</td>
				<td>${student.studentNo}</td>
				<td>${student.studentName}</td>

				<td>${student.points[1]}</td>
				<td>${student.points[2]}</td>

			</tr>

		</c:forEach>

	</table>

</div>
