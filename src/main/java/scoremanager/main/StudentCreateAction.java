package scoremanager.main;

import java.util.List;

import bean.ClassNum;
import bean.Teacher;
import dao.ClassNumDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateAction extends Action {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        ClassNumDAO dao = new ClassNumDAO();
        List<ClassNum> list = dao.filter(teacher.getSchool());

        request.setAttribute("classNumList", list);

        request.getRequestDispatcher("student_create.jsp")
        .forward(request, response); 
    }
}
