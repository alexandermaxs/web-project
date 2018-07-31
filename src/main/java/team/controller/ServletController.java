package team.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import team.command.Command;
import team.command.CommandFactory;

/**
 * This class corresponds MVC
 * This Servlet handles requests
 */
public class ServletController extends HttpServlet{

    /**
     * This method handles requests
     * @param request a httpServletRequest
     * @param response a httpServletResponse
     * @throws ServletException a ServletException
     * @throws IOException an IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * This method handles requests
     * @param request a httpServletRequest
     * @param response a httpServletResponse
     * @throws ServletException a ServletException
     * @throws IOException an IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * This method gets a instance of <code>Command</code> from <code>CommandFactory</code>
     * by request and execute this command. Then it gos to next jsp.
     * @param request a httpServletRequest
     * @param response a httpServletResponse
     * @throws ServletException a ServletException
     * @throws IOException an IOException
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Command command = CommandFactory.getCommand(request);
        response.setContentType("text/html;charset=UTF-8");
        try {
            command.processRequest(request, response);
            command.getMessages().saveMessages(request);
            request.getRequestDispatcher(command.getForward()).forward(request, response);
        } catch (IOException e) {
            request.getRequestDispatcher("ERROR!").forward(request, response);
        }
    }
}
