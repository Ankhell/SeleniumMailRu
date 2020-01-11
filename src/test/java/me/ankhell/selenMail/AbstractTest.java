package me.ankhell.selenMail;

import me.ankhell.selenMail.Config.Config;
import me.ankhell.selenMail.Utils.SeleniumDriverManager;
import me.ankhell.selenMail.pageObjects.LoginPageActions;
import me.ankhell.selenMail.pageObjects.MainPageActions;

import static me.ankhell.selenMail.TestHelpers.*;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public abstract class AbstractTest {

    private final static String CONFIG_FILENAME = "config.cfg";
    private final static String DB_CONFIG_FILENAME = "dbconnection.scfg";
    WebDriver driver;
    SeleniumDriverManager driverManager;


    @BeforeClass
    public void classInit() {
        Config.getConfigFromFile(CONFIG_FILENAME, DB_CONFIG_FILENAME);
        driverManager = new SeleniumDriverManager();
    }

    @BeforeMethod
    public abstract void methodInit();

    @Test(description = "Login test with correct username and password")
    public void loginTest() {
        Config.Email mail = getRandomEmail();

//        LoginPageActions loginPage = PageFactory.initElements(driver, LoginPageActions.class);
//        loginPage.typeUsername(mail.username);
//        loginPage.clickSubmit();
//        if (!loginPage.isPasswordInputVisible()) {
//            Assert.fail("Can't find password input field");
//        }
//        loginPage.typePassword(mail.password);
//        loginPage.clickSubmit();
//        Assert.assertTrue(loginPage.isUsernameOutputVisible());
        correctDataLogIn(driver,mail);
        logOut(driver);
//        MainPageActions mainPage = PageFactory.initElements(driver, MainPageActions.class);
//        mainPage.logout();
    }

    @Test(description = "Trying to login with incorrect username")
    public void invalidLoginTest() {
        LoginPageActions loginPage = PageFactory.initElements(driver, LoginPageActions.class);
        loginPage.typeUsername("Incorrect username");
        loginPage.clickSubmit();
        Assert.assertTrue(loginPage.isErrorFooterVisible());
    }

    @Test(description = "Trying to login with correct username, but incorrect password")
    public void invalidPasswordTest() {
        LoginPageActions loginPage = PageFactory.initElements(driver, LoginPageActions.class);
        Config.Email mail = getRandomEmail();
        loginPage.typeUsername(mail.username);
        loginPage.clickSubmit();
        if (!loginPage.isPasswordInputVisible()) {
            Assert.fail("Can't find password input field");
        }
        loginPage.typePassword("Incorrect password");
        loginPage.clickSubmit();
        Assert.assertTrue(loginPage.isErrorFooterVisible());
    }

    @Test(description = "Check if email is sent")
    public void sendMailTest() {

        Config.Email mail1 = getRandomEmail();
        Config.Email mail2 = getRandomEmail();

        correctDataLogIn(driver, mail1);

        sendMail(driver, mail2.username, Config.message);
        MainPageActions mainPage = PageFactory.initElements(driver, MainPageActions.class);
        Assert.assertTrue(mainPage.isSentSuccessVisible());
        logOut(driver);
    }

    @Test(description = "Inbox counter updated = > Message delivered")
    public void messageDeliveredTest() {

        int msgCounter = 0;

        Config.Email email1 = getRandomEmail();
        Config.Email email2;
        do {
            email2 = getRandomEmail();
        } while (email1.equals(email2));

        correctDataLogIn(driver,email1);
        MainPageActions mainPage = PageFactory.initElements(driver, MainPageActions.class);
        msgCounter = mainPage.getNewMsgCount();
        mainPage.logout();
        correctDataLogIn(driver,email2);
        sendMail(driver,email1.username,Config.message);
        logOut(driver);
        correctDataLogIn(driver,email1);
        mainPage = PageFactory.initElements(driver, MainPageActions.class);
        Assert.assertTrue(mainPage.getNewMsgCount() > msgCounter);
        logOut(driver);

    }

    @Test
    public void exactMessageDeliveredTest() {
        final Random random = new Random();
        int UniqueKey = random.nextInt();
        String UniqueString = Integer.toString(UniqueKey);
        Config.Email email1 = getRandomEmail();
        Config.Email email2;
        Config.Message message = new Config.Message();
        message.topic = Config.message.topic;
        message.messageText = Config.message.messageText + " " + UniqueString;
        do {
            email2 = getRandomEmail();
        } while (email1.equals(email2));

        correctDataLogIn(driver,email2);
//        MainPageActions mainPage = PageFactory.initElements(driver, MainPageActions.class);
//        mainPage.clickNewMessage();
//        mainPage.setMessageRecipient(email1.username);
//        mainPage.setMessageTopic(message.topic);
//        mainPage.setMessage(message.messageText);
//        mainPage.altSendMessage();
//        mainPage.isSentSuccessVisible();
        sendMail(driver,email1.username,message);
//        mainPage.logout();
        logOut(driver);
        correctDataLogIn(driver,email1);
        MainPageActions mainPage = PageFactory.initElements(driver, MainPageActions.class);
        mainPage.sendKeysToSearchBar(UniqueString);
        try {
            String xpath = "//span[.='" + UniqueString + "']";
            By found = By.xpath(xpath);
            WebElement element = driver.findElement(found);
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(element));
            Assert.assertTrue(element.isDisplayed());
        } catch (NoSuchElementException exc) {
            Assert.fail("Message not found");
        }
        logOut(driver);
    }

    @AfterClass
    public void classDestructor() {
        driverManager.closeAll();
    }


}
