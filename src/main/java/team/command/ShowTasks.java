package team.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import team.database.dao.TaskDAO;
import team.exception.DAOException;
import team.model.bean.Task;
import team.component.ResourceManager;

public class ShowTasks extends Command {
    private TaskDAO taskDAO = new TaskDAO();
    private static final String PARAM_LIST = "taskList";
    private static final String FORWARD = "/jsp/showTasks.jsp";

    @Override
    public void processRequest(HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Task> tasks = taskDAO.getAllTasks();
            request.getSession().setAttribute(PARAM_LIST, tasks);
            setForward(FORWARD);
        } catch (DAOException e) {
            LOGGER.error(ResourceManager.getValue(MSG_DATABASE_ERROR), e);
            setForward(ResourceManager.getValue(FORWARD_ERROR));
        }
    }
}
