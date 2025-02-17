package stepDefinitions;

import factory.PageFactoryManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;
import org.testng.Assert;
import pages.AdvancedSearchPage;
import pages.CheckoutPage;
import pages.SignInPage;
import pages.basePages.*;
import utils.CookiesLoader;
import utils.CredentialsParser;
import utils.DriverManager;

import java.util.Arrays;
import java.util.List;

public class StepDefinitions {
    WebDriver driver;
    DevTools devTools;

    PageFactoryManager pageFactoryManager;
    HomePage homePage;
    SearchPage searchPage;
    DealsPage dealsPage;
    ItemPage itemPage;
    CategoryPage categoryPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    SignInPage signInPage;
    WatchlistPage watchlistPage;
    AdvancedSearchPage advancedSearchPage;

    String savedText;

    @Before
    public void setUp() {
        driver = DriverManager.getWebDriver();
        driver.manage().window().maximize();
        devTools = DriverManager.getDevTools();

        pageFactoryManager = new PageFactoryManager(driver);
    }

    @Given("User opens item page {string}")
    public void openItemPage(String url) {
        itemPage = pageFactoryManager.getItemPage();
        itemPage.getDriver().get(url);
    }

    @Given("User opens home page")
    public void openHomePage() {
        homePage = pageFactoryManager.getHomePage();
        homePage.getDriver().get(HomePage.homePageURL);
    }

    @And("User checks search field visibility")
    public void checkSearchFieldVisibility() {
        Assert.assertTrue(homePage.checkSearchFieldVisibility());
    }

    @When("User inputs {string} in search field")
    public void inputKeywordInSearchField(String keyword) {
        homePage.inputIntoSearchField(keyword);
    }

    @And("User clicks 'Search' button")
    public void clickSearchButton() {
        homePage.clickSearchButton();
        searchPage = pageFactoryManager.getSearchPage();
    }

    @And("User checks items listing visibility")
    public void checkItemsListingVisibility() {
        Assert.assertTrue(searchPage.checkItemsVisibility());
    }

    @Then("User checks items listing contains {string}")
    public void checkItemsListingContainsKeyword(String keyword) {
        List<String> text = searchPage.getItemsText();

        Assert.assertTrue(text.stream()
                .anyMatch(item -> item.contains(keyword)));
    }

    @And("User check 'Advanced search' button visibility")
    public void checkAdvancedSearchButtonVisibility() {
        Assert.assertTrue(homePage.checkAdvancedSearchButtonVisibility());
    }

    @When("User clicks 'Advanced search' button")
    public void clickAdvancedSearchButton() {
        homePage.clickAdvancedSearchButton();

        advancedSearchPage = pageFactoryManager.getAdvancedSearchPage();
    }

    @Then("User check advanced search fields visibility")
    public void checkAdvancedSearchFieldsVisibility() {
        Assert.assertTrue(advancedSearchPage.checkFieldsVisibility());
    }

    @Then("User should see search result {string}")
    public void checkSearchResultMessage(String message) {
        Assert.assertTrue(searchPage.getErrorMessageText().contains(message));
    }

    @And("User checks 'Daily deals' button visibility")
    public void checkDailyDealsButtonVisibility() {
        Assert.assertTrue(homePage.checkDailyDealsButtonVisibility());
    }

    @When("User clicks 'Daily deals' button")
    public void clickDailyDealsButton() {
        homePage.clickDailyDealsButton();

        dealsPage = pageFactoryManager.getDealsPage();
    }

    @And("User checks spotlight item visibility")
    public void checkSpotlightItemVisibility() {
        Assert.assertTrue(dealsPage.checkSpotlightDealVisibility());
    }

    @And("User checks featured items visibility")
    public void checkFeaturedItemsVisibility() {
        Assert.assertTrue(dealsPage.checkFeaturedDealsVisibility());
    }

    @And("User clicks spotlight item")
    public void clickFirstItem() {
        dealsPage.clickSpotlightItem();

        itemPage = pageFactoryManager.getItemPage();
    }

    @And("User checks item price visibility")
    public void checkItemPriceVisibility() {
        Assert.assertTrue(itemPage.checkPriceVisibility());
    }

    @Then("User checks item is discounted")
    public void checkItemIsDiscounted() {
        Assert.assertTrue(itemPage.checkPromotionBannerVisibility());
    }

    @And("User checks 'shop by category' button visibility")
    public void checkShopByCategoryButtonVisibility() {
        Assert.assertTrue(homePage.checkCategoriesButtonVisibility());
    }

    @When("User clicks 'shop by category' button")
    public void clickShopByCategoryButton() {
        homePage.clickCategoriesButton();
    }

    @And("User checks categories visibility")
    public void checkCategoriesVisibility() {
        Assert.assertTrue(homePage.checkCategoriesVisibility());
    }

    @And("User selects {string}")
    public void selectCategory(String category) {
        homePage.clickCategory(category);

        categoryPage = pageFactoryManager.getCategoryPage();
    }

    @And("User checks sub-categories visibility")
    public void checkSubCategoriesVisibility() {
        Assert.assertTrue(categoryPage.checkSubCategoriesVisibility());
    }

    @And("User checks limited deals visibility")
    public void checkLimitedDealsVisibility() {
        Assert.assertTrue(categoryPage.checkLimitedDealsVisibility());
    }

    @And("User selects category {string}")
    public void selectSubCategory(String subCategory) {
        categoryPage.clickSubCategory(subCategory);

        searchPage = pageFactoryManager.getSearchPage();
    }

    @And("User selects category by {string}")
    public void selectSubCategoryByBrand(String brand) {
        categoryPage.clickSubCategory(brand);
    }

    @And("User clicks 'Shop now' button")
    public void clickShopNowButton() {
        categoryPage.clickShopNowButton();

        searchPage = pageFactoryManager.getSearchPage();
    }

    @And("User clicks first item in listing")
    public void clickFirstItemInListing() {
        searchPage.clickFirstItem();

        itemPage = pageFactoryManager.getItemPage();
    }

    @Then("Item name should contain selected {string}")
    public void checkItemNameContainsSelectedSubCategory(String subCategory) {
        String[] words = subCategory.split(" ");

        String text = itemPage.getItemTitleText();

        Arrays.stream(words).forEach(System.out::println);

        Assert.assertTrue(Arrays.stream(words)
                .anyMatch(text::contains));
    }

    @Then("User check item title visibility")
    public void checkItemTitleVisibility() {
        Assert.assertTrue(itemPage.checkItemTitleVisibility());

        savedText = itemPage.getItemTitleText();
    }

    @And("User checks 'Add to cart' button visibility")
    public void checkAddToCartButtonVisibility() {
        Assert.assertTrue(itemPage.checkAddToCartButtonVisibility());
    }

    @When("User adds item to cart")
    public void addItemToCart() {
        itemPage.clickAddToCartButton();

        cartPage = pageFactoryManager.getCartPage();
    }

    @And("User checks that cart is not empty")
    public void checkThatCartIsNotEmpty() {
        Assert.assertTrue(cartPage.checkCartIsNotEmpty());
    }

    @And("User checks 'Go to checkout' button visibility")
    public void checkGoToCheckoutButtonVisibility() {
        Assert.assertTrue(cartPage.checkGoToCheckoutButtonVisibility());
    }

    @And("User checks 'Remove' button visibility")
    public void checkRemoveButtonVisibility() {
        Assert.assertTrue(cartPage.checkRemoveButtonVisibility());
    }

    @And("Cart should contain this item")
    public void checkThatCartContainsCorrectItem() {
        String cartItemText = cartPage.getCartItemText();

        Assert.assertEquals(cartItemText, savedText);
    }

    @When("User clicks 'Buy it now' button")
    public void clickBuyItNowButton() {
        //savedText = itemPage.getItemTitleText();

        itemPage.clickBuyItNowButton();

        checkoutPage = pageFactoryManager.getCheckoutPage();
    }

    @And("User clicks 'Check out as guest' button")
    public void clickCheckOutAsAGuestButton() {
        itemPage.clickCheckoutAsAGuestButton();

        if (checkoutPage == null) checkoutPage = pageFactoryManager.getCheckoutPage();
        //checkoutPage = pageFactoryManager.getCheckoutPage();
    }

    @And("User checks selected item visibility")
    public void checkSelectedItemVisibility() {
        Assert.assertTrue(checkoutPage.checkItemVisibility());
    }

    @And("User checks selected item title is correct")
    public void checkSelectedItemTitleIsCorrect() {
        String title = checkoutPage.getItemTitleText();

        Assert.assertEquals(title, savedText);
    }

    @And("User checks shipping fields visibility")
    public void checkShippingFieldsVisibility() {
        Assert.assertTrue(checkoutPage.checkShippingFieldsVisibility());
    }

    @And("User checks payment fields visibility")
    public void checkPaymentFieldsVisibility() {
        Assert.assertTrue(checkoutPage.checkPaymentFieldsVisibility());
    }

    @And("User checks 'Confirm and pay' button visibility")
    public void checkConfirmAndPayButtonVisibility() {
        Assert.assertTrue(checkoutPage.checkConfirmAndPayButtonVisibility());
    }

    @And("User inputs shipping {string}")
    public void inputShippingInformation(String info) {
        checkoutPage.inputShippingInformation(info);
    }

    @And("User clicks address 'Done' button")
    public void clickAddressDoneButton() {
        checkoutPage.clickAddressDoneButton();
    }

    @And("User selects payment {string}")
    public void selectPaymentMethod(String method) {
        checkoutPage.selectPaymentMethod(method);
    }

    @Then("'Buy with {string}' button should be displayed")
    public void checkBuyWithMethodButtonDisplayed(String method) {
        Assert.assertTrue(checkoutPage.checkBuyWithPaymentMethodButtonVisibility(method));
    }

    @And("User checks phone number error message visibility")
    public void checkPhoneNumberErrorMessageVisibility() {
        Assert.assertTrue(checkoutPage.checkPhoneNumberErrorVisibility());
    }

    @And("User inputs {string}")
    public void inputPhoneNumber(String number) {
        checkoutPage.inputPhoneNumber(number);
    }

    @And("User clicks card 'Done' button")
    public void clickCardDoneButton() {
        checkoutPage.clickCardDoneButton();
    }

    @Then("User check card error messages visibility")
    public void checkCardErrorMessagesVisibility() {
        Assert.assertTrue(checkoutPage.checkCardErrorMessagesVisibility());
    }

    @And("User checks 'Sign in' button visibility")
    public void checkSignInButtonVisibility() {
        Assert.assertTrue(homePage.checkSignInButtonVisibility());
    }

    @When("User clicks 'Sign in' button")
    public void clickSignInButton() {
        homePage.clickSignInButton();

        signInPage = pageFactoryManager.getSignInPage();
    }

    @And("User checks Login field visibility")
    public void checkLoginFieldVisibility() {
        Assert.assertTrue(signInPage.checkLoginFieldVisibility());
    }

    @And("User enters login {string}")
    public void enterValidLogin(String login) {
        signInPage.inputIntoLoginField(login);
    }

    @And("User clicks Continue button")
    public void clickContinueButton() {
        signInPage.clickContinueButton();
    }

    @And("User checks Password field visibility")
    public void checkPasswordFieldVisibility() {
        Assert.assertTrue(signInPage.checkPasswordFieldVisibility());
    }

    @And("User enters password {string}")
    public void enterValidPassword(String password) {
        signInPage.inputIntoPasswordField(password);
    }

    @And("User clicks password 'Sign in' button")
    public void clickPasswordSignInButton() {
        signInPage.clickSignInButton();

        homePage = pageFactoryManager.getHomePage();
    }

    @Then("User should see welcome {string}")
    public void checkWelcomeMessage(String message) {
        Assert.assertEquals(homePage.getWelcomeText(), message);
    }

    @Then("User should see error {string}")
    public void checkErrorMessage(String error) {
        Assert.assertEquals(signInPage.getErrorMessageText(), error);
    }

    @Given("User is signed into account")
    public void signIntoAccount() {
        homePage = pageFactoryManager.getHomePage();
        homePage.getDriver().get(HomePage.homePageURL);

        CookiesLoader.loadCookies(driver, CookiesLoader.loginCookiesPath);

        if (homePage.checkSignInButtonVisibility()) {
            signInPage = pageFactoryManager.getSignInPage();
            signInPage.getDriver().get(SignInPage.signInPageURL);

            List<String> creds = CredentialsParser.getCredentials();

            signInPage.inputIntoLoginField(creds.get(1))
                    .clickContinueButton()
                    .inputIntoPasswordField(creds.get(2))
                    .clickSignInButton();
        }

        //cookies live for 2 hours
        //CookiesLoader.saveCookies(driver, CookiesLoader.loginCookiesPath);
    }

    @And("User check shipping information already present")
    public void checkShippingInfoAlreadyPresent() {
        Assert.assertTrue(checkoutPage.checkChangeAddressButtonVisibility());
    }

    @When("User adds item to watchlist")
    public void addItemToWatchlist() {
        itemPage.clickAddToWatchlistButton();
    }

    @And("User check Watchlist button visibility")
    public void checkWatchlistButtonVisibility() {
        Assert.assertTrue(itemPage.checkWatchlistButtonVisibility());
    }

    @And("User clicks Watchlist button")
    public void clickWatchlist() {
        itemPage.clickWatchlistButton();

        watchlistPage = pageFactoryManager.getWatchlistPage();
    }

    @And("User checks 'View all items you are watching' button visibility")
    public void checkViewAllItemsURWatchingButtonVisibility() {
        Assert.assertTrue(itemPage.checkViewAllItemsURWatchingButtonVisibility());
    }

    @And("User clicks 'View all items you are watching' button")
    public void clickViewAllItemsURWatchingButton() {
        itemPage.clickViewAllItemsURWatchingButton();

        watchlistPage = pageFactoryManager.getWatchlistPage();
    }

    @Then("Watchlist should contain the item")
    public void checkWatchlistItems() {
        Assert.assertTrue(watchlistPage.getWatchlistItemsText().toString().contains(savedText));

        watchlistPage.selectWatchlistItem(0)
                .clickDeleteItemsButton();
    }

    @After
    public void shutDown() {
        driver.close();
        devTools.close();
    }
}
