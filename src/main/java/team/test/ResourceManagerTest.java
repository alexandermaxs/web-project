package team.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.component.ResourceManager;
import org.junit.Test;

public class ResourceManagerTest {
    private static final Logger LOGGER = LogManager.getLogger(ResourceManagerTest.class);

    @Test
    public void resourceRequest() {
        LOGGER.info(ResourceManager.getValue("message.success"));
    }
}