package team.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.service.MessageManager;

public abstract class Command {
	public static final String MSG_DATABASE_ERROR = "error.database.fail";
	public static final String MSG_CP_ERROR = "error.database.cp.fail";
	public static final String MSG_SUCCESS = "message.success";
	public static final String MSG_EMPTY_CHOICE = "message.empty";
    public static final String FORWARD_ERROR = "forward.error";
    public static final String FORWARD_MAIN = "forward.main";
    public static final String PARAM_BODY_PAGE = "bodyPage";
    protected static final Logger LOGGER = LogManager.getLogger(Command.class);
    /**
     * To this address will be realized transition after executing the command
     */
    private String forward;
    /**
     * This object contains a list of messages which will print to jsp
     */
    private MessageManager messages = new MessageManager();

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }

    public void setMessages(MessageManager messages) {
        this.messages = messages;
    }

    public MessageManager getMessages() {
        return messages;
    }

    /**
     * This is an abstract method which provides business-logic, proceeds result to jsp
     * @param request a httpServletRequest
     * @param response a httpServletResponse
     * @throws ServletException a ServletException
     * @throws IOException a IOException
     */
    public abstract void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
}
