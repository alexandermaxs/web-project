package team.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.exception.DAOException;
import team.exception.PersistentException;
import team.database.dao.UserDAO;
import team.model.UserBuilder;
import team.model.bean.User;
import team.service.ResourceManager;

public class Registration extends Command{
	
	private UserDAO userDAO = new UserDAO();	
	private User user;
	public static final String MSG_ERR_EXIST = "error.login.exist";
    public static final String MSG_USER_CREATED = "message.user.created";
    public static final String LOGGER_MSG_USER_CREATED = "logger.message.user.has.registred";
    public static final String FORWARD_AUTHORIZATION = "forward.authorization";
    public static final String FORWARD_REGISTRATION = "forward.registration";
    public static final String PARAM_USER = "user";
	
	@Override
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBuilder userBuilder = new UserBuilder(getMessages());
	     
		try {
			if (userBuilder.build(request)) {
				user = userBuilder.getUser();
				
				if (!userDAO.isUserExist(user.getLogin())) {
					userDAO.create(user);
					getMessages().addMessage(ResourceManager.getValue(MSG_USER_CREATED));
                    LOGGER.info(ResourceManager.getValue(LOGGER_MSG_USER_CREATED).replace("1", user.getLogin()));
                    setForward(ResourceManager.getValue(FORWARD_MAIN));
				} else {
					 getMessages().addMessage(ResourceManager.getValue(MSG_ERR_EXIST));
	                    setForward(ResourceManager.getValue(FORWARD_REGISTRATION));
				}
				
			} else {
				request.setAttribute(PARAM_USER, userBuilder.getUser());
				setForward(ResourceManager.getValue(FORWARD_REGISTRATION));
			}
			
			} catch (DAOException e) {
				getMessages().addMessage(ResourceManager.getValue(MSG_DATABASE_ERROR));
				setForward(ResourceManager.getValue(FORWARD_ERROR));
				LOGGER.error(e);
			} catch (PersistentException e) {
				setForward(ResourceManager.getValue(FORWARD_REGISTRATION));
				LOGGER.error(e);
			}
		
	}

}
