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

		HttpSession session =
				req.getSession();

		Teacher teacher =
				(Teacher)session.getAttribute("user");

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

		List<Test> testList =
				new ArrayList<>();

		for (Student student : students) {

			String pointStr =
					req.getParameter(
							"point_" + student.getNo());

			if (pointStr != null
					&& !pointStr.isEmpty()) {

				Test test =
						new Test();

				test.setStudent(student);

				test.setSubject(subject);

				test.setSchool(
						teacher.getSchool());

				test.setClassNum(classNum);

				test.setNo(no);

				test.setPoint(
						Integer.parseInt(pointStr));

				testList.add(test);
			}
		}

		// 保存
		testDao.save(testList);

		// 完了画面へ
		req.getRequestDispatcher(
				"test_regist_done.jsp")
				.forward(req, res);
	}
}