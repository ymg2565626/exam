package scoremanager.main;

import java.util.List;

import bean.Student;
import bean.Teacher;
import bean.Test;
import dao.StudentDAO;
import dao.TestDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListStudentAction extends Action {

	@Override
	public void execute(
			HttpServletRequest req,
			HttpServletResponse res)
			throws Exception {

		HttpSession session =
				req.getSession();

		Teacher teacher =
				(Teacher) session.getAttribute("user");

		String studentNo =
				req.getParameter(
						"student_no");

		StudentDAO studentDao =
				new StudentDAO();

		Student student =
				studentDao.get(studentNo);

		TestDAO testDao =
				new TestDAO();

		List<Test> testList =
				testDao.filter(
						student.getEntYear(),
						student.getClassNum(),
						null,
						0,
						teacher.getSchool());

		req.setAttribute(
				"testList",
				testList);

		req.getRequestDispatcher(
				"test_list_student.jsp")
				.forward(req, res);
	}
}