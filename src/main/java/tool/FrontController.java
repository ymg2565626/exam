package tool;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class FrontController extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doPost(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String path = req.getServletPath();

        System.out.println("【DEBUG】path = " + path);

        Action action = null;

        try {

            // ログイン画面
            if (path.equals("/login.do")) {
                action = new scoremanager.LoginAction();

            // ログイン処理
            } else if (path.equals("/loginExecute.do")) {
                action = new scoremanager.LoginExecuteAction();

            // ログアウト
            } else if (path.equals("/logout.do")) {
                action = new scoremanager.main.LogoutAction();

            //メニュー（設計書どおり：直接JSP）
            } else if (path.equals("/menu.do")) {
                req.getRequestDispatcher("menu.jsp").forward(req, res);
                return;

            // 学生一覧
            } else if (path.equals("/StudentList.do")) {
                action = new scoremanager.main.StudentListAction();

            // 学生登録（画面）
            } else if (path.equals("/StudentCreate.do")) {
                action = new scoremanager.main.StudentCreateAction();

            // 学生登録（実行）
            } else if (path.equals("/StudentCreateExecute.do")) {
                action = new scoremanager.main.StudentCreateExecuteAction();

            // 学生変更（画面）
            } else if (path.equals("/StudentUpdate.do")) {
                action = new scoremanager.main.StudentUpdateAction();

            // 学生変更（実行）
            } else if (path.equals("/StudentUpdateExecute.do")) {
                action = new scoremanager.main.StudentUpdateExecuteAction();
            }

            // Action実行
            if (action != null) {
                String forward = action.execute(req, res);

                if (forward != null) {
                    req.getRequestDispatcher(forward).forward(req, res);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect("error.jsp");
        }
    }
}