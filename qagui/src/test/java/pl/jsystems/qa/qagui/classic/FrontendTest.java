package pl.jsystems.qa.qagui.classic;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pl.jsystems.qa.qagui.classic.functional.ConfigFrontEnd;
import pl.jsystems.qa.qagui.classic.functional.LoginFunction;
import pl.jsystems.qa.qagui.classic.page.*;
import pl.jsystems.qa.qagui.classic.page.modules.CommentModule;
import pl.jsystems.qa.qagui.config.GuiConfig;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static com.google.common.truth.Truth.assertThat;
import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("frontend")
@DisplayName("Frontend test")
public class FrontendTest extends ConfigFrontEnd {

    //private WebDriver driver;

    @Tag("login")
    @DisplayName("Login test")
    @Test
    public void loginTest() {
        driver.get("https://wordpress.com/");

        LoginFunction loginFunction = new LoginFunction(driver);
        loginFunction.login();

        MainUserPage mainUserPage = new MainUserPage(driver);

        String welcomeText = mainUserPage.welcomeText.getText();

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

        assertThat(userName).isEqualTo(GuiConfig.LOGIN);

    }

    @DisplayName("Check save button on user profile page.")
    @Test
    public void saveButton() {
        driver.get(GuiConfig.BASE_URL);

        LoginFunction loginFunction = new LoginFunction(driver);
        loginFunction.login();

        MainUserPage mainUserPage = new MainUserPage(driver);
        mainUserPage.userAvatar.click();

        UserProfilePage userProfilePage = new UserProfilePage(driver);

        assertTrue(userProfilePage.saveButton.isDisplayed());
        assertFalse(userProfilePage.saveButton.isEnabled());

    }

    @DisplayName("Check selected element.")
    @Test
    public void selectedElement() throws InterruptedException {
        driver.get(GuiConfig.BASE_URL);

        LoginFunction loginFunction = new LoginFunction(driver);
        loginFunction.login();

        MainUserPage mainUserPage = new MainUserPage(driver);

        mainUserPage.waitForElementToBeClickable(mainUserPage.userAvatar);
        mainUserPage.userAvatar.click();

        UserProfilePage userProfilePage = new UserProfilePage(driver);

        int j = 0;
        boolean displayed = false;
        while (!displayed) {
            System.out.println("++++++++++" + j++);
            try {
                userProfilePage.notificationLabel.click();
                displayed = true;
            } catch (Exception e) {
                displayed = false;
                try {
                    sleep(500);
                } catch (InterruptedException ex) {
                    e.printStackTrace();
                }
                mainUserPage.userAvatar.click();
            }
        }

        NotificationUserPage notificationUserPage = new NotificationUserPage(driver);
        notificationUserPage.getCommentsLabel().click();

        CommentModule commentModule = notificationUserPage.getCommentModule();

//        assertTrue(commentModule.likeRingCheckbox.isSelected());
//        commentModule.waitForElementToBeClickable(commentModule.likeRingCheckbox, 10);
//        commentModule.likeRingCheckbox.click();
//        commentModule.waitForElementToBeUnSelected(commentModule.likeRingCheckbox);
//        assertFalse(commentModule.likeRingCheckbox.isSelected());
//        commentModule.waitForElementToBeClickable(commentModule.likeRingCheckbox, 10);
//        commentModule.likeRingCheckbox.click();
//        assertTrue(commentModule.likeRingCheckbox.isSelected());

        int counter = 0;
        boolean selected = false;
        while (!selected & counter < 20) {
            sleep(500);
            if (commentModule.likeRingCheckbox.isSelected()) {
                selected = true;
            } else {
                commentModule.likeRingCheckbox.click();
            }
            counter++;
        }

        if (commentModule.saveSettingsButton.isEnabled()) {
            commentModule.saveSettingsButton.click();
        }

        assertTrue(commentModule.likeRingCheckbox.isSelected());
        commentModule.likeRingCheckbox.click();

        if (commentModule.saveSettingsButton.isEnabled()) {
            commentModule.saveSettingsButton.click();
        }

        counter = 0;
        boolean selected2 = true;
        while (selected2 & counter < 20) {
            sleep(500);
            if (!commentModule.likeRingCheckbox.isSelected()) {
                selected2 = false;
            } else {
                commentModule.likeRingCheckbox.click();
            }
            counter++;
        }

        commentModule.saveSettingsButton.click();

        assertFalse(commentModule.likeRingCheckbox.isSelected());

        commentModule.likeRingCheckbox.click();

        if (commentModule.saveSettingsButton.isEnabled()) {
            commentModule.saveSettingsButton.click();
        }

        counter = 0;
        boolean selected3 = false;
        while (!selected3 & counter < 20) {
            sleep(500);
            if (commentModule.likeRingCheckbox.isSelected()) {
                selected3 = true;
            } else {
                commentModule.likeRingCheckbox.click();
            }
            counter++;
        }

        if (commentModule.saveSettingsButton.isEnabled()) {
            commentModule.saveSettingsButton.click();
        }

        assertTrue(commentModule.likeRingCheckbox.isSelected());

    }

}