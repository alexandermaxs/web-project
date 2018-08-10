package team.command;

import team.database.dao.TaskDAO;
import team.exception.DAOException;
import team.service.ResourceManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Assignment extends Command{
    private TaskDAO taskDAO = new TaskDAO();
    private int taskId;
    private int developerId;
    public static final String FORWARD = "/jsp/managerMenu.jsp";

    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            taskId = Integer.parseInt(request.getParameter("taskId"));
            developerId = Integer.parseInt(request.getParameter("developerId"));
            if (taskId != 0 && developerId != 0){
                taskDAO.assign(taskId, developerId);
                setForward(FORWARD);
            }
        } catch (DAOException e) {
            LOGGER.error(e);
            getMessages().addMessage(ResourceManager.getValue(MSG_DATABASE_ERROR));
            setForward(ResourceManager.getValue(FORWARD_ERROR));
        }
    }
}
