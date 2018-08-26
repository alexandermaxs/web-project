package team.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeveloperMenu extends Command {
    private static final String FORWARD = "/jsp/developerMenu.jsp";

    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setForward(FORWARD);
    }
}
