package PageClasses;

import Utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Page to map all the home page elements and handle interactions with this page
 */
public class HomePageFactory extends BasePage {


    public HomePageFactory(WebDriver driver ){

        this.driver = driver;
        PageFactory.initElements(driver,this);

    }

    //TODO create enums for durations and destinations or pull from datasets
    final public String duration1 = "2 - 5 Days";
    final public String duration2 = "6 - 9 Days";
    final public String duration3 = "10+ Days";

    final public String destinationTheBahamas = "The Bahamas";
    final public String destinationAlaska = "Alaska";


    final String LOGO_SRC = "/-/media/images/header2013/carnival-logo-png.png";

    @FindBy(className = "vifp-no-thankyou")
    WebElement closePopUpLink;

    @FindBy(className = "cdc-filters-search-cta")
    WebElement searchButton;

    @FindBy(className = "cgh-header__logo")
    WebElement logo;

    @FindBy(id = "cdc-durations")
    WebElement durationButton;

    @FindBy(id = "cdc-destinations")
    WebElement destinationButton;

    @FindBy(xpath = "//*[contains(@class,\"cdc-filter-button\")]")
    List<WebElement> durationOptionButtons;

    @FindBy(xpath = "//*[@filter=\"model.filterCollection.destinations\" ]//*[contains(@class,\"cdc-filter-button\")]")
    List<WebElement> destinationOptionButtons;

    @FindBy(xpath = "//*[@class=\"cgh-header__secondary-menu-right\"]//a[contains(text(),\"Today's Deals\")]")
    WebElement todaysDealsLink;

    public void navigateTo(String url) {
        logger.logStep("Access website " + url);
        driver.get(url);
    }

    public void closePopUp(){
        logger.logStep("Close pop up");
        closePopUpLink.click();
    }

    public void performSearch() {
        logger.logStep("Perform search");
        searchButton.click();
    }

    public boolean isLogoImageCorrect(String baseURL){
        logger.log("Checking log");
        logger.log(LOGO_SRC);
        String completeSRC=baseURL+LOGO_SRC;
        return completeSRC.equalsIgnoreCase(LOGO_SRC);
    }

    public String actualLogoSRC() {
        logger.log("Actual logo src is "+ logo.getAttribute("src"));
        return logo.getAttribute("src");
    }

    public String expectedLogoSRC(String baseURL) {
        logger.log("Expected logo src is "+ baseURL+LOGO_SRC);
        return baseURL+LOGO_SRC;
    }

    public boolean isTodaysDealsLinkShown(){
        return todaysDealsLink.isDisplayed();
    }

    public void setDuration(String duration) {
        logger.logStep("Set duration to "+duration);
        durationButton.click();
        for (WebElement element : durationOptionButtons) {
            if(element.getText().trim().equalsIgnoreCase(duration)) {
                element.click();
                break;
            }
        }
        durationButton.click();
    }

    public void setDestination(String destination){
        logger.logStep("Set duration to "+destination);

        destinationButton.click();
        for (WebElement element : destinationOptionButtons) {
            if(element.getText().trim().equalsIgnoreCase(destination)) {
                element.click();
                break;
            }
        }
        destinationButton.click();
    }



}
