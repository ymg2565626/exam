package scoremanager.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDAO;
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TestDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistAction extends Action {

	@Override
	public void execute(
			HttpServletRequest req,
			HttpServletResponse res)
			throws Exception {

		// セッション取得
		HttpSession session = req.getSession();

		Teacher teacher =
				(Teacher) session.getAttribute("user");

		// ログイン確認
		if (teacher == null) {

			System.out.println("teacher is null");

			res.sendRedirect("../login.jsp");

			return;
		}

		// DAO
		StudentDAO studentDao =
				new StudentDAO();

		SubjectDAO subjectDao =
				new SubjectDAO();

		ClassNumDAO classNumDao =
				new ClassNumDAO();

		// =========================
		// セレクトボックス用データ
		// =========================

		List<Student> allStudents =
				studentDao.filter(
						teacher.getSchool(),
						true);

		List<Integer> entYearSet =
				new ArrayList<>();

		for (Student s : allStudents) {

			if (!entYearSet.contains(
					s.getEntYear())) {

				entYearSet.add(
						s.getEntYear());
			}
		}

		List<String> classNumSet =
				classNumDao.filter(
						teacher.getSchool());

		List<Subject> subjectSet =
				subjectDao.filter(
						teacher.getSchool());

		// JSPへセット
		req.setAttribute(
				"ent_year_set",
				entYearSet);

		req.setAttribute(
				"class_num_set",
				classNumSet);

		req.setAttribute(
				"subject_set",
				subjectSet);

		// =========================
		// 検索処理
		// =========================

		String f1 =
				req.getParameter("f1");

		String f2 =
				req.getParameter("f2");

		String subjectCd =
				req.getParameter("subject");

		String no =
				req.getParameter("no");

		System.out.println(
				"===== TEST REGIST =====");

		System.out.println(
				"f1 = " + f1);

		System.out.println(
				"f2 = " + f2);

		System.out.println(
				"subjectCd = " + subjectCd);

		System.out.println(
				"no = " + no);
		
		String search =
				req.getParameter("search");

		if (search != null) {

			if (f1 == null || f1.isEmpty()
					|| f2 == null || f2.isEmpty()
					|| subjectCd == null || subjectCd.isEmpty()
					|| no == null || no.isEmpty()) {

				req.setAttribute(
						"error",
						"入学年度とクラスと科目と回数を選択してください");

				req.getRequestDispatcher(
						"/scoremanager/main/test_regist.jsp")
						.forward(req, res);

				return;
			}
		}

		// 検索条件入力済みなら実行
		if (f1 != null
				&& f2 != null
				&& subjectCd != null
				&& no != null
				&& !f1.isEmpty()
				&& !f2.isEmpty()
				&& !subjectCd.isEmpty()
				&& !no.isEmpty()) {

			System.out.println(
					"検索処理に入りました");

			int entYear =
					Integer.parseInt(f1);

			Subject subject =
					subjectDao.get(
							subjectCd,
							teacher.getSchool());

			List<Student> students =
					studentDao.filter(
							entYear,
							f2,
							false,
							teacher.getSchool());

			System.out.println(
					"students size = "
					+ students.size());
			
			req.setAttribute("f1", f1);
			req.setAttribute("f2", f2);
			req.setAttribute("subject", subjectCd);
			req.setAttribute("selected_no", no);

			req.setAttribute(
					"students",
					students);

			req.setAttribute(
					"subject_name",
					subject.getName());

			req.setAttribute(
					"no",
					no);
			
			Map<String, Integer> pointMap =
					new HashMap<>();

			TestDAO testDao =
					new TestDAO();

			for (Student student : students) {

				Test test =
						testDao.get(
								student,
								subject,
								teacher.getSchool(),
								Integer.parseInt(no));

				if (test != null) {

					pointMap.put(
							student.getNo(),
							test.getPoint());
					
					System.out.println(
						    "student=" + student.getNo());

						System.out.println(
						    "subject=" + subject.getCd());

						System.out.println(
						    "no=" + no);

						System.out.println(
						    "test=" + test);
				}
			}

			req.setAttribute(
					"point_map",
					pointMap);
		}

		// JSPへ遷移
		req.getRequestDispatcher(
				"/scoremanager/main/test_regist.jsp")
				.forward(req, res);
		
		System.out.println(
				"検索no = " + no);
	}
}
