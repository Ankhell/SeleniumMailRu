package me.ankhell.selenMail;

import me.ankhell.selenMail.config.Config;
import me.ankhell.selenMail.pageObjects.LoginPageActions;
import me.ankhell.selenMail.pageObjects.MainPageActions;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.Random;

public class TestHelpers {

    public static Config.Email getRandomEmail(){
        Random random = new Random();
        return Config.emailList.get(random.nextInt(Config.emailList.size()));
    }

    public static void sendMail(WebDriver driver, String recipient, @NotNull Config.Message message){
        MainPageActions mainPage = PageFactory.initElements(driver,MainPageActions.class);
        mainPage.clickNewMessage();
        mainPage.setMessageRecipient(recipient);
        mainPage.setMessageTopic(message.topic);
        mainPage.setMessage(message.messageText);
        mainPage.altSendMessage();
        mainPage.isSentSuccessVisible();
    }

    public static void correctDataLogIn(WebDriver driver, @NotNull Config.Email mail){
        LoginPageActions loginPage = PageFactory.initElements(driver,LoginPageActions.class);

        loginPage.typeUsername(mail.username);
        loginPage.clickSubmit();
        loginPage.typePassword(mail.password);
        loginPage.clickSubmit();
        loginPage.isUsernameOutputVisible();
    }

    public static void logOut(WebDriver driver){
        MainPageActions mainPage = PageFactory.initElements(driver,MainPageActions.class);
        mainPage.logout();
    }
}
