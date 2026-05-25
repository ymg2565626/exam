<%-- 成績参照JSP --%>
<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
	uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

	<c:param name="title">

		成績参照

	</c:param>

	<c:param name="content">

		<section class="me-4">

			<h2
				class="h3 mb-4 fw-normal bg-secondary bg-opacity-10 py-2 px-4">

				成績参照

			</h2>

			<div class="border rounded p-4">

				<!-- ====================== -->
				<!-- 科目検索 -->
				<!-- ====================== -->

				<form action="TestListSubjectExecute.action"
					method="post">

					<div
						class="row align-items-center mb-4">

						<div class="col-md-2">

							<h5 class="mb-0">
								科目情報
							</h5>

						</div>

						<div class="col-md-2">

							<label class="form-label">
								入学年度
							</label>

							<select name="f1"
								class="form-select">

								<option value="">
									--------
								</option>

								<c:forEach var="year"
									items="${ent_year_set}">

									<option value="${year}"
										<c:if test="${f1 == year}">
											selected
										</c:if>>

										${year}

									</option>

								</c:forEach>

							</select>

						</div>

						<div class="col-md-2">

							<label class="form-label">
								クラス
							</label>

							<select name="f2"
								class="form-select">

								<option value="">
									--------
								</option>

								<c:forEach var="num"
									items="${class_num_set}">

									<option value="${num}"
										<c:if test="${f2 == num}">
											selected
										</c:if>>

										${num}

									</option>

								</c:forEach>

							</select>

						</div>

						<div class="col-md-4">

							<label class="form-label">
								科目
							</label>

							<select name="subject"
								class="form-select">

								<option value="">
									--------
								</option>

								<c:forEach var="sub"
									items="${subject_set}">

									<option value="${sub.cd}"
										<c:if test="${subject == sub.cd}">
											selected
										</c:if>>

										${sub.name}

									</option>

								</c:forEach>

							</select>

						</div>

						<div class="col-md-2">

							<button type="submit"
								class="btn btn-secondary w-100 mt-4">

								検索

							</button>

						</div>

					</div>

				</form>

				<hr>

				<!-- ====================== -->
				<!-- 学生検索 -->
				<!-- ====================== -->

				<form action="TestListStudentExecute.action"
					method="post">

					<div
						class="row align-items-center">

						<div class="col-md-2">

							<h5 class="mb-0">
								学生情報
							</h5>

						</div>

						<div class="col-md-4">

							<label class="form-label">
								学生番号
							</label>

							<input type="text"
								name="student_no"
								value="${student_no}"
								class="form-control"
								placeholder="学生番号を入力してください">

						</div>

						<div class="col-md-2">

							<button type="submit"
								class="btn btn-secondary w-100 mt-4">

								検索

							</button>

						</div>

					</div>

				</form>
				
				<div class="mt-4 small text-info fw-semibold">

					科目情報を選択または学生情報を入力して検索ボタンをクリックしてください

				</div>

				<!-- エラー -->

				<c:if test="${not empty subject_error}">

					<div
						class="alert alert-warning mt-4">

						${subject_error}

					</div>

				</c:if>

				<c:if test="${not empty student_error}">

					<div
						class="alert alert-warning mt-4">

						${student_error}

					</div>

				</c:if>

				<!-- ====================== -->
				<!-- 検索結果 -->
				<!-- ====================== -->

				<div class="mt-4">

					<c:choose>

						<c:when test="${search_type == 'subject'}">

							<c:import url="test_list_subject.jsp" />

						</c:when>

						<c:when test="${search_type == 'student'}">

							<c:import url="test_list_student.jsp" />

						</c:when>

					</c:choose>

				</div>

			</div>

		</section>

	</c:param>

</c:import>
