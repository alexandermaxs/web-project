package team.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import team.database.dao.ProjectDAO;
import team.exception.DAOException;
import team.model.bean.Project;
import team.component.ResourceManager;

public class ShowProjects extends Command {
    private ProjectDAO projectDAO = new ProjectDAO();
    private static final String PARAM_LIST = "projectList";
    private static final String FORWARD = "/jsp/showProjects.jsp";

    @Override
    public void processRequest(HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Project> projects = projectDAO.getAll();
            request.getSession().setAttribute(PARAM_LIST, projects);
            setForward(FORWARD);
        } catch (DAOException e) {
            LOGGER.error(ResourceManager.getValue(MSG_DATABASE_ERROR), e);
            setForward(ResourceManager.getValue(FORWARD_ERROR));
        }
    }
}
