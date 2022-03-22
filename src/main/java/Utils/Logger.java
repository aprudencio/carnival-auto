package Utils;

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
