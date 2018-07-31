package team.test;

import team.service.DBResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DBResourceManagerTest {
    private static final Logger LOGGER = LogManager.getLogger(DBResourceManagerTest.class);
    private DBResourceManager dbResourceManager;

    @Before
    public void setUp(){
        dbResourceManager = DBResourceManager.getInstance();
    }

    @After
    public void tearDown(){
        dbResourceManager = null;
    }

    @Test
    public void getInstance() {
        LOGGER.info(dbResourceManager.getValue("user"));
    }
}