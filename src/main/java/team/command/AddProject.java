package team.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import team.component.ResourceManager;
import team.database.dao.ProjectDAO;
import team.exception.DAOException;
import team.model.bean.Project;

public class AddProject extends Command {
    private static final String FORWARD = "/jsp/success.jsp";
    private static final String PARAM_CIPHER = "cipher";
    private static final String PARAM_DATE = "date";
    private static final String PARAM_COST = "cost";
    private static final String ADD_PROJECT = "add.project";

    @Override
    public void processRequest(HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException {
        Project project = new Project();
        project.setCipher(request.getParameter(PARAM_CIPHER));
        project.setDate(request.getParameter(PARAM_DATE));
        project.setCost(Double.parseDouble(request.getParameter(PARAM_COST)));
        setForward(FORWARD);
        try {
            ProjectDAO projectDAO = new ProjectDAO();
            projectDAO.add(project);
            LOGGER.info(project.getCipher() + ResourceManager.getValue(ADD_PROJECT));
        } catch (DAOException e) {
            LOGGER.error(e);
        }
    }
}
