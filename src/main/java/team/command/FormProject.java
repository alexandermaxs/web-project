package team.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormProject extends Command{
    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "/jsp/addProject.jsp";
        //String forward = "/jsp/en/formProject.jsp";
        setForward(forward);
    }
}
