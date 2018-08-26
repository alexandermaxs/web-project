package team.test;

import team.component.DBPropertyManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DBPropertyManagerTest {
    private static final Logger LOGGER = LogManager.getLogger(DBPropertyManagerTest.class);
    private DBPropertyManager dbPropertyManager;

    @Before
    public void setUp() {
        dbPropertyManager = DBPropertyManager.getInstance();
    }

    @After
    public void tearDown() {
        dbPropertyManager = null;
    }

    @Test
    public void getInstance() {
        LOGGER.info(dbPropertyManager.getValue("user"));
    }
}