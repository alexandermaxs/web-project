package team.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import team.database.dao.TaskDAO;
import team.exception.DAOException;
import team.model.bean.Task;

public class AddTask extends Command{
    public static final String FORWARD = "/jsp/success.jsp";
    public static final String PARAM_NUMBER = "number";
    public static final String PARAM_INFO = "info";
    public static final String PARAM_PROJECT_ID = "projectId";

    @Override
    public void processRequest(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
        Task task = new Task();
        task.setNumber(Integer.parseInt(request.getParameter(PARAM_NUMBER)));
        task.setInfo(request.getParameter(PARAM_INFO));
        task.setProjectId(Integer.parseInt(request.getParameter(PARAM_PROJECT_ID)));
        setForward(FORWARD);
        try {
            TaskDAO taskDAO = new TaskDAO();
            taskDAO.add(task);
        } catch (DAOException e) {
            LOGGER.error(e);
        }
    }
}
