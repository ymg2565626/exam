package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {

    public void execute(
            HttpServletRequest req,
            HttpServletResponse res
    ) throws Exception {

        req.getRequestDispatcher("/SubjectList.action")
           .forward(req, res);
    }
}