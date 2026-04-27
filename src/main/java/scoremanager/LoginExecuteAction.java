package scoremanager;

import bean.Teacher;
import dao.TeacherDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LoginExecuteAction extends Action {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String id = request.getParameter("id");
        String password = request.getParameter("password");

        TeacherDAO dao = new TeacherDAO();
        Teacher teacher = dao.get(id);

        if (teacher != null && teacher.getPassword().equals(password)) {

            HttpSession session = request.getSession();
            session.setAttribute("user", teacher); //ここ統一

            return "menu.jsp"; //returnで遷移

        } else {
            request.setAttribute("error", "IDまたはパスワードが違います");
            return "login.jsp"; //returnで戻る
        }
    }
}