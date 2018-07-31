package team.database.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.database.ConnectionPool;
import team.exception.DAOException;
import team.exception.ConnectionPoolException;


/**
 * This abstract class holds objects needed for work with database
 * Contains the method of closing the connection
 */
public abstract class AbstractDAO {

	public static final String CHARSET = "UTF-8";
	/**
	 * This object stores data obtained from the database
	 */
	protected ResultSet resultSet;
	/**
	 * This object provides connection with database
	 */
	protected static final Logger LOGGER = LogManager.getLogger(AbstractDAO.class);
	protected Connection connection;
	protected ConnectionPool connectionPool;

	/**
	 * This method tries to close <code>resultSet</code>
	 * and connection afterwards
	 * @throws ConnectionPoolException
	 */

	public Connection getConnection() throws DAOException {
		Connection connection = null;
		try {
		connectionPool = ConnectionPool.getInstance();
		connection = connectionPool.getFreeConnection();
		} catch (ConnectionPoolException e) {
			LOGGER.error(e);
			throw new DAOException("Problem with DAO connection", e);
		}
		return connection;
	}

	public void close() throws DAOException {
		try {
			if (connection!=null) {
				connectionPool.freeConnection(connection);
			}

			if (resultSet != null)
				resultSet.close();
		} catch (ConnectionPoolException | SQLException e) {
			LOGGER.error(e);
			throw new DAOException("Problem with DAO connection", e);
		}
	}
}
