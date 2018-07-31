package team.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.service.ExpensesCalculator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExpensesCalculatorTest {
    private static final Logger LOGGER = LogManager.getLogger(ExpensesCalculatorTest.class);
    private ExpensesCalculator expensesCalculator;

    @Before
    public void setUp(){
        expensesCalculator = new ExpensesCalculator();
    }

    @After
    public void tearDown(){
        expensesCalculator = null;
    }

    @Test
    public void multiCalculation() {
        expensesCalculator.setQualification("JUNIOR");
        expensesCalculator.setQualification("MIDDLE");
        expensesCalculator.setQualification("SENIOR");
        LOGGER.info(expensesCalculator.calculate(10));
    }
}
