package pages.basePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class WatchlistPage extends BasePage<WatchlistPage> {

    @FindBy(xpath = "//div[@class='m-item']//a[@class='title']")
    private List<WebElement> watchlistItems;

    @FindBy(xpath = "//input[@data-itemid]")
    private List<WebElement> watchlistItemsCheckboxes;

    @FindBy(xpath = "//button[@data-template='DELETE_ITEMS_TEMPLATE']")
    private WebElement deleteItemsButton;

    public WatchlistPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected WatchlistPage getThis() {
        return this;
    }

    public List<String> getWatchlistItemsText(){
        waitForElementsToLoad(watchlistItems, DEFAULT_WAIT_TIME);

        return watchlistItems.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public WatchlistPage selectWatchlistItem(int n){
        watchlistItemsCheckboxes.get(n).click();

        return getThis();
    }

    public WatchlistPage clickDeleteItemsButton(){
        waitForElementToBeClickable(deleteItemsButton, DEFAULT_WAIT_TIME);

        deleteItemsButton.click();

        return getThis();
    }
}
