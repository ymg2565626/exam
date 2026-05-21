<%-- 成績参照JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

	<c:param name="title">
		成績参照
	</c:param>

	<c:param name="content">

		<section class="me-4">

			<h2 class="h3 mb-4 fw-normal
				bg-secondary bg-opacity-10
				py-2 px-4">

				成績一覧（科目）

			</h2>

			<div class="border rounded p-4">

				<!-- 科目検索 -->
				<form action="TestListSubject.action"
					method="post">

					<div class="row align-items-end mb-4">

						<div class="col-3">

							<label class="form-label">
								入学年度
							</label>

							<select name="f1"
								class="form-select">

								<c:forEach var="year"
									items="${ent_year_set}">

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

							<select name="f2"
								class="form-select">

								<c:forEach var="num"
									items="${class_num_set}">

									<option value="${num}">
										${num}
									</option>

								</c:forEach>

							</select>

						</div>

						<div class="col-4">

							<label class="form-label">
								科目
							</label>

							<select name="subject"
								class="form-select">

								<c:forEach var="subject"
									items="${subject_set}">
		
									<option value="${subject.cd}">
										${subject.name}
									</option>

								</c:forEach>

							</select>

						</div>

						<div class="col-2">

							<button class="btn btn-secondary">
		
								検索

							</button>

						</div>

					</div>

				<form>

				<hr>

				<!-- 学生検索 -->
				<form action="TestListStudent.action"
					method="post">

					<div class="row align-items-end">

						<div class="col-6">

							<label class="form-label">
								学生番号
							</label>

							<input type="text"
								name="studentNo"
								class="form-control"
								placeholder="学生番号を入力してください">

						</div>

						<div class="col-2">

							<button class="btn btn-secondary">

								検索

							</button>

						</div>

					</div>

				</form>

			</div>

		</section>

	</c:param>

</c:import>
