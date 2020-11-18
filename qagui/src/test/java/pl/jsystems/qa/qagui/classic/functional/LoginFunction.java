package pl.jsystems.qa.qagui.classic.functional;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.jsystems.qa.qagui.classic.page.LoginPage;
import pl.jsystems.qa.qagui.classic.page.MainWordpressPage;
import pl.jsystems.qa.qagui.config.GuiConfig;

import static java.lang.Thread.sleep;

public class LoginFunction {

    private WebDriver driver;

    public LoginFunction(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Loguje się")
    public void login() {
        MainWordpressPage mainWordpressPage = new MainWordpressPage(driver);
        mainWordpressPage.loginButton.click();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginInput.clear();
        loginPage.loginInput.click();
        loginPage.loginInput.sendKeys(GuiConfig.LOGIN);
        loginPage.logginButton.click();

        loginPage.waitForVisibilityOfElement(By.id(LoginPage.password), 15);

        loginPage.passwordInput.clear();
        loginPage.passwordInput.click();
        loginPage.passwordInput.sendKeys(GuiConfig.PASSWORD);
        loginPage.passwordButton.click();

    }
}