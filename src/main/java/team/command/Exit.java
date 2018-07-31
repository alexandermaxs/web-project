package team.command;

import team.service.ResourceManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * This class implements a pattern command
 * This class provides the user exits
 */
public class Exit extends Command {
    public static final String FORWARD_AUTHORISATION = "forward.authorization";

    /**
     * This method provides the user exits
     * This method invalidates user session and makes a transition to a authorization page
     * @param request a httpServletRequest
     * @param response a httpServletResponse
     * @throws ServletException a ServletException
     * @throws IOException a IOException
     */
    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        setForward(ResourceManager.getValue(FORWARD_AUTHORISATION));
    }
}
