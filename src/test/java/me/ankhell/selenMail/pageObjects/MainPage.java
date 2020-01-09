package me.ankhell.selenMail.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

abstract class MainPage {

    WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css = "span[data-title-shortcut = \"N\"]")
    protected WebElement newMailButton;

    @FindBy(css = "input[tabindex = \"100\"]")
    protected WebElement mailToBox;

    @FindBy(css = "input[tabindex = \"400\"]")
    protected WebElement mailTopicBox;

    @FindBy(css = "div[tabindex = \"505\"]")
    protected WebElement messageBox;

    @FindBy(css = "span[tabindex = \"570\"]")
    protected WebElement sendMailButton;

    @FindBy (css = "span[class=\"layer-sent-page__contact-item\"]")
    protected WebElement sentSuccess;

    @FindBy (id = "app-loader")
    protected WebElement appLoader;

    @FindBy (css = ".badge_transparent > span:nth-child(1)")
    protected WebElement newMsgCounter;

}
