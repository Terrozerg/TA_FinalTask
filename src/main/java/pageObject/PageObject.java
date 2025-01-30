package pageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

public abstract class PageObject {
    private WebDriver driver;
    private Actions actions;

    public static final int DEFAULT_WAIT_TIME = 60;

    public PageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.actions = new Actions(driver);
    }

    public void waitForElementToLoad(WebElement element, int timeOut) {
        new WebDriverWait(driver, Duration.of(timeOut, ChronoUnit.SECONDS)).until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementsToLoad(List<WebElement> elements, int timeOut) {
        new WebDriverWait(driver, Duration.of(timeOut, ChronoUnit.SECONDS)).until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitForAjaxToComplete(int timeOut) {
        new WebDriverWait(driver, Duration.of(timeOut, ChronoUnit.SECONDS))
                .until(webDriver -> Objects.requireNonNull(((JavascriptExecutor) webDriver)
                .executeScript("return window.jQuery != undefined && jQuery.active == 0;")));
    }

    public WebElement waitForElementToBeClickable(WebElement element, int timeOut) {
        new WebDriverWait(driver, Duration.of(timeOut, ChronoUnit.SECONDS))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.elementToBeClickable(element));

        return element;
    }

    public void waitForPageToLoad(int timeOut) {
        new WebDriverWait(driver, Duration.of(timeOut, ChronoUnit.SECONDS))
                .until(webDriver -> Objects.equals(((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState"), "complete"));
    }

    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: \"end\", inline: \"nearest\"});", element);
    }

    public void clickElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public Actions getActions() {
        return actions;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
