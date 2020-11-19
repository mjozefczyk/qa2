package pl.jsystems.qa.qagui.cucmber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pl.jsystems.qa.qagui.cucmber.ConfigBaseStep;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParamCucumberSteps {
    private WebDriver driver;

    public ParamCucumberSteps(ConfigBaseStep configBaseStep) {
        this.driver = configBaseStep.setUpWebDriver();
    }

    @Given("website has got title")
    public void websiteHasGotTitle(Map<String, String> dataMap) {
        String website = dataMap.get("website");
        String title = dataMap.get("title");
        driver.get(website);
        assertEquals(driver.getTitle(), title);
    }

    @Given("Name of the website {string}")
    public void nameOfTheWebsite(String arg0) { ;
        driver.get(arg0);
    }

    @Then("Title is {string}")
    public void titleIs(String arg0) {
        assertEquals(driver.getTitle(), arg0);
    }

    @Given("Name of the website is {}")
    public void nameOfTheWebsiteIsWebsite(String arg0) {
        driver.get(arg0);
    }

    @Then("Title of this website is {}")
    public void titleOfThisWebsiteIsTitle(String arg0) {
        System.out.println(driver.getTitle());
        assertEquals(driver.getTitle(), arg0);
    }

}
