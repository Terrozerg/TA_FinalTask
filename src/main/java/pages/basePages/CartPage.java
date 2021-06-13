package pages.basePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage<CartPage> {

    @FindBy(className = "cart-bucket-lineitem")
    private WebElement cartItemContainer;

    @FindBy(xpath = "//button[@data-test-id='cta-top']")
    private WebElement goToCheckoutButton;

    @FindBy(xpath = "//button[@data-test-id='cart-remove-item']")
    private WebElement removeButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected CartPage getThis() {
        return this;
    }

    public String getCartItemText(){
        return cartItemContainer.findElement(By.tagName("a")).getText();
    }

    public boolean checkCartIsNotEmpty(){
        waitForElementToLoad(cartItemContainer, DEFAULT_WAIT_TIME);

        return cartItemContainer.isDisplayed();
    }

    public boolean checkGoToCheckoutButtonVisibility(){
        return goToCheckoutButton.isDisplayed();
    }

    public boolean checkRemoveButtonVisibility(){
        return removeButton.isDisplayed();
    }
}
