package team.command;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import team.exception.DAOException;
import team.database.dao.UserDAO;
import team.exception.InvalidArgumentException;
import team.model.bean.User;
import team.service.MD5Generator;
import team.service.ResourceManager;

/**
 * This class implements a pattern command
 * This class authorizes users
 */
public class Authorization extends Command {
    public static final String PARAM_LOGIN = "login";
    public static final String PARAM_LAST_LOGIN = "lastLogin";
    public static final String PARAM_PASSWORD = "password";
    public static final String PARAM_USER = "user";
    public static final String FORWARD_AUTHORIZATION = "forward.authorization";
    public static final String MSG_USER_ENTER = "logger.message.user.enter";
    public static final String MSG_LOGIN_ERROR = "error.login.or.password";
    /**
     * This is an instance of class <code>UserDAO</code>
     * which links entity <code>User</code> with the database
     */
    private UserDAO userDAO = new UserDAO();
    /**
     * This user is entering in system
     */
    private User user;

    /**
     * Gets the login and password from request and find user in database
     * If such user exist this user will add to session and user will enter in system
     * @param request a httpServletRequest
     * @param response a httpServletResponse
     * @throws ServletException a ServletException
     * @throws IOException a IOException
     */
    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String login = request.getParameter(PARAM_LOGIN);
        String password = request.getParameter(PARAM_PASSWORD);
        try {
            if (login.length() > 0 && password.length() > 0) { 
            	MD5Generator md5Generator = new MD5Generator();
				String hash = md5Generator.getMD5(password);
				user = userDAO.getUserByLoginPassword(login, hash);
            }
            
            if (user != null) {
                setForward(ResourceManager.getValue(FORWARD_MAIN));
                request.getSession().setAttribute(PARAM_USER, user);
                LOGGER.info(user.getLogin() + " " + ResourceManager.getValue(MSG_USER_ENTER));
            } else {
                request.setAttribute(PARAM_LAST_LOGIN, login);
                getMessages().addMessage(ResourceManager.getValue(MSG_LOGIN_ERROR));
                setForward(ResourceManager.getValue(FORWARD_AUTHORIZATION));
            }
        } catch (DAOException e) {
        	getMessages().addMessage(ResourceManager.getValue(MSG_DATABASE_ERROR));
            setForward(ResourceManager.getValue(FORWARD_ERROR));
            LOGGER.error(e);
		} catch (InvalidArgumentException e) {
            setForward(ResourceManager.getValue(FORWARD_ERROR));
            LOGGER.error(e);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e);
		}
    }
}
