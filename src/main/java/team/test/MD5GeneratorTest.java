package team.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;

import team.component.MD5Generator;

public class MD5GeneratorTest {
    private static final Logger LOGGER = LogManager.getLogger(MD5GeneratorTest.class);
    private MD5Generator md5Generator;

    @Before
    public void setUp() {
        md5Generator = new MD5Generator();
    }

    @After
    public void tearDown() {
        md5Generator = null;
    }

    @Test
    public void getHash() {
        try {
            LOGGER.info(md5Generator.getMD5("painter"));
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e);
        }
    }
}