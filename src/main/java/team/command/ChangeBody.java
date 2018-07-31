package team.command;

import team.service.ResourceManager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * This class implements a pattern command
 * This class changes the body of the main page
 */
public class ChangeBody extends Command {

    public static final String PARAM_PAGE = "forwardPage";
    public static final String MSG_NO_PAGE = "message.error.page.not.found";

    /**
     * This method changes a body of main page
     * The main page consists of several jsp. This method replaces a body of main page
     * @param request a httpServletRequest
     * @param response a httpServletResponse
     * @throws ServletException a ServletException
     * @throws IOException a IOException
     */
    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newBody = request.getParameter(PARAM_BODY_PAGE);
        if (newBody != null) {
            setForward(ResourceManager.getValue(FORWARD_MAIN));
            request.setAttribute(PARAM_BODY_PAGE, newBody);
        } else {
            getMessages().addMessage(ResourceManager.getValue(MSG_NO_PAGE));
            setForward(ResourceManager.getValue(FORWARD_ERROR));
        }
    }
}
