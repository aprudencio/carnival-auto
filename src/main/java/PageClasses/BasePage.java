package PageClasses;

import Utils.Logger;
import org.openqa.selenium.WebDriver;

/**
 * Handles common properties for Page classes such as driver and logger
 */
public class BasePage {

    /**
     * logger instance to log messages in console
     */
    Logger logger = new Logger();

    /**
     * driver used throughout the tests
     */
    WebDriver driver;
}
