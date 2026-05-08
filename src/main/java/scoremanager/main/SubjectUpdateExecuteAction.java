package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {

    public void execute(
            HttpServletRequest req,
            HttpServletResponse res
    ) throws Exception {

        req.getRequestDispatcher("/SubjectList.action")
           .forward(req, res);
    }
}