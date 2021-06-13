package pages.basePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObject.PageObject;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public abstract class BasePage<T extends BasePage<T>> extends PageObject {

    @FindBy(xpath = "//form[@method='get']//input[@type='text']")
    private WebElement searchField;

    @FindBy(xpath = "//form[@method='get']//input[@type='submit']")
    private WebElement searchButton;

    @FindBy(xpath = "//a[@title='Advanced Search']")
    private WebElement advancedSearchButton;

    @FindBy(xpath = "//header//div[@id='gh-shop']")
    private WebElement categoriesButton;

    @FindBy(xpath = "//table[@id='gh-sbc']//a")
    private List<WebElement> categories;

    @FindBy(xpath = "//a[@aria-label='Your shopping cart']")
    private WebElement cartButton;

    //a[@title='Watchlist']
    @FindBy(xpath = "//span[@id='w1-7-_lmsg']//a")
    private WebElement watchlistButton;

    @FindBy(className = "gh-info__title")
    private List<WebElement> popupWatchlistItems;

    @FindBy(className = "rvi__titlelink")
    private WebElement viewAllItemsYouAreWatchingButton;

    @FindBy(xpath = "//div[@id='gh-top']//a[contains(@href,'globaldeals')]")
    private WebElement dailyDealsButton;

    //div[@id='gh-top']//button[@id='gh-ug']
    @FindBy(xpath = "//div[@id='gh-top']//*[@id='gh-ug']")
    private WebElement signInButton;

    public BasePage(WebDriver driver) {
        super(driver);
    }

    protected abstract T getThis();

    public boolean checkSearchFieldVisibility(){
        return searchField.isDisplayed();
    }

    public T inputIntoSearchField(String text){
        searchField.clear();
        searchField.sendKeys(text);

        return getThis();
    }

    public void clickSearchButton(){
        searchButton.click();
    }

    public boolean checkAdvancedSearchButtonVisibility(){
        return advancedSearchButton.isDisplayed();
    }

    public void clickAdvancedSearchButton(){
        advancedSearchButton.click();
    }

    public boolean checkCategoriesButtonVisibility(){
        return categoriesButton.isDisplayed();
    }

    public T clickCategoriesButton(){
        categoriesButton.click();

        return getThis();
    }

    public boolean checkCategoriesVisibility(){
        return categories.stream()
                .allMatch(WebElement::isDisplayed);
    }

    public void clickCategory(String category){
        categories.stream()
                .filter(item->item.getText().equalsIgnoreCase(category))
                .findFirst()
                .orElseThrow(NoSuchElementException::new)
        .click();
    }

    public boolean checkWatchlistButtonVisibility(){
        waitForElementToLoad(watchlistButton, DEFAULT_WAIT_TIME);

        return watchlistButton.isDisplayed();
    }

    public T clickWatchlistButton(){
        watchlistButton.click();

        return getThis();
    }

    public boolean checkViewAllItemsURWatchingButtonVisibility(){
        waitForElementToLoad(viewAllItemsYouAreWatchingButton, DEFAULT_WAIT_TIME);

        return viewAllItemsYouAreWatchingButton.isDisplayed();
    }

    public void clickViewAllItemsURWatchingButton(){
        viewAllItemsYouAreWatchingButton.click();
    }

    public List<String> getPopupWatchlistItemsText(){
        return popupWatchlistItems.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public boolean checkSignInButtonVisibility(){
        List<WebElement> list = signInButton.findElements(By.tagName("a"));

        return !list.isEmpty() && list.get(0).isDisplayed();
    }

    public void clickSignInButton(){
        signInButton.click();
    }

    public String getWelcomeText(){
        return signInButton.getText();
    }

    public boolean checkDailyDealsButtonVisibility(){
        return dailyDealsButton.isDisplayed();
    }

    public void clickDailyDealsButton(){
        dailyDealsButton.click();
    }
}
