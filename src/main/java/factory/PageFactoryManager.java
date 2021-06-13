package factory;

import org.openqa.selenium.WebDriver;
import pages.AdvancedSearchPage;
import pages.CheckoutPage;
import pages.SignInPage;
import pages.basePages.*;

public class PageFactoryManager {
    WebDriver driver;

    public PageFactoryManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage(){
        return new HomePage(driver);
    }

    public CartPage getCartPage(){
        return new CartPage(driver);
    }

    public CategoryPage getCategoryPage(){
        return new CategoryPage(driver);
    }

    public DealsPage getDealsPage(){
        return new DealsPage(driver);
    }

    public ItemPage getItemPage(){
        return new ItemPage(driver);
    }

    public SearchPage getSearchPage(){
        return new SearchPage(driver);
    }

    public AdvancedSearchPage getAdvancedSearchPage(){
        return new AdvancedSearchPage(driver);
    }

    public WatchlistPage getWatchlistPage(){
        return new WatchlistPage(driver);
    }

    public CheckoutPage getCheckoutPage(){
        return new CheckoutPage(driver);
    }

    public SignInPage getSignInPage(){
        return new SignInPage(driver);
    }
}
