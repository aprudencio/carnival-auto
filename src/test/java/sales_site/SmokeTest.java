package sales_site;

import PageClasses.HomePageFactory;
import PageClasses.SearchPageFactory;
import TestClasses.BaseTest;
import Utils.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SmokeTest extends BaseTest {

    HomePageFactory home;
    SearchPageFactory searchPage;
    @BeforeClass
    public void beforeClass() throws IOException {
        super.beforeClass();
        home = new HomePageFactory(driver);
        searchPage = new SearchPageFactory(driver);
    }

    //Test case CC-1
    @Test
    public void HomePageLoadedCorrectlyTest(){
        home.navigateTo(baseURL);
        home.closePopUp();

        Assert.assertEquals( home.actualLogoSRC(),home.expectedLogoSRC(baseURL),"Logo is incorrect");
        Assert.assertTrue( home.isTodaysDealsLinkShown());
        //TODO create assertions for country selector, homepage search crioses, destination grid menu
    }

    //Test case CC-28
    @Test(priority = 1)
    public void SearchPerformedShowSearchPageTest(){

        var destination = home.destinationTheBahamas;
        var duration = home.duration2;

        home.setDuration(duration);
        home.setDestination(destination);
        home.performSearch();
        Assert.assertTrue(searchPage.isSearchPageLoaded(),"Search page did not load after performing search from homepage");
        Assert.assertEquals(searchPage.getExpectedDestination(destination),searchPage.getSelectedDestination(),"Destination incorrect");
        Assert.assertEquals(searchPage.getExpectedDuration(duration),searchPage.getSelectedDuration(), "Duration incorrect at search page");

    }

    //Test case CC-19
    @Test(priority = 2)
    public void VerifySearchPageResultsTest(){

        var destination = home.destinationTheBahamas;
        var duration = home.duration2;


        Assert.assertTrue(searchPage.isSearchPageLoaded(),"Search page did not load after performing search from homepage");
        Assert.assertEquals(searchPage.getExpectedDestination(destination),searchPage.getSelectedDestination(),"Destination incorrect");
        Assert.assertEquals(searchPage.getExpectedDuration(duration),searchPage.getSelectedDuration(), "Duration incorrect at search page");

        HashMap<String,Integer> o = searchPage.getMiMaxDaysDisplayed();

        //TODO need to define a map between durations and actual min/max values
        Integer max=9,min=6;
        Assert.assertTrue(o.get("max")<=max,"Days offered ("+o.get("max")+") is higher than max days selected "+max);
        Assert.assertTrue(o.get("min")>=min,"Days offered ("+o.get("min")+") is lower than max days selected "+min);

    }

    //Implementation of tests for story number 2 pending as I spent more than two hours on story 1, making this change to commit story 2

}
