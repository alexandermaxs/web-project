package team.command;

import team.database.dao.TaskDAO;
import team.exception.DAOException;
import team.model.bean.Task;
import team.service.ResourceManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CurrentTask extends Command{
    private List<Task> tasks;
    private TaskDAO taskDAO = new TaskDAO();
    public static final String PARAM_ID = "userId";
    public static final String PARAM_LIST = "taskList";
    public static final String FORWARD = "/jsp/currentTasks.jsp";

    @Override
    public void processRequest(HttpServletRequest request,
       HttpServletResponse response) throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter(PARAM_ID));
            tasks = taskDAO.getCustomerTasks(userId);
            request.getSession().setAttribute(PARAM_LIST, tasks);
            setForward(FORWARD);
        } catch (DAOException e) {
            LOGGER.error(e);
            getMessages().addMessage(ResourceManager.getValue(MSG_DATABASE_ERROR));
            setForward(ResourceManager.getValue(FORWARD_ERROR));
        }
    }
}
