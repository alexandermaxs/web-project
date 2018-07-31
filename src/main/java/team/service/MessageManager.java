package team.service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * This class preserves a list of messages
 */
public class MessageManager {
    public static final String PARAM_MESSAGE = "message";
    /**
     * This is the list of messages
     */
    private ArrayList<String> messageList = new ArrayList<>();

    /**
     *This method adds a message to the list
     * @param message a new message
     */
    public void addMessage(String message) {
        if (message != null) {
            messageList.add(message);
        }
    }

    /**
     * This method sets message-list to request
     * @param request a HttpServletRequest
     */
    public void saveMessages(HttpServletRequest request) {
        request.setAttribute(PARAM_MESSAGE, messageList);
    }

    /**
     * This method adds all messages from new message-list to current list
     * @param messages is the new messages list
     */
    public void importMessages(ArrayList<String> messages) {
        if (messages != null) {
            messageList.addAll(messages);
        }
    }
}
