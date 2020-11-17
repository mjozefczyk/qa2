package pl.jsystems.qa.qagui.classic;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.jsystems.qa.qagui.classic.functional.LoginFunction;
import pl.jsystems.qa.qagui.classic.page.LoginPage;
import pl.jsystems.qa.qagui.classic.page.MainUserPage;
import pl.jsystems.qa.qagui.classic.page.MainWordpressPage;
import pl.jsystems.qa.qagui.classic.page.UserProfilePage;
import pl.jsystems.qa.qagui.config.GuiConfig;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static com.google.common.truth.Truth.assertThat;
import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Tag("frontend")
@DisplayName("Frontend test")
public class FrontendTest {

    private WebDriver driver;

    @BeforeAll
    public static void setUpBefore() {
    }

    @BeforeEach
    public void setUpEach() {
        try {
            System.setProperty("webdriver.chrome.driver", Paths.get(getClass().getClassLoader().getResource("drivers/chromedriver.exe").toURI()).toFile().getAbsolutePath());
            System.setProperty("webdriver.gecko.driver", Paths.get(getClass().getClassLoader().getResource("drivers/geckodriver.exe").toURI()).toFile().getAbsolutePath());
//            System.setProperty("webdriver.edge.driver", Paths.get(getClass().getClassLoader().getResource("drivers/msedgedriver.exe").toURI()).toFile().getAbsolutePath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        driver = new ChromeDriver();
//        driver = new FirefoxDriver();
//        driver = new SafariDriver();
//        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

    }

    @Tag("Front")
    @DisplayName("Login test")
    @Test
    public void loginTest() {
        driver.get(GuiConfig.BASE_URL);
        LoginFunction loginFunction = new LoginFunction(driver);
        loginFunction.login();

        MainUserPage mainUserPage = new MainUserPage(driver);
        mainUserPage.userAvatar.click();

        String welcomeText = mainUserPage.userName.getText();

        assertThat(welcomeText).isEqualTo("Witaj w Czytniku");

    }

    @DisplayName("Check user")
    @Test
    public void checkUser() {
        driver.get(GuiConfig.BASE_URL);

        LoginFunction loginFunction = new LoginFunction(driver);
        loginFunction.login();

        MainUserPage mainUserPage = new MainUserPage(driver);
        mainUserPage.userAvatar.click();

        UserProfilePage userProfilePage = new UserProfilePage(driver);
        String userName = userProfilePage.userNamePanel.getText();

        assertThat(userName).isEqualTo("testautomation112020");

    }

    @DisplayName("Check display button")
    @Test
    public void checkButton() {
        driver.get("https://wordpress.com/");
        LoginFunction loginFunction = new LoginFunction(driver);
        loginFunction.login();

        MainUserPage mainUserPage = new MainUserPage(driver);
        mainUserPage.userAvatar.click();

        UserProfilePage userProfilePage = new UserProfilePage(driver);
        assertThat(userProfilePage.userNamePanel.isDisplayed());
        assertThat(userProfilePage.userNamePanel.isDisplayed());
        assertThat(userProfilePage.notificationButton.isDisplayed());
    }

    @DisplayName("Check selected elements")
    @Test
    public void selectedElement() {
        driver.get("https://wordpress.com/");
        LoginFunction loginFunction = new LoginFunction(driver);
        loginFunction.login();

        MainUserPage mainUserPage = new MainUserPage(driver);
        mainUserPage.userAvatar.click();

        UserProfilePage userProfilePage = new UserProfilePage(driver);
        assertThat(userProfilePage.userNamePanel.isDisplayed());
        assertThat(userProfilePage.userNamePanel.isDisplayed());
        assertThat(userProfilePage.notificationButton.isDisplayed());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }


}