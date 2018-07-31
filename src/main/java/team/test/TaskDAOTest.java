package team.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.database.dao.TaskDAO;

public class TaskDAOTest {
    private static final Logger LOGGER = LogManager.getLogger(TaskDAOTest.class);
    private TaskDAO taskDAO;

    @Before
    public void setUp() throws Exception {
        taskDAO = new TaskDAO();
    }

    @After
    public void tearDown() throws Exception {
        taskDAO = null;
    }

    @Test
    public void Info() {
        LOGGER.info(taskDAO.getInfo("murate"));
        LOGGER.info(taskDAO.getDate("murate"));
    }
}