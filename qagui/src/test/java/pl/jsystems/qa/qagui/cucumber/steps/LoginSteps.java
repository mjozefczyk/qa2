package pl.jsystems.qa.qagui.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ma;
import org.openqa.selenium.WebDriver;
import pl.jsystems.qa.qagui.classic.functional.LoginFunction;
import pl.jsystems.qa.qagui.classic.page.MainUserPage;
import pl.jsystems.qa.qagui.classic.page.MainWordpressPage;
import pl.jsystems.qa.qagui.config.GuiConfig;
import pl.jsystems.qa.qagui.cucumber.ConfigBaseStep;

import static com.google.common.truth.Truth.assertThat;

public class LoginSteps {

    private WebDriver driver;

    public LoginSteps(ConfigBaseStep configBaseStep){
        this.driver=configBaseStep.setUpDriver();
    }

    @Given("User start on main page")
    public void userStartOnMainPage() {
        driver.navigate().to(GuiConfig.BASE_URL);
    }

    @When("User logs to user panel")
    public void userLogsToUserPanel() {
        LoginFunction loginFunction = new LoginFunction(driver);
        loginFunction.login();
    }

    @Then("User can modify user profile")
    public void userCanModifyUserProfile() {
        MainUserPage mainUserPage = new MainUserPage(driver);
        String welcomeText = mainUserPage.welcomeText.getText();
        assertThat(welcomeText).isEqualTo("Witaj w Czytniku");
    }
}
