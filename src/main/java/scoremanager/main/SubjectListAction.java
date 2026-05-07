package scoremanager.main;

import java.util.List;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectListAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// ローカル変数の指定 1
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		SubjectDao subjectDao = new SubjectDao();
		List<Subject> subjects = null;
		// リクエストパラメーターの取得 2
		subjects = subjectDao.filter(teacher.getSchool());
		// ビジネスロジック 4


		// DBからデータ取得 3
		// ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		req.setAttribute("subjects", subjects);
		// リクエストにデータをセット

		// JSPへフォワード 7
		req.getRequestDispatcher("subject_list.jsp").forward(req, res);
	}

}
