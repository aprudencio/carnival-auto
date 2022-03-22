package PageClasses;

import Utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

public class SearchPageFactory extends BasePage {


    @FindBy(className = "sfn-nav__filter-title")
    WebElement findByLabel;

    //TODO create a new Page class for search form as component is common between search page and home page

    @FindBy(id = "cdc-durations")
    WebElement durationButton;

    @FindBy(id = "cdc-destinations")
    WebElement destinationButton;

    @FindBy(xpath = "//*[@id=\"cdc-destinations\" ]//*[contains(@class,\"cdc-filters-tab-link-inner-text\")]")
    WebElement destinationButtonText;

    @FindBy(xpath = "//*[@id=\"cdc-durations\" ]//*[contains(@class,\"cdc-filters-tab-link-inner-text\")]")
    WebElement durationButtonText;

    @FindBy(xpath = "//*[contains(@class,'cgc-cruise-glance__days')]")
    List<WebElement> displayedDays;

    public SearchPageFactory(WebDriver driver ){
        this.driver = driver;
        logger = new Logger();
        PageFactory.initElements(driver,this);
    }

    public boolean isSearchPageLoaded() {
        //TODO create additional checks
        return findByLabel.getText().equalsIgnoreCase("filter by:");
    }

    public String getSelectedDestination(){
        return destinationButtonText.getText();
    }

    /**
     * Returns the expected displayed text inside the destination button based on a given destination
     * @param destination
     * @return
     */
    public String getExpectedDestination(String destination)  {
        return "SAIL TO\n"+destination.toUpperCase();
    }

    /**
     * Returns the selected duration
     * @return returns the text of the selected duration
     */
    public String getSelectedDuration(){
        return durationButtonText.getText();
    }

    /**
     * Returns the expected displayed text inside the duration button based on a given duration
     * @param duration desired duration
     * @return expected string format for duration
     */
    public String getExpectedDuration(String duration)  {
        return "DURATION\n"+duration.toUpperCase();
    }

    public boolean isDurationSetCorrectly(String duration) {
        return getExpectedDestination(duration).equals(getSelectedDuration());
    }

    public HashMap<String,Integer> getMiMaxDaysDisplayed(){
        logger.logStep("Get max and min days displayed");
        Integer min=null, max=null;
        for (WebElement day : displayedDays){
            //logger.log("Days found: "+day.getText());
            if(day.getText().isBlank()) continue;
            Integer num = Integer.parseInt( day.getText());
            if(min==null) min=num;
            else if (num<min) min=num;
            if(max==null) max=num;
            else if (num>max) max=num;
        }
        HashMap<String,Integer> o = new HashMap<>();
        o.put("max",max);
        o.put("min",min);
        logger.log("Days found min "+min);
        logger.log("Days found max "+max);

        return o;
    }



}
