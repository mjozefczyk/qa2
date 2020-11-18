package pl.jsystems.qa.qagui.classic.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.jsystems.qa.qagui.classic.page.modules.Comment;

public class NotificationUserPage extends BasePage {

    public NotificationUserPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a[href=\"/me/notifications/comments\"]")
    public WebElement commentsLabel;


    /*
    ul.sidebar__menu a[href="/me/notifications"]
    * */
}
