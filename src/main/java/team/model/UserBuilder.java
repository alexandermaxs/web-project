package team.model;

import java.security.NoSuchAlgorithmException;

import team.model.bean.User;
import team.model.bean.UserType;
import team.component.MD5Generator;
import team.component.MessageManager;
import team.component.ResourceManager;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class gets user data from request
 * and builds the user
 */
public class UserBuilder {
    private static final Logger LOGGER = LogManager.getLogger(UserBuilder.class);
    /**
     * This is the user, which will be built
     */
    private User user = new User();
    /**
     * This object stores reports of incorrect data
     */
    private MessageManager messages;

    public UserBuilder(MessageManager messages) {
        this.messages = messages;
        user.setUserType(UserType.DEVELOPER);
    }

    public User getUser() {
        return user;
    }

    /**
     * This method builds up the user
     * Reads all user properties
     * if success, then user is built
     *
     * @param request a HttpServletRequest
     * @return <code>true</code> if success
     */
    public boolean build(HttpServletRequest request) {
        boolean readingSuccessful;
        readingSuccessful = readLogin(request);
        readingSuccessful &= readPassword(request);
        return readingSuccessful;
    }

    /**
     * This method gets login from the request and check it
     *
     * @param request a HttpServletRequest
     * @return <code>true</code> if login is correct
     */
    private boolean readLogin(HttpServletRequest request) {
        String login = request.getParameter("login");
        if (login != null) {
            user.setLogin(login);
            if (login.matches(ResourceManager.getValue("pattern.login"))) {
                return true;
            }
        }
        messages.addMessage(ResourceManager.getValue("error.pattern.login") + " " + ResourceManager.getValue("pattern.login"));
        return false;
    }

    /**
     * This method gets password from request and check it
     *
     * @param request a HttpServletRequest
     * @return <code>true</code> if password is equals to confirmed password
     */
    private boolean readPassword(HttpServletRequest request) {
        String password = request.getParameter("password");
        try {
            if (password != null) {
                if (password.matches(ResourceManager.getValue("pattern.password"))) {
                    if (password.equals(request.getParameter("password2"))) {
                        MD5Generator md5Generator = new MD5Generator();
                        String hash;

                        hash = md5Generator.getMD5(password);

                        user.setPassword(hash);
                        return true;
                    } else {
                        messages.addMessage(ResourceManager.getValue("error.confirm.password"));
                    }
                }
            }
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e);
        }
        messages.addMessage(ResourceManager.getValue("error.pattern.password") + " " + ResourceManager.getValue("pattern.password"));
        return false;
    }
}
