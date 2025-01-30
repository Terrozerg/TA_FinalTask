package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObject.PageObject;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

public class CheckoutPage extends PageObject {

    @FindBy(className = "item-title")
    private WebElement itemTitle;

    @FindBy(id = "country")
    private WebElement countryField;

    @FindBy(id = "firstName")
    private WebElement firstNameField;

    @FindBy(id = "lastName")
    private WebElement lastNameField;

    @FindBy(id = "addressLine1")
    private WebElement streetAddressField;

    @FindBy(id = "addressLine2")
    private WebElement secondaryStreetAddressField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id = "stateOrProvince")
    private WebElement stateOrProvinceField;

    @FindBy(id = "postalCode")
    private WebElement postalCodeField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "emailConfirm")
    private WebElement emailConfirmField;

    @FindBy(id = "phone-country-number")
    private WebElement phoneCountryNumberField;

    @FindBy(id = "phoneNumber")
    private WebElement phoneNumberField;

    @FindBy(xpath = "//button[@data-test-id='ADD_ADDRESS_SUBMIT']")
    private WebElement addressDoneButton;

    @FindBy(xpath = "//button[@data-test-id='ADD_CARD']")
    private WebElement cardDoneButton;

    @FindBy(xpath = "//button[@data-test-id='CANCEL']")
    private WebElement cancelButton;

    @FindBy(id = "payment-selection-fieldset")
    private WebElement paymentMethods;

    @FindBy(xpath = "//form[@id='page-form']/div[@data-test-id]")
    private WebElement confirmAndPayButton;

    @FindBy(id = "phoneNumber-error")
    private WebElement phoneNumberError;

    @FindBy(className = "error-message")
    private List<WebElement> errorMessages;

    @FindBy(xpath = "//a[@aria-label='Change shipping address']")
    private WebElement changeAddressButton;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkItemVisibility() {
        waitForElementToLoad(itemTitle, DEFAULT_WAIT_TIME);

        return itemTitle.isDisplayed();
    }

    public String getItemTitleText() {
        return itemTitle.getText();
    }

    public boolean checkShippingFieldsVisibility() {
        return countryField.isDisplayed() &&
                firstNameField.isDisplayed() &&
                lastNameField.isDisplayed() &&
                streetAddressField.isDisplayed() &&
                secondaryStreetAddressField.isDisplayed() &&
                cityField.isDisplayed() &&
                stateOrProvinceField.isDisplayed() &&
                postalCodeField.isDisplayed() &&
                emailField.isDisplayed() &&
                emailConfirmField.isDisplayed() &&
                phoneCountryNumberField.isDisplayed() &&
                phoneNumberField.isDisplayed() &&
                addressDoneButton.isDisplayed();
    }

    public boolean checkChangeAddressButtonVisibility(){
        waitForElementToLoad(changeAddressButton, DEFAULT_WAIT_TIME);

        return changeAddressButton.isDisplayed();
    }

    public boolean checkPaymentFieldsVisibility() {
        waitForElementToLoad(changeAddressButton, DEFAULT_WAIT_TIME);

        return paymentMethods.isDisplayed();
    }

    public boolean checkConfirmAndPayButtonVisibility() {
        waitForElementToLoad(confirmAndPayButton, DEFAULT_WAIT_TIME);

        return confirmAndPayButton.isDisplayed();
    }

    public CheckoutPage inputShippingInformation(String information) {
        waitForElementToLoad(firstNameField, DEFAULT_WAIT_TIME);

        String[] parsedInfo = information.split(",");

        firstNameField.clear();
        firstNameField.sendKeys(parsedInfo[0]);

        lastNameField.clear();
        lastNameField.sendKeys(parsedInfo[1]);

        streetAddressField.clear();
        streetAddressField.sendKeys(parsedInfo[2]);

        cityField.clear();
        cityField.sendKeys(parsedInfo[3]);

        postalCodeField.clear();
        postalCodeField.sendKeys(parsedInfo[4]);

        emailField.clear();
        emailField.sendKeys(parsedInfo[5]);

        emailConfirmField.clear();
        emailConfirmField.sendKeys(parsedInfo[5]);

        phoneNumberField.clear();
        phoneNumberField.sendKeys(parsedInfo[6]);

        return this;
    }

    public CheckoutPage clickAddressDoneButton() {
        addressDoneButton.click();

        return this;
    }

    public CheckoutPage selectPaymentMethod(String method) {
        waitForElementToLoad(changeAddressButton, DEFAULT_WAIT_TIME);

        paymentMethods.findElements(By.xpath("./div[contains(@class,'payment-entry')]")).stream()
                .filter(item -> item.findElement(By.tagName("input")).getAttribute("title").equalsIgnoreCase(method) ||
                        item.getText().equalsIgnoreCase(method))
                .findFirst()
                .orElseThrow(NoSuchElementException::new)
                .click();

        return this;
    }

    public boolean checkBuyWithPaymentMethodButtonVisibility(String method) {
        waitForElementToBeClickable(confirmAndPayButton, DEFAULT_WAIT_TIME);

        switch (method) {
            case "Google Pay": {
                String[] text = confirmAndPayButton.getAttribute("id").split("-");
                return Arrays.stream(text)
                        .anyMatch(item->method.toLowerCase(Locale.ROOT).contains(item));
            }
            case "PayPal": {
                System.out.println(confirmAndPayButton.getText());
                return confirmAndPayButton.getText().contains(method);
            }
        }

        return false;
    }

    public CheckoutPage clickCardDoneButton() {
        cardDoneButton.click();

        return this;
    }

    public boolean checkPhoneNumberErrorVisibility() {
        waitForElementToLoad(phoneNumberError, DEFAULT_WAIT_TIME);

        return phoneNumberError.isDisplayed();
    }

    public CheckoutPage inputPhoneNumber(String phoneNumber) {
        phoneNumberField.clear();
        phoneNumberField.sendKeys(phoneNumber);

        return this;
    }

    public boolean checkCardErrorMessagesVisibility() {
        return errorMessages.stream()
                .allMatch(WebElement::isDisplayed);
    }
}
