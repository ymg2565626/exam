package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TestDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistExecuteAction extends Action {

	@Override
	public void execute(
			HttpServletRequest req,
			HttpServletResponse res)
			throws Exception {

		// セッション取得
		HttpSession session =
				req.getSession();

		Teacher teacher =
				(Teacher) session.getAttribute("user");

		// DAO
		StudentDAO studentDao =
				new StudentDAO();

		SubjectDAO subjectDao =
				new SubjectDAO();

		TestDAO testDao =
				new TestDAO();

		// パラメータ取得
		int entYear =
				Integer.parseInt(
						req.getParameter("f1"));

		String classNum =
				req.getParameter("f2");

		String subjectCd =
				req.getParameter("subject");

		int no =
				Integer.parseInt(
						req.getParameter("no"));

		// 科目取得
		Subject subject =
				subjectDao.get(
						subjectCd,
						teacher.getSchool());

		// 学生一覧取得
		List<Student> students =
				studentDao.filter(
						entYear,
						classNum,
						false,
						teacher.getSchool());

		// 保存用リスト
		List<Test> testList =
				new ArrayList<>();

		// 学生ごとに点数取得
		for (Student student : students) {

			String pointStr =
					req.getParameter(
							"point_" + student.getNo());

			// 未入力はスキップ
			if (pointStr != null
					&& !pointStr.isEmpty()) {

				Test test = new Test();

				test.setStudent(student);

				test.setSubject(subject);

				test.setSchool(
				    teacher.getSchool());

				test.setClassNum(
				    student.getClassNum());

				test.setNo((no));

				test.setPoint(
				    Integer.parseInt(pointStr));

				testList.add(test);
			}
		}

		// DB保存
		testDao.save(testList);

		// 完了画面
		req.getRequestDispatcher(
				"test_regist_done.jsp")
				.forward(req, res);
	}
}
