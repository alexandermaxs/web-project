package team.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class implements a pattern command
 * This class changes language of user interface
 */
public class ChangeLanguage extends Command {

    public static final String PARAM_LANGUAGE = "language";
    public static final String PARAM_PAGE = "forwardPage";

    /**
     * This method changes language of user interface
     * @param request a httpServletRequest
     * @param response a httpServletResponse
     * @throws ServletException a ServletException
     * @throws IOException a IOException
     */
    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forwardPage = request.getParameter(PARAM_PAGE);
        String language = request.getParameter(PARAM_LANGUAGE);
        request.getSession().setAttribute(PARAM_LANGUAGE, language);
        setForward(forwardPage);
    }
}
