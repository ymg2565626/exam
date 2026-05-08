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

public class SubjectCreateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// ローカル変数の指定 1
		HttpSession session = req.getSession(); // セッション
		Teacher teacher = (Teacher)session.getAttribute("user");
		String subject_cd = ""; // 入力された科目コード
		String subject_name = "";//入力された科目名
		Subject subject = new Subject();
		SubjectDAO subjectDao = new SubjectDAO();
		Map<String, String> errors = new HashMap<>(); // エラーメッセージ
		Subject list_subject = null;
		// リクエストパラメーターの取得 2

		subject_cd = req.getParameter("cd");
		subject_name = req.getParameter("name");
		//subjectをリストで全件取得
		list_subject = subjectDao.get(subject_cd,teacher.getSchool());
		
		// DBからデータ取得 3
		// なし

		// ビジネスロジック 4
		if (subject_cd.length() != 3) { // 科目コードの文字数が違うとき
			errors.put("1", " 科目コードは3文字で入力してください");
			// リクエストにエラーメッセージをセット
			req.setAttribute("errors", errors);
		} else {
			if (list_subject != null) { // 科目コードが重複している場合
				errors.put("2", "科目コードが重複しています");
				// リクエストにエラーメッセージをセット
				req.setAttribute("errors", errors);
			} else {
				//
				subject.setCd(subject_cd);
				subject.setSchool(teacher.getSchool());
				subject.setName(subject_name);
				// 
				subjectDao.save(subject);
			}
		}


		// JSPへフォワード 7
		if (errors.isEmpty()) { // エラーメッセージがない場合
			// 登録完了画面にフォワード
			req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);
		} else { // エラーメッセージがある場合
			// 登録画面にフォワード
			req.getRequestDispatcher("SubjectCreate.action").forward(req, res);
		}
	}

}
