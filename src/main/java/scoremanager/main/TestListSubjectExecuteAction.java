package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDAO;
import dao.SubjectDAO;
import dao.TestListSubjectDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        // セッション取得
        HttpSession session =
                request.getSession();

        Teacher teacher =
                (Teacher) session.getAttribute("user");

        // リクエストパラメータ取得
        String subjectCd =
                request.getParameter("subject");

        String entYearStr =
                request.getParameter("f1");

        String classNum =
                request.getParameter("f2");

        // DAO生成
        SubjectDAO subjectDao =
                new SubjectDAO();

        ClassNumDAO classNumDao =
                new ClassNumDAO();

        // プルダウン用データ
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

        request.setAttribute(
                "ent_year_set",
                entYearSet);

        request.setAttribute(
                "class_num_set",
                classNumDao.filter(
                        teacher.getSchool()));

        request.setAttribute(
                "subject_set",
                subjectDao.filter(
                        teacher.getSchool()));

        // 未入力チェック
        if (entYearStr == null || entYearStr.isEmpty()
                || classNum == null || classNum.isEmpty()
                || subjectCd == null || subjectCd.isEmpty()) {

            request.setAttribute(
                    "error",
                    "入学年度とクラスと科目を選択してください");

            request.getRequestDispatcher(
                    "test_list_subject.jsp")
                    .forward(request, response);

            return;
        }

        // int変換
        int entYear =
                Integer.parseInt(entYearStr);

        // DAO生成
        TestListSubjectDAO testListSubjectDao =
                new TestListSubjectDAO();

        // 科目情報取得
        Subject subject =
                subjectDao.get(
                        subjectCd,
                        teacher.getSchool());

        // 学生一覧取得
        List<TestListSubject> students =
                testListSubjectDao.filter(
                        entYear,
                        classNum,
                        subject,
                        teacher.getSchool());

        // 一覧表示用
        request.setAttribute(
                "subject",
                subject);

        request.setAttribute(
                "students",
                students);

        // 同じ画面へ戻す
        request.getRequestDispatcher(
                "test_list_subject.jsp")
                .forward(request, response);
    }
}
