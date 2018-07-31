package team.command;

import team.service.ResourceManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * This class implements a pattern command
 * This class makes a transition to a page whose name is placed in request
 */
public class ToPage extends Command {

    public static final String PARAM_PAGE = "forwardPage";
    public static final String MSG_NO_PAGE = "message.error.page.not.found";

    /**
     * This method makes a transition to a page whose name is placed in request
     * @param request a httpServletRequest
     * @param response a httpServletResponse
     * @throws ServletException a ServletException
     * @throws IOException a IOException
     */
    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = request.getParameter(PARAM_PAGE);
        if (forward != null) {
            setForward(forward);
        } else {
            getMessages().addMessage(ResourceManager.getValue(MSG_NO_PAGE));
            setForward(ResourceManager.getValue(FORWARD_ERROR));
        }
    }
}
