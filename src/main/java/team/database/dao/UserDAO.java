package team.database.dao;

import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import team.exception.InvalidArgumentException;
import team.exception.DAOException;
import team.exception.PersistentException;
import team.model.bean.User;
import team.model.bean.UserType;

public class UserDAO extends AbstractDAO {
	private String sqlCreateUser = "INSERT INTO team.user (login, password, role) VALUES (?, ?, ?)";
    private String sqlCheckIsUserExist = "SELECT login FROM team.user WHERE login = ?";
    private String sqlReadUserByLoginAndPass = "SELECT id, login, password, role, first_name FROM team.user WHERE login = ? and password = ?";

	public void create(User user) throws PersistentException, DAOException{
		try {
			this.connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sqlCreateUser);
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getUserType().toString());

			int count = statement.executeUpdate();
			if (count != 1) {
				throw new PersistentException("On persist modify more then 1 record: " + count);
			}
		} catch (Exception e) {
			throw new PersistentException(e);
		} finally {
			try {
				super.close();
			} catch (SQLException e) {
				LOGGER.error(e);
			}
		}
	}

	public boolean isUserExist(String login) throws DAOException {
		boolean b = false;
		try {
			this.connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sqlCheckIsUserExist);
			statement.setString(1, login);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				b = true;
			}
		} catch (SQLException e) {
			throw new DAOException("Problems with DAO request", e);
		} finally {
			try {
				super.close();
				//if (connection != null) {connection.close();}
			} catch (SQLException e) {
				//LOGGER.error(e);
			}
		}
		return b;
	}

	public User getUserByLoginPassword(String login, String password) throws  UnsupportedEncodingException, DAOException, InvalidArgumentException {
		 User user = null;
	        try {
	        	this.connection = getConnection();
	        	PreparedStatement statement = connection.prepareStatement(sqlReadUserByLoginAndPass);
	            statement.setBytes(1, login.getBytes(CHARSET));
	            statement.setBytes(2, password.getBytes(CHARSET));
	            ResultSet resultSet = statement.executeQuery();
				if (resultSet.next()) {
					user = buildUser(resultSet);
				}
	        } catch (SQLException e) {
	        	throw new DAOException("Problems with DAO request", e);
			} finally {
				try {
					super.close();
					//if (connection != null) {connection.close();}
				} catch (SQLException e) {
					//LOGGER.error(e);
				}
	        }
	        return user;
	}

	 /**
     * This method builds a user
     * This method gets data from <code>resultSet</code> and
     * sets this data to a new user
     * @return a user
     * @throws SQLException a SQLException
	 * @throws InvalidArgumentException 
     */
    private User buildUser(ResultSet resultSet) throws DAOException, InvalidArgumentException {
        User user = new User();
        try {
        user.setId(resultSet.getInt(1));
        user.setLogin(resultSet.getString(2));
        user.setUserType(UserType.getUserType(resultSet.getString(4)));
        user.setName(resultSet.getString(5));
        UserType userType = user.getUserType();
        
        if(userType.getTypeValue().equals("manager"))
			user.setAuthority(3);
		
        if(userType.getTypeValue().equals("customer"))
			user.setAuthority(2);

		if(userType.getTypeValue().equals("developer"))
			user.setAuthority(1);
        
		user.setPassword(resultSet.getString(3));
		TaskDAO taskDAO = new TaskDAO();
		user.setInfo(taskDAO.getInfo(user.getLogin()));
		user.setDate(taskDAO.getDate(user.getLogin()));
        } catch(SQLException e) {
			LOGGER.error(e);
        	throw new DAOException("Problems with DAO request", e);
        }
        return user;
    }
}
