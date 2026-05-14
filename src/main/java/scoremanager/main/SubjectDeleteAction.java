package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDeleteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");
		String cd = req.getParameter("cd");
		SubjectDAO subjectDao = new SubjectDAO();

		Subject subject = subjectDao.get(cd, teacher.getSchool());

		req.setAttribute("subject", subject);

		req.getRequestDispatcher("subject_delete.jsp").forward(req, res);
	}
}
