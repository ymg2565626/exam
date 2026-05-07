package scoremanager.main;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setCharacterEncoding("UTF-8");

        int no = Integer.parseInt(request.getParameter("no")); // ★ここ変更

        String name = request.getParameter("name");
        int entYear = Integer.parseInt(request.getParameter("entYear"));
        String classNum = request.getParameter("classNum");
        boolean isAttend = Boolean.parseBoolean(request.getParameter("isAttend"));

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        School school = teacher.getSchool();

        Student s = new Student();
        s.setNo(no);   //変更場所
        s.setName(name);
        s.setEntYear(entYear);
        s.setClassNum(classNum);
        s.setAttend(isAttend);
        s.setSchool(school);

        StudentDAO dao = new StudentDAO();
        dao.save(s);

        request.getRequestDispatcher("student_list.jsp")
        .forward(request, response); 
    }
}
