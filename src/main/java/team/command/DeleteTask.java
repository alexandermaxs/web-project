package team.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import team.component.ResourceManager;
import team.database.dao.TaskDAO;
import team.exception.DAOException;

public class DeleteTask extends Command {
    private static final String FORWARD = "/jsp/success.jsp";
    private static final String PARAM_ID = "taskId";
    private static final String DELETION = "delete.task";

    @Override
    public void processRequest(HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException {
        int taskId = Integer.parseInt(request.getParameter(PARAM_ID));
        setForward(FORWARD);
        try {
            TaskDAO taskDAO = new TaskDAO();
            taskDAO.delete(taskId);
            LOGGER.info(ResourceManager.getValue(DELETION) + taskId);
        } catch (DAOException e) {
            LOGGER.error(e);
        }
    }
}
