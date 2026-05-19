<%-- 成績登録完了JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

	<c:param name="title">
		登録完了
	</c:param>

	<c:param name="content">

		<section class="me-4">

			<h2 class="h3 mb-4 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				登録完了
			</h2>

			<div class="alert alert-success">

				登録が完了しました

			</div>

			<div class="mt-4">

				<a href="TestRegist.action">

					戻る

				</a>

				<span class="mx-3"></span>

				<a href="TestList.action">

					成績参照

				</a>

			</div>

		</section>

	</c:param>

</c:import>