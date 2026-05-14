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

public class SubjectUpdateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");
		String cd = req.getParameter("cd");
		String name = req.getParameter("name");
		Subject subject = new Subject();
		SubjectDAO subjectDao = new SubjectDAO();
		Map<String, String> errors = new HashMap<>();

		subject.setCd(cd);
		subject.setName(name);
		subject.setSchool(teacher.getSchool());

		if (subjectDao.get(cd, teacher.getSchool()) == null) {
			errors.put("1", "科目が存在していません");
			req.setAttribute("subject", subject);
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("subject_update.jsp").forward(req, res);
			return;
		}

		subjectDao.save(subject);

		req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);
	}
}

