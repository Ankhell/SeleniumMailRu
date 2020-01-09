package me.ankhell.selenMail.pageObjects;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
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
        if (!input.matches("^\\d+$")){
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
}
