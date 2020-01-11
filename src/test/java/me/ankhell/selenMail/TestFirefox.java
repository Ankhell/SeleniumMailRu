package me.ankhell.selenMail;

import me.ankhell.selenMail.utils.Browser;
import org.testng.annotations.BeforeMethod;

public class TestFirefox extends AbstractTest {

    @BeforeMethod
    @Override
    public void methodInit() {
        driver = driverManager.getDriver(Browser.FIREFOX);
    }
}
