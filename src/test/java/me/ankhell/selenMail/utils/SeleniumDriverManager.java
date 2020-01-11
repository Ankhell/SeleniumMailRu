package me.ankhell.selenMail.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SeleniumDriverManager {

    private static final int IMPLICITLY_TIMEOUT = 1;
    private static final TimeUnit IMPLICITLY_TIMEOUT_TIME_UNITS = TimeUnit.SECONDS;

    private final Map<Long,WebDriver> driverMap = new HashMap<>();

    public WebDriver getDriver(Browser browser){

        long tid = Thread.currentThread().getId();

        if (driverMap.containsKey(tid)){
            return driverMap.get(tid);
        } else {
            switch (browser){
                case FIREFOX:
                    driverMap.put(tid,new FirefoxDriver());
                    driverMap.get(tid).manage().timeouts().implicitlyWait(IMPLICITLY_TIMEOUT, IMPLICITLY_TIMEOUT_TIME_UNITS);
                    return driverMap.get(tid);
                case CHROME:
                    driverMap.put(tid,new ChromeDriver());
                    driverMap.get(tid).manage().timeouts().implicitlyWait(IMPLICITLY_TIMEOUT, IMPLICITLY_TIMEOUT_TIME_UNITS);
                    return driverMap.get(tid);
                default:
                    return null;
            }
        }
    }

    public void closeAll(){
        for (Map.Entry<Long,WebDriver> driver : driverMap.entrySet()){
            driver.getValue().close();
        }
        driverMap.clear();
    }
}
