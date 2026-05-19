package scoremanager.main;

import java.util.List;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.SubjectDAO;
import dao.TestDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectAction extends Action {

	@Override
	public void execute(
			HttpServletRequest req,
			HttpServletResponse res)
			throws Exception {

		HttpSession session =
				req.getSession();

		Teacher teacher =
				(Teacher) session.getAttribute("user");

		int entYear =
				Integer.parseInt(
						req.getParameter("f1"));

		String classNum =
				req.getParameter("f2");

		String subjectCd =
				req.getParameter("subject");

		SubjectDAO subjectDao =
				new SubjectDAO();

		Subject subject =
				subjectDao.get(
						subjectCd,
						teacher.getSchool());

		TestDAO testDao =
				new TestDAO();

		List<Test> list =
				testDao.filter(
						entYear,
						classNum,
						subject,
						1,
						teacher.getSchool());

		req.setAttribute(
				"testList",
				list);

		req.setAttribute(
				"subjectName",
				subject.getName());

		req.getRequestDispatcher(
				"test_list_subject.jsp")
				.forward(req, res);
	}
}