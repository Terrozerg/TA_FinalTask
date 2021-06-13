package pages.basePages;

import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage<HomePage>{

    public static String homePageURL = "https://www.ebay.com/";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected HomePage getThis() {
        return this;
    }
}
