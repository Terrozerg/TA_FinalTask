package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObject.PageObject;

public class SignInPage extends PageObject {

    public static String signInPageURL = "https://www.ebay.com/signin/";

    @FindBy(xpath = "//input[@id='userid']")
    private WebElement loginField;

    @FindBy(xpath = "//input[@id='pass']")
    private WebElement passwordField;

    @FindBy(id = "signin-continue-btn")
    private WebElement continueButton;

    @FindBy(id = "sgnBt")
    private WebElement signInButton;

    @FindBy(id = "signin-error-msg")
    private WebElement errorMessage;

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkLoginFieldVisibility(){
        waitForElementToLoad(loginField, DEFAULT_WAIT_TIME);

        return loginField.isDisplayed();
    }

    public boolean checkPasswordFieldVisibility(){
        waitForElementToLoad(passwordField, DEFAULT_WAIT_TIME);

        return passwordField.isDisplayed();
    }

    public SignInPage inputIntoLoginField(String login){
        waitForElementToLoad(loginField, DEFAULT_WAIT_TIME);

        loginField.clear();
        loginField.sendKeys(login);

        return this;
    }

    public SignInPage clickContinueButton(){
        continueButton.click();

        return this;
    }

    public SignInPage inputIntoPasswordField(String pass){
        waitForElementToLoad(passwordField, DEFAULT_WAIT_TIME);

        passwordField.clear();
        passwordField.sendKeys(pass);

        return this;
    }

    public void clickSignInButton(){
        signInButton.click();
    }

    public String getErrorMessageText(){
        waitForElementToLoad(errorMessage, DEFAULT_WAIT_TIME);

        return errorMessage.getText();
    }
}
