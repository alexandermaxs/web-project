package team.command;

import team.component.ResourceManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Success extends Command {
    private static final String FORWARD = "/jsp/success.jsp";
    private static final String SUCCESS = "page.success";

    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info(ResourceManager.getValue(SUCCESS));
        setForward(FORWARD);
    }
}
