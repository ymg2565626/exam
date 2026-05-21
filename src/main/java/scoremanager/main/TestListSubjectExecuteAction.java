package scoremanager.main;

import java.util.List;

import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.SubjectDAO;
import dao.TestListSubjectDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectExecuteAction extends Action{
	@Override
	public void execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		 // セッション取得
        HttpSession session = request.getSession();
        Teacher teacher =(Teacher)session.getAttribute("user");
       
        // リクエストパラメータ取得
        String subjectCd = request.getParameter("subject");
        int entYear =Integer.parseInt(request.getParameter("f1"));

        String classNum =request.getParameter("f2");
        

        // DAO生成
        SubjectDAO subjectDao = new SubjectDAO();
        TestListSubjectDAO testListSubjectDao = new TestListSubjectDAO();

        //科目情報取得
        Subject subject = subjectDao.get(subjectCd,teacher.getSchool());

        // 学生一覧取得
        List<TestListSubject> students =testListSubjectDao.filter(entYear,classNum,subject,teacher.getSchool());
        
       

        // JSPへ値を渡す
        request.setAttribute("subject", subject);
        request.setAttribute("students", students);
        
        //jspへフォワード
        request.getRequestDispatcher("test_list_subject.jsp").forward(request, response);
        
        

	}

}
