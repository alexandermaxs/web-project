package team.tag;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This tag print a list of string
 * This tag gets a <code>ArrayList<String></code> from request and prints it

 */
public class MessageTag extends TagSupport {
	public static final String PARAM_MASSAGE = "message";
    public static final String NEW_LINE = "<br/>";
    /**
     * This is logger which print some messages to log file
     */
    private static final Logger LOGGER = LogManager.getLogger(MessageTag.class);

    /**
     * This tag print a list of string
     * This tag gets an attribute "message" from request and prints it
     * @return SKIP_BODY
     * @throws JspException a JspException
     */
    @Override
    public int doStartTag() throws JspException {
        @SuppressWarnings("unchecked")
		ArrayList<String> messageList = (ArrayList<String>) pageContext.getRequest().getAttribute(PARAM_MASSAGE);
        if (messageList != null) {
            JspWriter writer = pageContext.getOut();
            try {
                for (String message : messageList) {
                    writer.print(message + NEW_LINE);
                }
            } catch (IOException ex) {
                LOGGER.error(ex);
            }
        }
        return SKIP_BODY;
    }
}
