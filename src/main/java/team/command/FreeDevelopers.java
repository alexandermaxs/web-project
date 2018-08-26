package team.command;

import team.database.dao.TaskDAO;
import team.exception.DAOException;
import team.model.bean.User;
import team.component.ResourceManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FreeDevelopers extends Command {
    private TaskDAO taskDAO = new TaskDAO();
    private static final String PARAM_LIST = "developerList";
    private static final String FORWARD = "/jsp/freeDevelopers.jsp";

    @Override
    public void processRequest(HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException {
        try {
            List<User> developers = taskDAO.getFreeDevelopers();
            request.getSession().setAttribute(PARAM_LIST, developers);
            setForward(FORWARD);
        } catch (DAOException e) {
            LOGGER.error(ResourceManager.getValue(MSG_DATABASE_ERROR), e);
            setForward(ResourceManager.getValue(FORWARD_ERROR));
        }
    }
}
