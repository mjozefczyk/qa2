package pl.jsystems.qa.qagui.cucmber.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pl.jsystems.qa.qagui.config.GuiConfig;
import pl.jsystems.qa.qagui.cucmber.ConfigBaseStep;
import pl.jsystems.qa.qagui.cucmber.functional.LoginFunction;
import pl.jsystems.qa.qagui.cucmber.page.MainUserPage;
import pl.jsystems.qa.qagui.cucmber.page.SearchPage;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertTrue;

public class LoginSteps {
    private WebDriver driver;

    public LoginSteps(ConfigBaseStep configBaseStep) {
        this.driver = configBaseStep.setUpWebDriver();
    }

    @Given("User starts on main page")
    public void userStartsOnMainPage() {
        driver.navigate().to(GuiConfig.BASE_URL);
    }

    @When("User logs to the user panel")
    public void userLogsToTheUserPanel() {
        LoginFunction loginFunction = new LoginFunction(driver);
        loginFunction.login();
    }

    MainUserPage mainUserPage;
    @Then("User can modify user profile")
    public void userCanModifyUserProfile() {
        mainUserPage = new MainUserPage(driver);
        String welcomeText = mainUserPage.welcomeText.getText();
        assertThat(welcomeText).isEqualTo("Witaj w Czytniku");
    }

    @And("User checks search button")
    public void userChecksSearchButton() {
        mainUserPage = new MainUserPage(driver);
        assertTrue(mainUserPage.searchButton.isDisplayed());
    }

    @When("User clicks to search button")
    public void userClicksToSearchButton() {
        mainUserPage.searchButton.click();
    }

    @Then("User arrives to search panel")
    public void userArrivesToSearchPanel() {
        SearchPage searchPage = new SearchPage(driver);
        assertTrue(searchPage.searchInput.isDisplayed());
    }
}
