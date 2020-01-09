package me.ankhell.selenMail.pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

abstract class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.driver.get("https://account.mail.ru/login?page");
    }

    @FindBy (name = "Login")
    protected WebElement usernameInput;

    @FindBy (name = "Password")
    protected WebElement passwordInput;

    @FindBy (css = "button[type=submit]")
    protected WebElement submitButton;

    @FindBy (id = "PH_user-email")
    protected WebElement usernameOutput;

    @FindBy (css = "div[data-test-id=\"error-footer-text\"]")
    protected WebElement errorFooter;

}
