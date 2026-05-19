package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import dao.ClassNumDAO;
import dao.SubjectDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {

	@Override
	public void execute(
			HttpServletRequest req,
			HttpServletResponse res)
			throws Exception {

		HttpSession session =
				req.getSession();

		Teacher teacher =
				(Teacher) session.getAttribute("user");

		ClassNumDAO classNumDao =
				new ClassNumDAO();

		SubjectDAO subjectDao =
				new SubjectDAO();

		LocalDate today =
				LocalDate.now();

		int year =
				today.getYear();

		List<Integer> entYearSet =
				new ArrayList<>();

		for (int i = year - 10;
				i <= year + 10;
				i++) {

			entYearSet.add(i);
		}

		req.setAttribute(
				"class_num_set",
				classNumDao.filter(
						teacher.getSchool()));

		req.setAttribute(
				"subject_set",
				subjectDao.filter(
						teacher.getSchool()));

		req.setAttribute(
				"ent_year_set",
				entYearSet);

		req.getRequestDispatcher(
				"test_list.jsp")
				.forward(req, res);
	}
}