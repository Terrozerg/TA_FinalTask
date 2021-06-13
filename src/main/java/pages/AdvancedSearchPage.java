package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObject.PageObject;

public class AdvancedSearchPage extends PageObject {

    @FindBy(id = "_nkw")
    private WebElement keywords;

    @FindBy(id = "_ex_kw")
    private WebElement excludeKeywords;

    @FindBy(id = "e1-1")
    private WebElement categoriesList;

    @FindBy(xpath = "//div[contains(@class,'space-top')]/button")
    private WebElement topSearchButton;

    @FindBy(id = "LH_TitleDesc")
    private WebElement titleAndDesc;

    @FindBy(id = "LH_Complete")
    private WebElement completedListings;

    @FindBy(id = "LH_Sold")
    private WebElement soldListings;

    @FindBy(id = "_mPrRngCbx")
    private WebElement showItemsPricedCheckBox;

    @FindBy(xpath = "//input[@name='_udlo']")
    private WebElement pricesFrom;

    @FindBy(xpath = "//input[@name='_udhi']")
    private WebElement pricesTo;

    @FindBy(id = "LH_Auction")
    private WebElement auction;

    @FindBy(id = "LH_BIN")
    private WebElement buyItNow;

    @FindBy(id = "LH_ItemConditionNew")
    private WebElement newItems;

    @FindBy(id = "LH_ItemConditionUsed")
    private WebElement usedItems;

    @FindBy(id = "LH_ItemConditionNS")
    private WebElement notSpecifiedItems;

    @FindBy(id = "LH_Time")
    private WebElement listings;

    @FindBy(id = "LH_NOB")
    private WebElement numberOfBids;

    @FindBy(id = "LH_MIL")
    private WebElement multipleItemsListingsFrom;

    @FindBy(id = "LH_Lots")
    private WebElement itemsListedAsLots;

    @FindBy(id = "LH_SaleItems")
    private WebElement saleItems;

    @FindBy(id = "LH_BO")
    private WebElement bestOffer;

    @FindBy(id = "LH_Charity")
    private WebElement charity;

    @FindBy(id = "LH_FS")
    private WebElement freeInternationalShipping;

    @FindBy(id = "LH_Located")
    private WebElement locatedInDistance;

    @FindBy(id = "LH_PrefLocRadio")
    private WebElement fromPreferredLocations;

    @FindBy(id = "LH_LocatedInRadio")
    private WebElement locatedInCountry;

    @FindBy(id = "_fss")
    private WebElement onlyShowItemsFrom;

    @FindBy(id = "LH_SORT_BY")
    private WebElement sortByList;

    @FindBy(id = "LH_VIEW_RESULTS_AS")
    private WebElement viewResultsList;

    @FindBy(id = "LH_IPP")
    private WebElement resultsPerPageList;

    @FindBy(id = "searchBtnLowerLnk")
    private WebElement bottomSearchButton;

    @FindBy(xpath = "//div[@class='bottom-action-bar']/a")
    private WebElement clearButton;

    public AdvancedSearchPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkFieldsVisibility(){
        waitForElementToLoad(keywords, DEFAULT_WAIT_TIME);

        return keywords.isDisplayed() &&
                excludeKeywords.isDisplayed() &&
                categoriesList.isDisplayed() &&
                topSearchButton.isDisplayed() &&
                titleAndDesc.isDisplayed() &&
                completedListings.isDisplayed() &&
                soldListings.isDisplayed() &&
                showItemsPricedCheckBox.isDisplayed() &&
                pricesFrom.isDisplayed() &&
                pricesTo.isDisplayed() &&
                auction.isDisplayed() &&
                buyItNow.isDisplayed() &&
                newItems.isDisplayed() &&
                usedItems.isDisplayed() &&
                notSpecifiedItems.isDisplayed() &&
                listings.isDisplayed() &&
                numberOfBids.isDisplayed() &&
                multipleItemsListingsFrom.isDisplayed() &&
                itemsListedAsLots.isDisplayed() &&
                saleItems.isDisplayed() &&
                bestOffer.isDisplayed() &&
                charity.isDisplayed() &&
                freeInternationalShipping.isDisplayed() &&
                locatedInCountry.isDisplayed() &&
                onlyShowItemsFrom.isDisplayed() &&
                sortByList.isDisplayed() &&
                viewResultsList.isDisplayed() &&
                resultsPerPageList.isDisplayed() &&
                bottomSearchButton.isDisplayed() &&
                clearButton.isDisplayed();
    }
}
