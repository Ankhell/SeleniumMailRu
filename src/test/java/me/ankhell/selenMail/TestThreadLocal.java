package me.ankhell.selenMail;

import me.ankhell.selenMail.Utils.Browser;
import me.ankhell.selenMail.Utils.DriverMode;
import me.ankhell.selenMail.Utils.SeleniumDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestThreadLocal {
    ThreadLocal<SeleniumDriverManager> seleniumDriverManagerThreadLocal = new ThreadLocal<SeleniumDriverManager>();
    ThreadLocal<WebDriver> driverThreadLocal  = new ThreadLocal<WebDriver>();

    @BeforeMethod
    public void methodInit(){
        seleniumDriverManagerThreadLocal.set(new SeleniumDriverManager());
        driverThreadLocal.set(seleniumDriverManagerThreadLocal.get().getDriver(Browser.CHROME, DriverMode.PARALLEL));
    }

    @Test
    public void threadLocalTest(){
        driverThreadLocal.get().get("https://mail.ru");
        driverThreadLocal.get().close();
    }

    @Test
    public void threadLocalTest2(){
        driverThreadLocal.get().get("https://ya.ru");

    }

    @Test
    public void threadLocalTest3(){
        driverThreadLocal.get().get("https://yandex.ru");
        driverThreadLocal.get().close();

    }

    @Test
    public void threadLocalTest4(){
        driverThreadLocal.get().get("https://google.ru");
    }
}
