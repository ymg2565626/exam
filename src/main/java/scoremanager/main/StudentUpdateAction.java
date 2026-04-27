package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateAction extends Action {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String noStr = request.getParameter("no");

        StudentDAO dao = new StudentDAO();

        //noがある → 編集画面
        if (noStr != null && !noStr.isEmpty()) {

            int no = Integer.parseInt(noStr);
            Student student = dao.get(no);

            request.setAttribute("student", student);
            return "student_update.jsp";
        }

        //noがない → 一覧を表示（選択画面）
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        List<Student> list = dao.filter(school, true);

        request.setAttribute("studentList", list);

        return "student_update_list.jsp";
    }
}