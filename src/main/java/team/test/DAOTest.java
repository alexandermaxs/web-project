package team.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import team.database.dao.ProjectDAO;
import team.database.dao.TaskDAO;
import team.database.dao.UserDAO;
import team.exception.DAOException;
import team.exception.InvalidArgumentException;
import team.exception.PersistentException;
import team.model.bean.Project;
import team.model.bean.User;

public class DAOTest {
    private static final Logger LOGGER = LogManager.getLogger(DAOTest.class);
    private UserDAO userDAO;
    private Project project;
    private ProjectDAO projectDAO;
    private TaskDAO taskDAO;

    @Before
    public void setUp() {
        userDAO = new UserDAO();
        project = new Project();
        projectDAO = new ProjectDAO();
        taskDAO = new TaskDAO();
    }

    @After
    public void tearDown() {
        userDAO = null;
        projectDAO = null;
        project = null;
        taskDAO = null;
    }

    @Test
    public void createProject() {
        project.setCipher("PIU21");
        project.setDate("2018-11-21");
        project.setCost(1000);
        try {
            System.out.println(projectDAO.read("RUSS1").getDate());
            projectDAO.delete("PIU20");
            projectDAO.add(project);
            System.out.println(projectDAO.getAll().toString());
        } catch (DAOException e) {
            LOGGER.error(e);
        }
    }

    @Test
    public void checkUser() {
        try {
            System.out.println(userDAO.isUserExist("admin"));
            if (userDAO.getUserByLoginPassword("admin", "21232f297a57a5a743894a0e4a801fc3") != null) {
                System.out.println(userDAO.getUserByLoginPassword("admin", "21232f297a57a5a743894a0e4a801fc3").getLogin());
            }
        } catch (DAOException | InvalidArgumentException | UnsupportedEncodingException e) {
            LOGGER.error(e);
        }
    }

    @Test
    public void Info() {
        LOGGER.info(taskDAO.getInfo("murate"));
        LOGGER.info(taskDAO.getDate("murate"));
    }

    @Test
    public void roughConnection() {
        Connection connection = null;
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/team", "root", "ABLE112yjhk&^39.");
            LOGGER.info("database connection established");
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    }
}