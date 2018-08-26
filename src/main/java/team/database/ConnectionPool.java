package team.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.component.DBPropertyManager;
import team.exception.ExceptionConstant;
import team.exception.ConnectionPoolException;

/**
 * This class initializes connection with database
 */

public class ConnectionPool {
    private static final String DB_DRIVER = "driver";
    private static final String DB_URL = "url";
    private static final String DB_USER = "user";
    private static final String DB_PASSWORD = "password";
    private static final String DB_POOLSIZE = "poolsize";
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static ConnectionPool instance;
    private BlockingQueue<Connection> freeConnections;
    private BlockingQueue<Connection> busyConnections;
    DBPropertyManager dbPropertyManager;

    public static synchronized ConnectionPool getInstance()
            throws ConnectionPoolException {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    private ConnectionPool() throws ConnectionPoolException {
        dbPropertyManager = DBPropertyManager.getInstance();
        int size = Integer.parseInt(dbPropertyManager.getValue(DB_POOLSIZE));
        freeConnections = new ArrayBlockingQueue<>(size);
        busyConnections = new ArrayBlockingQueue<>(size);

        try {
            Class.forName(dbPropertyManager.getValue(DB_DRIVER));

            for (int i = 0; i < size; i++) {
                Connection connection = DriverManager.getConnection(
                        dbPropertyManager.getValue(DB_URL),
                        dbPropertyManager.getValue(DB_USER),
                        dbPropertyManager.getValue(DB_PASSWORD));
                freeConnections.add(connection);
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error(e);
            throw new ConnectionPoolException(ExceptionConstant.DB_DRIVER_NOT_FOUND, e);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new ConnectionPoolException(ExceptionConstant.CP_INIT_FAIL, e);
        }
    }

    public Connection getFreeConnection() throws ConnectionPoolException {
        Connection connection;
        try {
            connection = freeConnections.take();
            busyConnections.add(connection);
        } catch (InterruptedException e) {
            LOGGER.error(e);
            throw new ConnectionPoolException(ExceptionConstant.TAKING_INTERRUPTED, e);
        }
        return connection;
    }

    public void freeConnection(Connection connection)
            throws ConnectionPoolException {
        if (busyConnections.contains(connection)) {
            try {
                freeConnections.put(connection);
                busyConnections.remove(connection);
            } catch (InterruptedException e) {
                LOGGER.error(e);
            }
        } else {
            throw new ConnectionPoolException(ExceptionConstant.NONPOOLED_CONNECTION);
        }
    }

    public void closeAll() throws ConnectionPoolException {
        try {
            for (Connection freeConnection : freeConnections) {
                freeConnection.close();
            }
            for (Connection busyConnection : busyConnections) {
                busyConnection.close();
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException(ExceptionConstant.CP_CLOSE_FAIL, e);
        }
    }

}
