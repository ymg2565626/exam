
<%-- 科目別成績一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

	<c:param name="title">
		成績参照
	</c:param>

	<c:param name="content">

		<section class="me-4">

			<h2 class="h3 mb-4 fw-bold bg-secondary bg-opacity-10 py-2 px-4">
				成績一覧（科目）
			</h2>
			
			<div class="border rounded p-3 mb-3">

			<form action="TestListSubjectExecute.action" method="get">

				<div class="row mb-4 align-items-center">

				<div class="col-2 text-center">
					<label class="mb-0">
						科目情報
					</label>
				</div>
				
			
					<div class="col-2">

						<label class="form-label">
							入学年度
						</label>

						<select name="f1" class="form-select">

							<option value="">--------</option>

							<c:forEach var="year" items="${ent_year_set}">

								<option value="${year}">
									${year}
								</option>

							</c:forEach>

						</select>

					</div>

					<div class="col-3">

						<label class="form-label">
							クラス
						</label>

						<select name="f2" class="form-select">

							<option value="">--------</option>

							<c:forEach var="num" items="${class_num_set}">

								<option value="${num}">
									${num}
								</option>

							</c:forEach>

						</select>

					</div>

					<div class="col-3">

						<label class="form-label">
							科目
						</label>

						<select name="subject" class="form-select">

							<option value="">--------</option>

							<c:forEach var="subject" items="${subject_set}">

								<option value="${subject.cd}">
									${subject.name}
								</option>

							</c:forEach>

						</select>

					</div>

					<div class="col-2 d-flex align-items-end ">

						<button class="btn btn-secondary">
							検索
						</button>

					</div>

				</div>

			</form>
			<%--　検索条件不足エラー --%>
			<c:if test="${not empty error}">

   			 <div class="text-warning mb-3">
       			 ${error}
    		</div>
    
			<hr class="my-4">
			
			

			</c:if>

			<form action="TestListStudent.action" method="get">

				

				
				<div class="row mb-4 align-items-center">

				<div class="col-2 text-center">
					<label class="mb-0">
						学生情報
					</label>
				</div>

					<div class="col-4">

						<label class="form-label">
							学生番号
						</label>

						<input
							type="text"
							name="student_no"
							class="form-control"
							placeholder="学生番号を入力してください">

					</div>

					<div class="col-2 d-flex align-items-end ms-4">

						<button class="btn btn-secondary">
							検索
						</button>

					</div>

				</div>

			</form>
			</div>
			


			<%-- 一覧表示部分 --%>
		<c:choose>
			<%-- 成績情報がない場合 --%>
		<c:when test="${empty students}">

			<div class="mt-3">
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

		</section>

	</c:param>

</c:import>
