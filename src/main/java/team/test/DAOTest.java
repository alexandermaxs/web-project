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
import team.database.dao.UserDAO;
import team.exception.DAOException;
import team.exception.InvalidArgumentException;
import team.exception.PersistentException;
import team.model.bean.Project;
import team.model.bean.User;

public class DAOTest {
    private static final Logger LOGGER = LogManager.getLogger(DAOTest.class);
    private User user;
    private UserDAO userDAO;
    private Project project;
    private ProjectDAO projectDAO;

    @Before
    public void setUp() {
        userDAO = new UserDAO();
        user = new User("vovik666","pass999/111");
        project = new Project();
        projectDAO = new ProjectDAO();
    }

    @After
    public void tearDown() {
        userDAO = null;
        user = null;
        projectDAO = null;
        project = null;
    }

    @Test
    public void createProject(){
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
    public void checkUser(){
        try {
            userDAO.create(user);
            LOGGER.info("insertion is successful");
            System.out.println(userDAO.isUserExist("admin"));
            if (userDAO.getUserByLoginPassword("admin", "21232f297a57a5a743894a0e4a801fc3") != null) {
                System.out.println(userDAO.getUserByLoginPassword("admin", "21232f297a57a5a743894a0e4a801fc3").getLogin());
            }
        } catch (PersistentException e) {
            LOGGER.error(e);
        } catch (DAOException e) {
            LOGGER.error(e);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e);
        } catch (InvalidArgumentException e) {
            LOGGER.error(e);
        }
    }

    @Test
    public void roughConnection(){
        Connection connection = null;
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/team","root","ABLE112yjhk&^39.");
            LOGGER.info("database connection established" );
        } catch (ClassNotFoundException e) {
            LOGGER.error(e);
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            try {
                if (connection != null) {connection.close();}
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    }
}