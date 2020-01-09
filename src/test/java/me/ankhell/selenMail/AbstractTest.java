package me.ankhell.selenMail;

import me.ankhell.selenMail.Utils.DriverMode;
import me.ankhell.selenMail.Utils.SeleniumDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public abstract class AbstractTest {

    private static final String LOGIN = "java.test.selenium@mail.ru";
    private static final String PASSWORD = "3ajnDdyE";
    private static final String TOPIC = "Test";
    private static final String MESSAGE = "Yet another test message";
    private static final String RECIPIENT = "java.test2.selenium@mail.ru";
    private static final String RECIPIENT_PASSWORD = "sE3iwBkK";
    static final DriverMode MODE = DriverMode.SINGLETHREAD;

    ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    SeleniumDriverManager driverManager;
    @BeforeClass
    public  void classInit(){
        driverManager = new SeleniumDriverManager();
    }

    @BeforeMethod
    public abstract void methodInit();

    @Test
    public void loginTest() {
        Assert.assertTrue(TestSteps.isLogInSuccess(LOGIN, PASSWORD, driver.get()));
    }

    @Test
    public void invalidLoginTest() {
        Assert.assertFalse(TestSteps.isLogInSuccess("invalidLogin", "somepasswd", driver.get()));
    }

    @Test
    public void invalidPasswordTest() {
        Assert.assertFalse(TestSteps.isLogInSuccess(LOGIN, "wrongpasswd", driver.get()));
    }

    @Test
    public void sendMailTest() {
        Assert.assertTrue(TestSteps.
                isMessageSent(LOGIN, PASSWORD, TOPIC, MESSAGE, RECIPIENT, driver.get()));
    }

    @Test
    public void mailDeliveredTest() {
        Assert.assertTrue(TestSteps.isNewMsgCounterUpdated(LOGIN, PASSWORD, TOPIC,
                MESSAGE, RECIPIENT, RECIPIENT_PASSWORD, driver.get()));
    }

    @AfterMethod
    public void methodDestructor(){
        driverManager.conditionalDriverClose(driver.get(),MODE,DriverMode.PARALLEL);
    }

    @AfterClass
    public void classDestructor(){
        driverManager.conditionalDriverClose(driver.get(),MODE,DriverMode.SINGLETHREAD);
    }
}

