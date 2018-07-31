package team.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import team.database.dao.ProjectDAO;
import team.exception.DAOException;
import team.model.bean.Project;

public class AddProject extends Command{
    public static final String FORWARD = "/jsp/success.jsp";
    public static final String PARAM_CIPHER = "cipher";
    public static final String PARAM_DATE = "date";
    public static final String PARAM_COST = "cost";

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
        } catch (DAOException e) {
            LOGGER.error(e);
        }
    }
}
