package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectDeleteAction extends Action {

    public void execute(
            HttpServletRequest req,
            HttpServletResponse res
    ) throws Exception {

        req.getRequestDispatcher("/subject_delete.jsp")
           .forward(req, res);
    }
}