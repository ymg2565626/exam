package scoremanager.main;

import java.util.List;

import bean.ClassNum;
import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDAO;
import dao.StudentDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentListAction extends Action {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // セッション取得
        HttpSession session = request.getSession();

        // ログイン情報取得
        Teacher teacher = (Teacher) session.getAttribute("user");

        if (teacher == null) {
            System.out.println("【DEBUG】teacher is null");
            return "login.jsp";
        }

        // 学校取得
        School school = teacher.getSchool();

        if (school == null) {
            System.out.println("【DEBUG】school is null");
            return "error.jsp";
        }

        System.out.println("【DEBUG】school_cd = " + school.getCd());

        // パラメータ取得
        String entYearStr = request.getParameter("entYear");
        String classNum = request.getParameter("classNum");
        String isAttendStr = request.getParameter("isAttend");

        System.out.println("【DEBUG】entYear = " + entYearStr);
        System.out.println("【DEBUG】classNum = " + classNum);
        System.out.println("【DEBUG】isAttendStr = " + isAttendStr);

        // DAO
        StudentDAO studentDAO = new StudentDAO();
        ClassNumDAO classNumDAO = new ClassNumDAO();

        List<Student> studentList;

        // 在学フラグ
        boolean isAttend = true;
        if (isAttendStr != null && !isAttendStr.isEmpty()) {
            isAttend = Boolean.parseBoolean(isAttendStr);
        }

        System.out.println("【DEBUG】isAttend = " + isAttend);

        // ★まずは必ずデータが出るか確認（重要）
        studentList = studentDAO.filter(school, isAttend);

        System.out.println("【DEBUG】studentList size = " + studentList.size());

        // クラス一覧
        List<ClassNum> classNumList = classNumDAO.filter(school);

        // requestにセット
        request.setAttribute("studentList", studentList);
        request.setAttribute("classNumList", classNumList);

        // 検索条件も戻す
        request.setAttribute("entYear", entYearStr);
        request.setAttribute("classNum", classNum);
        request.setAttribute("isAttend", isAttend);

        // 画面へ
        return "student_list.jsp";
    }
}