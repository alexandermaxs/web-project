package team.database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class implements MVC-pattern
 * Creates safe connection with database
 * Provides information to developerMenu.jsp
 */
public class TaskDAO extends AbstractDAO{
    private String sqlGetUserId = "SELECT id FROM team.user WHERE login = ?";
    private String sqlGetTaskId = "SELECT task_id FROM team.developer WHERE user_id = ?";
    private String sqlGetTaskInfo = "SELECT info FROM team.task WHERE id = ?";

    private String sqlGetProjectId = "SELECT project_id FROM team.task WHERE id = ?";
    private String sqlGetProjectDate = "SELECT date FROM team.project WHERE id = ?";

    public String getInfo(String login){
		String info = "";
		int userId = 0;
		int taskId = 0;
		try {
			this.connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sqlGetUserId);
			statement.setString(1, login);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				userId = resultSet.getInt(1);;
			}
			statement = connection.prepareStatement(sqlGetTaskId);
			statement.setInt(1, userId);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				taskId = resultSet.getInt(1);
			}
			statement = connection.prepareStatement(sqlGetTaskInfo);
			statement.setInt(1, taskId);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				info = resultSet.getString(1);
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		} finally {
			try {
				super.close();
			} catch (SQLException e) {
				LOGGER.error(e);
			}
		}
		return info;
	}

	public String getDate(String login){
		String date = "";
		int userId = 0;
		int taskId = 0;
		int projectId = 0;
		try {
			this.connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sqlGetUserId);
			statement.setString(1, login);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				userId = resultSet.getInt(1);;
			}
			statement = connection.prepareStatement(sqlGetTaskId);
			statement.setInt(1, userId);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				taskId = resultSet.getInt(1);
			}
			statement = connection.prepareStatement(sqlGetProjectId);
			statement.setInt(1, taskId);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				projectId = resultSet.getInt(1);
			}
			statement = connection.prepareStatement(sqlGetProjectDate);
			statement.setInt(1, projectId);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				date = resultSet.getString(1);
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		} finally {
			try {
				super.close();
			} catch (SQLException e) {
				LOGGER.error(e);
			}
		}
		return date;
	}
}
