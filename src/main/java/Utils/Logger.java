package Utils;

/**
 * Handles all the log operations
 * Could be integrated with other logging tools
 * TODO need to call some methods statically to avoid creating instances
 */
public class Logger {

    private Integer step=1;
    public void log(String message) {
        System.out.println(message);
    }

    public void logStep(String message) {
        log(step.toString()+" "+ message);
        step++;
    }

    public void resetSteps(){
        step=0;
    }


}
