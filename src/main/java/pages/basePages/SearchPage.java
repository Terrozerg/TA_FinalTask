package pages.basePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class SearchPage extends BasePage<SearchPage> {

    @FindBy(xpath = "//ul//li[contains(@class,'s-item')]//a/*[contains(@class,'s-item__title')]")
    private List<WebElement> items;

    @FindBy(className = "s-message__content")
    private WebElement errorMessage;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected SearchPage getThis() {
        return this;
    }

    public void clickFirstItem() {
        waitForElementsToLoad(items, DEFAULT_WAIT_TIME);

        items.get(0).click();
    }

    public boolean checkItemsVisibility() {
        return items.stream()
                .allMatch(WebElement::isDisplayed);
    }

    public List<String> getItemsText() {
        return items.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public String getErrorMessageText(){
        return errorMessage.getText();
    }
}
