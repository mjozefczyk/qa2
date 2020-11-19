package pl.jsystems.qa.qagui.classic.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.jsystems.qa.qagui.classic.page.modules.CommentModule;

public class NotificationUserPage extends BasePage {

    private CommentModule commentModule;

    public NotificationUserPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a[href=\"/me/notifications/comments\"]")
    private WebElement commentsLabel;

    public WebElement getCommentsLabel() {
        if (commentModule == null) {
            commentModule = new CommentModule(driver);
        }
        return commentsLabel;
    }

    public CommentModule getCommentModule() {
        return commentModule;
    }

    /*
    ul.sidebar__menu a[href="/me/notifications"]
    * */
}
