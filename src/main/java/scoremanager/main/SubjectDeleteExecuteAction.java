package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");
		String cd = req.getParameter("cd");
		Subject subject = new Subject();
		SubjectDAO subjectDao = new SubjectDAO();

		subject.setCd(cd);
		subject.setSchool(teacher.getSchool());

		subjectDao.delete(subject);

		req.getRequestDispatcher("SubjectList.action").forward(req, res);
	}
}
