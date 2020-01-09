package me.ankhell.selenMail.Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class SeleniumDriverManager {

    private static final int IMPLICITLY_TIMEOUT = 1;
    private static final TimeUnit IMPLICITLY_TIMEOUT_TIME_UNITS = TimeUnit.SECONDS;

    WebDriver driver;

    public WebDriver getDriver(Browser browser, DriverMode driverMode) {

        switch (driverMode) {
            case SINGLETHREAD:
                if (driver != null) {
                    return driver;
                } else {
                    return getNewDriver(browser);
                }
            case PARALLEL:
                return getNewDriver(browser);
        }
        return driver;
    }

    private WebDriver getNewDriver(Browser browser){
        switch (browser) {
            case CHROME:
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            default:
                return null;
        }
        driver.manage().timeouts().implicitlyWait(IMPLICITLY_TIMEOUT, IMPLICITLY_TIMEOUT_TIME_UNITS);
        return driver;
    }

    public void conditionalDriverClose(WebDriver driver, DriverMode driverMode, DriverMode driverCloseCondition){
        if (driverMode.equals(driverCloseCondition)) driver.close();
    }
}
