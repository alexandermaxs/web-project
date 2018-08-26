package team.database.dao;

import team.exception.DAOException;
import team.model.bean.Task;
import team.model.bean.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements MVC-pattern
 * Creates safe connection with database
 * Provides information to developerMenu.jsp
 */
public class TaskDAO extends AbstractDAO {
    private static final String SQL_GET_USER_ID = "SELECT id FROM team.user WHERE login = ?";
    private static final String SQL_GET_TASK_ID = "SELECT task_id FROM team.developer WHERE user_id = ?";
    private static final String SQL_GET_TASK_INFO = "SELECT info FROM team.task WHERE id = ?";
    private static final String SQL_GET_PROJECT_ID = "SELECT project_id FROM team.task WHERE id = ?";
    private static final String SQL_GET_PROJECT_DATE = "SELECT date FROM team.project WHERE id = ?";
    private static final String SQL_FREE_DEVELOPERS = "SELECT developer.id, first_name, last_name, qualification FROM team.developer INNER JOIN team.user ON team.developer.user_id = team.user.id WHERE task_id = 1";
    private static final String SQL_CUSTOMER_ID = "SELECT id FROM team.customer WHERE user_id = ?";
    private static final String SQL_CUSTOMER_TASKS = "SELECT task.info, project.date, task.number, project.cipher FROM team.task INNER JOIN team.project ON team.task.project_id = team.project.id WHERE team.project.customer_id = ?";
    private static final String SQL_GET_ALL_TASKS = "SELECT task.id, info, date, number, cipher FROM team.task INNER JOIN team.project ON team.task.project_id = team.project.id";
    private static final String SQL_ASSIGN_DEVELOPER = "UPDATE team.developer SET task_id = ? WHERE id = ?";
    private static final String SQL_ADD_TASK = "INSERT INTO team.task (number, info, project_id) VALUES (?, ?, ?)";
    private static final String SQL_DELETE_TASK_BY_ID = "DELETE FROM team.task WHERE id = ?";

    public String getInfo(String login) {
        String info = "";
        int userId = 0;
        int taskId = 0;
        try {
            this.connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_GET_USER_ID);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getInt(1);
            }
            statement = connection.prepareStatement(SQL_GET_TASK_ID);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                taskId = resultSet.getInt(1);
            }
            statement = connection.prepareStatement(SQL_GET_TASK_INFO);
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

    public String getDate(String login) {
        String date = "";
        int userId = 0;
        int taskId = 0;
        int projectId = 0;
        try {
            this.connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_GET_USER_ID);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getInt(1);
            }
            statement = connection.prepareStatement(SQL_GET_TASK_ID);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                taskId = resultSet.getInt(1);
            }
            statement = connection.prepareStatement(SQL_GET_PROJECT_ID);
            statement.setInt(1, taskId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                projectId = resultSet.getInt(1);
            }
            statement = connection.prepareStatement(SQL_GET_PROJECT_DATE);
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

    public List<Task> getAllTasks() throws DAOException {
        List<Task> list = new ArrayList<>();
        try {
            this.connection = getConnection();
            PreparedStatement stm = connection.prepareStatement(SQL_GET_ALL_TASKS);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Task t = new Task();
                t.setId(rs.getInt("id"));
                t.setInfo(rs.getString("info"));
                t.setDate(rs.getString("date"));
                t.setNumber(rs.getInt("number"));
                t.setCipher(rs.getString("cipher"));
                list.add(t);
            }
        } catch (SQLException e) {
            throw new DAOException("Problem with DAO request", e);
        } finally {
            try {
                super.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return list;
    }

    public List<Task> getCustomerTasks(int userId) throws DAOException {
        List<Task> list = new ArrayList<>();
        int customerId = 0;
        try {
            this.connection = getConnection();
            PreparedStatement stm = connection.prepareStatement(SQL_CUSTOMER_ID);
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                customerId = rs.getInt(1);
            }
            stm = connection.prepareStatement(SQL_CUSTOMER_TASKS);
            stm.setInt(1, customerId);
            rs = stm.executeQuery();
            while (rs.next()) {
                Task t = new Task();
                t.setInfo(rs.getString("info"));
                t.setDate(rs.getString("date"));
                t.setNumber(rs.getInt("number"));
                t.setCipher(rs.getString("cipher"));
                list.add(t);
            }
        } catch (SQLException e) {
            throw new DAOException("Problem with DAO request", e);
        } finally {
            try {
                super.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return list;
    }

    public List<User> getFreeDevelopers() throws DAOException {
        List<User> list = new ArrayList<>();
        try {
            this.connection = getConnection();
            PreparedStatement stm = connection.prepareStatement(SQL_FREE_DEVELOPERS);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                StringBuilder name = new StringBuilder();
                name.append(rs.getString("first_name"));
                name.append(" ");
                name.append(rs.getString("last_name"));
                u.setName(name.toString());
                u.setInfo(rs.getString("qualification"));
                list.add(u);
            }
        } catch (SQLException e) {
            throw new DAOException("Problem with DAO request", e);
        } finally {
            try {
                super.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return list;
    }

    public void assign(int taskId, int developerId) throws DAOException {
        try {
            this.connection = getConnection();
            PreparedStatement stm = connection.prepareStatement(SQL_ASSIGN_DEVELOPER);
            stm.setInt(1, taskId);
            stm.setInt(2, developerId);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Problem with DAO request", e);
        } finally {
            try {
                super.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    }

    public void add(Task task) throws DAOException {
        try {
            this.connection = getConnection();
            PreparedStatement stm = connection.prepareStatement(SQL_ADD_TASK);
            stm.setInt(1, task.getNumber());
            stm.setString(2, task.getInfo());
            stm.setInt(3, task.getProjectId());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Problems with DAO request", e);
        } finally {
            try {
                super.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    }

    public void delete(int id) throws DAOException {
        try {
            this.connection = getConnection();
            PreparedStatement stm = connection.prepareStatement(SQL_DELETE_TASK_BY_ID);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Problems with DAO request", e);
        } finally {
            try {
                super.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    }
}
