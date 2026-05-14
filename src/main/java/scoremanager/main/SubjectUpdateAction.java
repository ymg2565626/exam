package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");
		String cd = req.getParameter("cd");
		SubjectDAO subjectDao = new SubjectDAO();
		Map<String, String> errors = new HashMap<>();

		Subject subject = subjectDao.get(cd, teacher.getSchool());
		if (subject == null) {
			subject = new Subject();
			subject.setCd(cd);
			errors.put("1", "科目が存在していません");
		}

		req.setAttribute("subject", subject);
		req.setAttribute("errors", errors);

		req.getRequestDispatcher("subject_update.jsp").forward(req, res);
	}
}
