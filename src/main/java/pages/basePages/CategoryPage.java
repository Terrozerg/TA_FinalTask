package pages.basePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;

public class CategoryPage extends BasePage<CategoryPage> {

    @FindBy(className = "b-guidancecard__title")
    private List<WebElement> subCategories;

    @FindBy(xpath = "//div[@class='x-carousel carousel-toggle']//div[@class='b-info']")
    private List<WebElement> limitedDeals;

    @FindBy(className = "b-brand-banner__right-btn")
    private List<WebElement> shopNowButton;

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected CategoryPage getThis() {
        return this;
    }

    public void clickSubCategory(String name) {
        WebElement element = subCategories.stream()
                .peek(this::scrollIntoView)
                .filter(item -> item.getText().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);

        clickElement(element);
    }

    public boolean checkSubCategoriesVisibility() {
        return subCategories.stream()
                .peek(item->getActions().moveToElement(item).build().perform())
                .allMatch(WebElement::isDisplayed);
    }

    public boolean checkLimitedDealsVisibility(){
        return limitedDeals.stream()
                .peek(item->getActions().moveToElement(item).build().perform())
                .allMatch(WebElement::isDisplayed);
    }

    public boolean checkShopNowButtonVisibility(){
        return shopNowButton.get(0).isDisplayed();
    }

    public void clickShopNowButton(){
        if(shopNowButton.size() > 0) {
            shopNowButton.get(0).click();
        }
    }
}
