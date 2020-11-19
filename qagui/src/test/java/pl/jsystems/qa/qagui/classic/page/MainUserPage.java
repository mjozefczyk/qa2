package pl.jsystems.qa.qagui.classic.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainUserPage extends BasePage  {

    public MainUserPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "empty-content__title")
    public WebElement welcomeText;
//    public WebElement welcomeText = driver.findElement(By.className("empty-content__title"));

    public static final String USER_AVATAR = "a[data-tip-target=\"me\"]";
    @FindBy(css = USER_AVATAR)
    public WebElement userAvatar;

    @FindBy(css = "a[data-tip-target=\"reader\"]")
    public WebElement readerLabel;

}
