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
import team.component.MD5Generator;
import team.component.ResourceManager;

/**
 * This class implements a pattern command
 * This class authorizes users
 */
public class Authorization extends Command {
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_LAST_LOGIN = "lastLogin";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_USER = "user";
    private static final String FORWARD_AUTHORIZATION = "forward.authorization";
    private static final String MSG_USER_ENTER = "logger.message.user.enter";
    private static final String MSG_LOGIN_ERROR = "error.login.or.password";
    /**
     * This is an instance of class <code>UserDAO</code>
     * which links entity <code>User</code> with the database
     */
    private UserDAO userDAO = new UserDAO();

    /**
     * Gets the login and password from request and find user in database
     * If such user exist this user will add to session and user will enter in system
     *
     * @param request  a httpServletRequest
     * @param response a httpServletResponse
     * @throws ServletException a ServletException
     * @throws IOException      a IOException
     */
    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = null;
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
                LOGGER.info(ResourceManager.getValue(MSG_LOGIN_ERROR));
                setForward(ResourceManager.getValue(FORWARD_AUTHORIZATION));
            }
        } catch (DAOException e) {
            LOGGER.error(ResourceManager.getValue(MSG_DATABASE_ERROR), e);
            setForward(ResourceManager.getValue(FORWARD_ERROR));
        } catch (InvalidArgumentException | NoSuchAlgorithmException e) {
            LOGGER.error(e);
            setForward(ResourceManager.getValue(FORWARD_ERROR));
        }
    }
}
