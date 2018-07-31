package team.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import team.database.dao.ProjectDAO;
import team.exception.DAOException;
import team.model.bean.Project;
import team.service.ResourceManager;

public class ShowProjects extends Command{
    private List<Project> projects;
    private ProjectDAO projectDAO = new ProjectDAO();
    public static final String PARAM_LIST = "projectList";
    public static final String FORWARD = "/jsp/showProjects.jsp";

    @Override
    public void processRequest(HttpServletRequest request,
       HttpServletResponse response) throws ServletException, IOException {
        try {
            projects = projectDAO.getAll();
            request.getSession().setAttribute(PARAM_LIST, projects);
            setForward(FORWARD);
        } catch (DAOException e) {
            LOGGER.error(e);
            getMessages().addMessage(ResourceManager.getValue(MSG_DATABASE_ERROR));
            setForward(ResourceManager.getValue(FORWARD_ERROR));
        }
    }
}
