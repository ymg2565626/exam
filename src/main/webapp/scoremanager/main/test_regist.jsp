<%-- 成績登録JSP --%>
<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

	<c:param name="title">
		成績登録
	</c:param>

	<c:param name="content">

		<section class="me-4">

			<h2 class="h3 mb-4 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				成績登録
			</h2>

			<!-- ===================== -->
			<!-- 検索フォーム -->
			<!-- ===================== -->

			<form action="TestRegist.action" method="post">

				<div class="row border mx-3 mb-4 py-3 align-items-end rounded">

					<div class="col-3">

						<label class="form-label">
							入学年度
						</label>

						<select name="f1" class="form-select">

							<option value="">
								--------
							</option>

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

							<option value="">
								--------
							</option>

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

							<option value="">
								--------
							</option>

							<c:forEach var="subject" items="${subject_set}">

								<option value="${subject.cd}">
									${subject.name}
								</option>

							</c:forEach>

						</select>

					</div>

					<div class="col-2">

						<label class="form-label">
							回数
						</label>

						<select name="no" class="form-select">

							<option value="1">1回</option>
							<option value="2">2回</option>
							<option value="3">3回</option>

						</select>

					</div>
					
					<c:if test="${not empty error}">
						<div class="alert alert-warning">
							${error}
						</div>
					</c:if>

					<div class="col-1">

						<button type="submit"
							class="btn btn-secondary">

							検索

						</button>

					</div>

				</div>

			</form>

			<!-- ===================== -->
			<!-- 検索結果 -->
			<!-- ===================== -->

			<c:if test="${not empty students}">

				<form action="TestRegistExecute.action"
					method="post">

					<!-- hidden -->
					<input type="hidden"
						name="f1"
						value="${param.f1}">

					<input type="hidden"
						name="f2"
						value="${param.f2}">

					<input type="hidden"
						name="subject"
						value="${param.subject}">

					<input type="hidden"
						name="no"
						value="${param.no}">

					<h3 class="mb-3">

						科目：${subject_name}
						（第${no}回）

					</h3>

					<table class="table table-hover">

						<tr>

							<th>入学年度</th>
							<th>クラス</th>
							<th>学生番号</th>
							<th>氏名</th>
							<th>点数</th>

						</tr>

						<c:forEach var="student"
							items="${students}">

							<tr>

								<td>
									${student.entYear}
								</td>

								<td>
									${student.classNum}
								</td>

								<td>
									${student.no}
								</td>

								<td>
									${student.name}
								</td>

								<td>

									<input
										type="number"
										name="point_${student.no}"
										class="form-control"
										min="0"
										max="100"
										required
										step="1"
										oninput="checkScore(this)"
										value="<c:out value='${point_map[student.no]}'/>">

									<div class="text-danger small"
										id="error_${student.no}">
									</div>

								</td>

							</tr>

						</c:forEach>

					</table>

					<div class="mt-4">

						<button type="submit"
							class="btn btn-primary">

							登録して終了

						</button>

					</div>

				</form>

			</c:if>
			
			<script>

			function checkScore(input) {

				const value = parseInt(input.value);

					const error =
						document.getElementById(
						"error_" +
						input.name.replace("point_", "")
						);

						if (value < 0 || value > 100) {

						error.innerText =
							"0〜100の範囲で入力してください";

						} else {

						error.innerText = "";

						}
					}

			</script>

		</section>

	</c:param>

</c:import>
