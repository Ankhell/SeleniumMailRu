package me.ankhell.selenMail.pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPageActions extends LoginPage {

    WebDriverWait wait;

    public LoginPageActions(WebDriver driver) {
        super(driver);
    }

    public void typeUsername(String username) {
        Actions actions = new Actions(driver);
        while (!usernameInput.getAttribute("value").isEmpty()){
            actions.keyDown(usernameInput, Keys.CONTROL)
                    .sendKeys(usernameInput, "a" + Keys.DELETE)
                    .keyUp(usernameInput, Keys.CONTROL)
                    .build()
                    .perform();
        }
//        usernameInput.sendKeys(Keys.CONTROL + "a");
//        usernameInput.sendKeys(Keys.DELETE);
        usernameInput.sendKeys(username);
    }

    public void typePassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickSubmit() {
        submitButton.click();
    }

    private boolean isVisible(WebElement element) {
        this.wait = new WebDriverWait(driver, 10);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isUsernameOutputVisible() {
        return isVisible(usernameOutput);
    }

    public boolean isPasswordInputVisible() {
        return isVisible(passwordInput);
    }

    public boolean isErrorFooterVisible() {
        return isVisible(errorFooter);
    }

    public boolean isLoginInputVisible() {
        return isVisible(usernameInput);
    }

    public void clearUsernameInput() {
        Actions actions = new Actions(driver);
        actions.keyDown(usernameInput, Keys.CONTROL).sendKeys(usernameInput, "a" + Keys.DELETE).build().perform();
    }
}
