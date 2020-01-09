package me.ankhell.selenMail;

import me.ankhell.selenMail.Utils.Browser;
import me.ankhell.selenMail.Utils.SeleniumDriverManager;

import org.testng.annotations.BeforeMethod;

public class TestChrome extends AbstractTest {

    @BeforeMethod
    @Override
    public void methodInit() {
//        driverManager.set(new SeleniumDriverManager());
//        System.out.println(driverManager);
        driver.set(driverManager.getDriver(Browser.CHROME,MODE));
    }
}
