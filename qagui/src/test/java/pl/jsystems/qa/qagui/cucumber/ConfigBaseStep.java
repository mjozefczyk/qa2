package pl.jsystems.qa.qagui.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import pl.jsystems.qa.qagui.config.GuiConfig;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class ConfigBaseStep {

    protected WebDriver driver;

    @Before
    public void setUp()
    {

    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.println("=========================== @After Cucumber Test  =======================================");
        String status;
        if(!scenario.isFailed()) {
            status = "( ͡° ͜ʖ ͡°)";
//            status = "++++++++++";
            scenario.log("Scenario passed");
        } else {
            status = "(✖╭╮✖)";
//            status = "-------------";
            //scenario.attach(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES),"images/png");
            scenario.log("Scenario failed");
        }
        System.out.println("\n"+status+" End of: " + scenario.getName() + " scenario.");
        driver.quit();
        driver = null;
    }

    public WebDriver setUpDriver(){
        if(GuiConfig.MACHINE.equals("local"))
        {
            setLocal();
            driver = setWebDriver();
        }else
        {
            driver = setRemoteDriver();
        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        return driver;
    }

    private void setLocal() {
        try {
            System.setProperty("webdriver.chrome.driver", Paths.get(getClass().getClassLoader().getResource("drivers/chromedriver.exe").toURI()).toFile().getAbsolutePath());
            System.setProperty("webdriver.gecko.driver", Paths.get(getClass().getClassLoader().getResource("drivers/geckodriver.exe").toURI()).toFile().getAbsolutePath());
            //System.setProperty("webdriver.edge.driver", Paths.get(getClass().getClassLoader().getResource("driver/msedgedriver.exe").toURI()).toFile().getAbsolutePath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private WebDriver setWebDriver() {
        switch (GuiConfig.BROWSER) {
            case "chrome":
                return new ChromeDriver();
            case "firefox":
                return new FirefoxDriver();
            case "edge":
                return new EdgeDriver();
        }
        return new ChromeDriver();
    }

    private WebDriver setRemoteDriver() {
        DesiredCapabilities desiredCapabilities = setUpDesCapabilities();
        driver = null;
        try {
            driver = new RemoteWebDriver(new URL(GuiConfig.REMOTE_URL), desiredCapabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return driver;
    }

    private DesiredCapabilities setUpDesCapabilities() {
        DesiredCapabilities desiredCapabilities;
        desiredCapabilities = setUpRemoteBrowser();

        desiredCapabilities.setPlatform(Platform.LINUX);
        desiredCapabilities.setVersion("");
        return desiredCapabilities;
    }

    private DesiredCapabilities setUpRemoteBrowser() {
        DesiredCapabilities desiredCapabilities;
        switch (GuiConfig.BROWSER) {
            case "chrome":
                desiredCapabilities = DesiredCapabilities.chrome();
                break;
            case "firefox":
                desiredCapabilities = DesiredCapabilities.firefox();
                break;
            case "edge":
                desiredCapabilities = DesiredCapabilities.edge();
                break;
            default:
                desiredCapabilities = DesiredCapabilities.chrome();
        }
        return desiredCapabilities;
    }
}
