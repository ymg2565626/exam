<%-- 科目別成績一覧JSP --%>
<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="mx-3">

	<c:choose>

		<%-- 成績情報がない場合 --%>
		<c:when test="${empty students}">

			<div class="alert alert-warning">
				学生情報が存在しませんでした
			</div>

		</c:when>

		<%-- 成績情報がある場合 --%>
		<c:otherwise>

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

						<td>

							<c:choose>

								<c:when test="${student.getPoint(1) == -1}">
									-
								</c:when>

								<c:otherwise>
									${student.getPoint(1)}
								</c:otherwise>

							</c:choose>

						</td>

						<td>

							<c:choose>

								<c:when test="${student.getPoint(2) == -1}">
									-
								</c:when>

								<c:otherwise>
									${student.getPoint(2)}
								</c:otherwise>

							</c:choose>

						</td>

					</tr>

				</c:forEach>

			</table>

		</c:otherwise>

	</c:choose>

</div>
