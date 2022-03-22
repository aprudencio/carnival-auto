package TestClasses;

import Utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    Properties prop;
    FileInputStream file;
    public String baseURL;
    public String browser;
    public Logger logger;

    private void loadProperties( ) throws IOException {
        prop= new Properties();
        //TODO create system property so env is set and then corresponding properties file is read
        file=new FileInputStream("src/main/java/Resources/prod.data.properties");
        prop.load(file);
        baseURL = prop.getProperty("baseURL");
        System.out.println("Using baseURL: "+baseURL);
        browser = System.getProperty("browser");

    }

    public WebDriver initializeDriver() throws IOException {
        loadProperties();
        WebDriver driver = null;
        if(browser==null) browser="chrome";
        System.out.println("On browser: "+browser);
        switch (browser){//Setting system properties of ChromeDriver
            case "firefox": break;
            case "ie11": break;
            default:
                //default browser is Chrome
                System.setProperty("webdriver.chrome.driver", "E://Docs//2022//CARNIVAL//chromedriver.exe");
                driver = new ChromeDriver();
                break;
        }

        //apply driver defaults here
        //maximize window
        driver.manage().window().maximize();
        //Deleting all the cookies
        driver.manage().deleteAllCookies();

        return driver;
    }

    @BeforeClass
    public void beforeClass() throws IOException {
        driver = initializeDriver();
    }
}
