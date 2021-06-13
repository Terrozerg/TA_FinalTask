package pages.basePages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObject.PageObject;

public class ItemPage extends BasePage<ItemPage> {

    @FindBy(id = "itemTitle")
    private WebElement itemTitle;

    @FindBy(xpath = "//div[@class='product ']//*[@class='product-title']")
    private WebElement productTitle;

    @FindBy(id = "binBtn_btn")
    private WebElement buyItNowButton;

    @FindBy(id = "isCartBtn_btn")
    private WebElement addToCartButton;

    @FindBy(xpath = "//div[@id='watchWrapperId']//a")
    private WebElement addToWatchlistButton;

    @FindBy(id = "prcIsum")
    private WebElement price;

    @FindBy(id = "sbin-gxo-btn")
    private WebElement checkoutAsAGuestButton;

    @FindBy(className = "smeOfferMsg")
    private WebElement promotionBanner;

    public ItemPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected ItemPage getThis() {
        return this;
    }


    public boolean checkItemTitleVisibility() {
        boolean visible;
        try {
            visible = itemTitle.isDisplayed();
        } catch (NoSuchElementException e){
            visible = productTitle.isDisplayed();
        }

        return visible;
    }

    public String getItemTitleText(){
        String text;
        try {
            text = itemTitle.getText();
        } catch (NoSuchElementException e){
            text = productTitle.getText();
        }

        return text;
    }

    public boolean checkBuyItNowButtonVisibility(){
        return buyItNowButton.isDisplayed();
    }

    public void clickBuyItNowButton() {
        buyItNowButton.click();
    }

    public boolean checkAddToCartButtonVisibility(){
        return addToCartButton.isDisplayed();
    }

    public void clickAddToCartButton() {
        addToCartButton.click();
    }

    public void clickAddToWatchlistButton() {
        waitForElementToBeClickable(addToWatchlistButton, DEFAULT_WAIT_TIME);

        addToWatchlistButton.click();
    }

    public void clickCheckoutAsAGuestButton() {
        waitForElementToBeClickable(checkoutAsAGuestButton, DEFAULT_WAIT_TIME);

        checkoutAsAGuestButton.click();
    }

    public boolean checkPriceVisibility() {
        waitForElementToLoad(price, DEFAULT_WAIT_TIME);

        return price.isDisplayed();
    }

    public boolean checkPromotionBannerVisibility(){
        if(!promotionBanner.isDisplayed()) {
            getDriver().navigate().refresh();
        }

        return promotionBanner.isDisplayed();
    }
}
