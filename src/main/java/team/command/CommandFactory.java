package team.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class CommandFactory {
	public static final String PARAM_COMMAND = "commandName";
	private static final Logger LOGGER = LogManager.getLogger(CommandFactory.class);
	
	public static Command getCommand(HttpServletRequest request) {
        Command command = null;
        CommandEnum commandType = getCommandEnum(request.getParameter(PARAM_COMMAND));
        
		switch (commandType) {
            case TO_LOG_IN:
                command = new ToLogIn();
                break;
		    case SHOW_PROJECTS:
                command = new ShowProjects();
                break;
            case MANAGER_MENU:
                command = new ManagerMenu();
                break;
            case DEVELOPER_MENU:
                command = new DeveloperMenu();
                break;
            case CUSTOMER_MENU:
                command = new CustomerMenu();
                break;
            case FORM_PROJECT:
                command = new FormProject();
                break;
            case ADD_PROJECT:
                command = new AddProject();
		        break;
		    case SUCCESS:
                command = new Success();
		        break;
            case SHOW_TASKS:
                command = new ShowTasks();
                break;
            case FREE_DEVELOPERS:
                command = new FreeDevelopers();
                break;
            case ASSIGNMENT:
                command = new Assignment();
                break;
            case ADD_TASK:
                command = new AddTask();
                break;
            case DELETE_TASK:
                command = new DeleteTask();
                break;
            case FORM_TASK:
                command = new FormTask();
                break;
            case CURRENT_TASK:
                command = new CurrentTask();
                break;
            case USER_TAG:
                command = new UserTag();
                break;

            case REGISTRATION:
                command = new Registration();
                break;
            case CHANGE_BODY:
                command = new ChangeBody();
                break;
            case TO_PAGE:
                command = new ToPage();
                break;
            case AUTHORIZATION:
                command = new Authorization();
                break;
            case EXIT:
                command = new Exit();
                break;
            case CHANGE_LANGUAGE:
                command = new ChangeLanguage();
                break;
            default:
                break;
		}
        return command;
	}
	
    private static CommandEnum getCommandEnum(String commandName) {
        CommandEnum commandEnum = null;
        try {
            commandEnum = CommandEnum.valueOf(commandName);
        } catch (IllegalArgumentException e) {
            commandEnum = CommandEnum.UNKNOWN_COMMAND;
            LOGGER.error(e);
        } catch (NullPointerException e) {
            commandEnum = CommandEnum.UNKNOWN_COMMAND;
            LOGGER.error(e);
        }
        return commandEnum;
    }
}
