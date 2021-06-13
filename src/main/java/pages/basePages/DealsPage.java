package pages.basePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DealsPage extends BasePage<DealsPage>{

    @FindBy(className = "ebayui-dne-summary-card__wrapper")
    private WebElement spotlightDeal;

    @FindBy(xpath = "//div[@class='col']//*[@itemprop='name']")
    private List<WebElement> featuredDeals;

    public DealsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected DealsPage getThis() {
        return this;
    }

    public boolean checkSpotlightDealVisibility(){
        return spotlightDeal.isDisplayed();
    }

    public boolean checkFeaturedDealsVisibility(){
        return featuredDeals.stream()
                .allMatch(WebElement::isDisplayed);
    }

    public void clickSpotlightItem(){
        spotlightDeal.findElement(By.className("ebayui-ellipsis-3")).click();
    }
}
