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
				<form action="TestListSubjectExecute.action"
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

				</form>

				<hr>

				<!-- 学生検索 -->
				<!-- 学生検索 -->
				<form action="TestListStudentExecute.action"
				    method="post">
				
				    <div class="row align-items-end">
				
				        <div class="col-6">
				            <label class="form-label">
				                学生番号
				            </label>
				
				            <input type="text"
				                name="studentNo"
				                class="form-control"
				                placeholder="学生番号を入力してください"
				                required>
				        </div>
				
				        <div class="col-2">
				            <button class="btn btn-secondary">
				                検索
				            </button>
				        </div>
				
				    </div>
				
				</form>
				
				<div class="mt-4 small text-info fw-semibold">

					科目情報を選択または学生情報を入力して検索ボタンをクリックしてください

				</div>
				
				<!-- ここに結果表示 -->
				<c:if test="${not empty test_list}">
				
				    <h4 class="mt-4">検索結果</h4>
				
				    <table class="table table-bordered">
				        <tr>
				            <th>科目名</th>
				            <th>科目コード</th>
				            <th>回数</th>
				            <th>点数</th>
				        </tr>
				
				        <c:forEach var="test" items="${test_list}">
				            <tr>
				                <td>${test.subjectName}</td>
				                <td>${test.subjectCd}</td>
				                <td>${test.num}</td>
				                <td>${test.point}</td>
				            </tr>
				        </c:forEach>
				
				    </table>
				
				</c:if>
				
				<c:if test="${searched and empty test_list}">
				    <p class="text-danger mt-3">
				        成績情報が存在しませんでした
				    </p>
				</c:if>

			</div>

		</section>

	</c:param>

</c:import>
