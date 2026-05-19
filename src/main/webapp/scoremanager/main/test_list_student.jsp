<%-- 学生成績参照JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

	<c:param name="title">
		学生別成績参照
	</c:param>

	<c:param name="content">

		<section class="me-4">

			<h2 class="h3 mb-4 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				学生別成績参照
			</h2>

			<c:choose>

				<c:when test="${testList.size() > 0}">

					<table class="table table-hover">

						<tr>

							<th>
								科目名
							</th>

							<th>
								科目コード
							</th>

							<th>
								回数
							</th>

							<th>
								点数
							</th>

						</tr>

						<c:forEach var="test" items="${testList}">

							<tr>

								<td>
									${test.subject.name}
								</td>

								<td>
									${test.subject.cd}
								</td>

								<td>
									${test.no}
								</td>

								<td>
									${test.point}
								</td>

							</tr>

						</c:forEach>

					</table>

				</c:when>

				<c:otherwise>

					<div>
						成績情報が存在しませんでした
					</div>

				</c:otherwise>

			</c:choose>

		</section>

	</c:param>

</c:import>