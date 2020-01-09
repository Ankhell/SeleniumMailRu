package me.ankhell.selenMail.pageObjects;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPageActions extends LoginPage {

    WebDriverWait wait;

    public LoginPageActions(WebDriver driver) {
        super(driver);
    }

    public void typeUsername(String username) {
        usernameInput.sendKeys(username);
    }

    public void typePassword(String password){
        passwordInput.sendKeys(password);
    }

    public void clickSubmit(){
        submitButton.click();
    }

    private boolean isVisible(WebElement element){
        this.wait = new WebDriverWait(driver, 1);
        try{
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (TimeoutException e){
            return false;
        }
    }

    public boolean isUsernameOutputVisible(){
        return isVisible(usernameOutput);
    }

    public boolean isPasswordInputVisible(){
        return isVisible(passwordInput);
    }

    public boolean isErrorFooterVisible(){
        return isVisible(errorFooter);
    }
}
