package pl.jsystems.qa.qagui.classic;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.jsystems.qa.qagui.classic.functional.ConfigFrontEnd;

import java.util.Set;

@Tags({@Tag("frontend"),@Tag("Windows")})
public class WindowsTest extends ConfigFrontEnd {

    String firstPageWindow;
    String secondPagewindow;

    String urlDiary ="http://www.testdiary.com/training/selenium/selenium-test-page/";

    String openWindow = "Open page in a new window";

    @DisplayName("Window Test")
    @Test
    public void windowTest() {
        driver.get(urlDiary);
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(openWindow)));

        firstPageWindow = driver.getWindowHandle();
        WebElement hyperLink = driver.findElement(By.linkText(openWindow));

        int hyperLinkY = hyperLink.getLocation().y;
        int hyperLinkX = hyperLink.getLocation().x;

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy("+hyperLinkX+","+hyperLinkY+")","");
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(openWindow)));
        hyperLink.click();
        Set<String> windowHandles = driver.getWindowHandles();

        for (String window:windowHandles) {
            if(!firstPageWindow.equals(window)){
                secondPagewindow=window;
            }
        }
        driver.switchTo().window(secondPagewindow);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("testpagelink")));
        driver.close();
        driver.switchTo().window(firstPageWindow);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(openWindow)));
    }

    @Test
    public void pageScroll()
    {
        String contactUrl = "http://www.testdiary.com/training/selenium/selenium-test-page/";

        driver.get(contactUrl);


        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Open page in the same window")));


        int hyperlinkYCoordinate = driver.findElement(By.linkText("Open page in the same window")).getLocation().getY();
        int hyperlinkXCoordinate = driver.findElement(By.linkText("Open page in the same window")).getLocation().getX();

        JavascriptExecutor jsexecutor = (JavascriptExecutor) driver;
        jsexecutor.executeScript("window.scrollBy(" + hyperlinkXCoordinate + "," + hyperlinkYCoordinate + ")", "");

        (new WebDriverWait(driver, 100))
                .until(ExpectedConditions.elementToBeClickable(By.linkText("Open page in the same window")));

        driver.findElement(By.linkText("Open page in the same window")).click();
    }

    @Disabled
    @Test
    public void popupHanlder()
    {
        driver.switchTo().alert();
        driver.findElement(By.id("userName")).sendKeys("username");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.switchTo().alert().accept();
        driver.switchTo().defaultContent();
    }
}
