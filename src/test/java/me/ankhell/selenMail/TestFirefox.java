package me.ankhell.selenMail;

import me.ankhell.selenMail.Utils.Browser;
import org.testng.annotations.BeforeMethod;

public class TestFirefox extends AbstractTest {

    @BeforeMethod
    @Override
    public void methodInit() {
        driver.set(driverManager.getDriver(Browser.FIREFOX, MODE));
    }
}
