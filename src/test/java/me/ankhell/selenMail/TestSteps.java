package me.ankhell.selenMail;

import me.ankhell.selenMail.pageObjects.LoginPageActions;
import me.ankhell.selenMail.pageObjects.MainPageActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class TestSteps {

    public static boolean isLogInSuccess(String username, String password, WebDriver driver) {
        LoginPageActions loginPage = PageFactory.initElements(driver, LoginPageActions.class);
        loginPage.typeUsername(username);
        loginPage.clickSubmit();
        if (!loginPage.isPasswordInputVisible()){
            return false;
        }
        loginPage.typePassword(password);
        loginPage.clickSubmit();
        if (loginPage.isErrorFooterVisible()) return false;
        return loginPage.isUsernameOutputVisible();


    }

    public static boolean isMessageSent(String username, String password, String topic, String message, String recipient,
                                        WebDriver driver) {
        if (isLogInSuccess(username, password, driver)) {
            MainPageActions mainPage = PageFactory.initElements(driver, MainPageActions.class);
            mainPage.clickNewMessage();
            mainPage.setMessageRecipient(recipient);
            mainPage.setMessageTopic(topic);
            mainPage.setMessage(message);
            mainPage.clickSendMessage();
            return (mainPage.isSentSuccessVisible());
        } else {
            System.out.println("Logging in failed.");
            return false;
        }
    }

    public static boolean isNewMsgCounterUpdated(String username, String password, String topic, String message,
                                                 String recipient, String recipientPassword, WebDriver driver){
        int newMsgCount = 0;
        if (isLogInSuccess(recipient, recipientPassword, driver)){
            MainPageActions mainPage = PageFactory.initElements(driver,MainPageActions.class);
            newMsgCount = mainPage.getNewMsgCount();
        }
        isMessageSent(username, password, topic, message, recipient, driver);
        if (isLogInSuccess(recipient, recipientPassword, driver)){
            MainPageActions mainPage = PageFactory.initElements(driver,MainPageActions.class);
            return newMsgCount != mainPage.getNewMsgCount();
        }
        return false;
    }
}
