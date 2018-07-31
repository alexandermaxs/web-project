package team.service;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

/**
 * This class provides business logic
 */
public class ExpensesCalculator {
    //private static final Logger LOGGER = LogManager.getLogger(ExpensesCalculator.class);
    private double juniorMultiplier = 1;
    private double middleMultiplier = 2;
    private double seniorMultiplier = 3;
    private double baseRate = 20;
    private double qualification;

    /**
     * This method accumulates summary qualification rate for project
     * @param qualificationResponse is an expected enum from db
     */
    public void setQualification(String qualificationResponse) {
        try {
            if (qualificationResponse.equals("JUNIOR")) {
                qualification += juniorMultiplier;
            } else if (qualificationResponse.equals("MIDDLE")) {
                qualification += middleMultiplier;
            } else if (qualificationResponse.equals("SENIOR")) {
                qualification += seniorMultiplier;
            }
        } catch (IllegalArgumentException e){
            //LOGGER.error("Constant does not match ",e);
        } catch (NullPointerException e){
            //LOGGER.error("Empty field in db ",e);
        }
    }

    /**
     * This method calculates expenses
     * @param days is date difference from start to end of the project
     * @return expected summary expenses for project
     */
    public double calculate(int days){
        try {
            double sum = baseRate * qualification * 5 / 7 * days;
            return sum;
        } catch (NullPointerException e){
            //LOGGER.error("Empty field in db ",e);
            return Double.parseDouble(null);
        }
    }
}
