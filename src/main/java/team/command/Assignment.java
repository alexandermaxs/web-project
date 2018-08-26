package team.command;

import team.database.dao.TaskDAO;
import team.exception.DAOException;
import team.component.ResourceManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Assignment extends Command {
    private TaskDAO taskDAO = new TaskDAO();
    private static final String FORWARD = "/jsp/managerMenu.jsp";
    private static final String IDENTIFICATION = "developer.identification";
    private static final String ASSIGNMENT = "developer.assignment";

    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int taskId = Integer.parseInt(request.getParameter("taskId"));
            int developerId = Integer.parseInt(request.getParameter("developerId"));
            if (taskId != 0 && developerId != 0) {
                taskDAO.assign(taskId, developerId);
                StringBuilder sb = new StringBuilder(ResourceManager.getValue(IDENTIFICATION));
                sb.append(developerId);
                sb.append(ResourceManager.getValue(ASSIGNMENT));
                sb.append(taskId);
                LOGGER.info(sb);
                setForward(FORWARD);
            }
        } catch (DAOException e) {
            LOGGER.error(e);
            getMessages().addMessage(ResourceManager.getValue(MSG_DATABASE_ERROR));
            setForward(ResourceManager.getValue(FORWARD_ERROR));
        }
    }
}
