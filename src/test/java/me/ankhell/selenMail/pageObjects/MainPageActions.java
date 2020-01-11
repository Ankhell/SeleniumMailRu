package me.ankhell.selenMail.pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPageActions extends MainPage {

    WebDriverWait wait;

    public MainPageActions(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, 10);
    }

    public void clickNewMessage() {
        wait.until(ExpectedConditions.invisibilityOf(appLoader));
        newMailButton.click();
    }

    public void setMessageRecipient(String recipient) {
        mailToBox.sendKeys(recipient);
    }

    public void setMessageTopic(String topic) {
        mailTopicBox.sendKeys(topic);
    }

    public void setMessage(String message) {
        messageBox.sendKeys(message);
    }

    public void clickSendMessage() {
        sendMailButton.click();
    }

    public void altSendMessage(){
        Actions actions = new Actions(driver);
        actions.keyDown(messageBox,Keys.CONTROL).sendKeys(messageBox,Keys.RETURN).build().perform();
    }

    public void logout(){
        driver.get("https://r.mail.ru/cls1074201/auth.mail.ru/cgi-bin/logout?next=1&lang=ru_RU&Page=https%3A%2F%2Fmail.ru%2F%3Ffrom%3Dlogout");
//        wait.until(ExpectedConditions.elementToBeClickable(logoutLink));
//        logoutLink.click();
    }

    public boolean isSentSuccessVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(sentSuccess));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public int getNewMsgCount() {
        String input = newMsgCounter.getText();
        if (!input.matches("^\\d+$")) {
            Point position;
            Dimension size;
            position = driver.manage().window().getPosition();
            size = driver.manage().window().getSize();
            driver.manage().window().maximize();
            wait.until(ExpectedConditions.visibilityOf(newMsgCounter));
            input = newMsgCounter.getText();
            driver.manage().window().setPosition(position);
            driver.manage().window().setSize(size);
        }
        return Integer.parseInt(input);
    }

    public void sendKeysToSearchBar(String input) {
        Point position;
        Dimension size;
        position = driver.manage().window().getPosition();
        size = driver.manage().window().getSize();
        driver.manage().window().maximize();
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
        driver.manage().window().setPosition(position);
        driver.manage().window().setSize(size);
        wait.until(ExpectedConditions.visibilityOf(searchBar));
        searchBar.sendKeys(input + Keys.RETURN);
    }
}
