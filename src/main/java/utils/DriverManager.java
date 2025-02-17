package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DriverManager {

    public static final String browserPath = "src/main/resources/browsers";

    private static WebDriver webdriver;

    private static List<String> getBrowsers() {
        try {
            return Files.lines(Path.of(browserPath))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String getCurrentBrowser() {
        List<String> browsers = getBrowsers();
        String browserName = System.getProperty("browser");

        return Objects.requireNonNull(browsers).stream().filter(item -> item.equals(browserName))
                .findFirst().orElse("chrome");
    }

    private static String getBrowserOptions() {
        return System.getProperty("browserOptions");
    }

    private static WebDriver getDriverInstance(String currentBrowser, String browserOptions) {
        if (webdriver == null) {
            webdriver = switch (currentBrowser) {
                case "safari" -> new SafariDriver();
                case "firefox" -> browserOptions == null ?
                        new FirefoxDriver() :
                        new FirefoxDriver(new FirefoxOptions().addArguments(browserOptions));

                default -> browserOptions == null ?
                        new ChromeDriver() :
                        new ChromeDriver(new ChromeOptions().addArguments(browserOptions));
            };
        }

        return webdriver;
    }

    public static WebDriver getWebDriver() {
        String currentBrowser = getCurrentBrowser();
        String browserOptions = getBrowserOptions();

        return getDriverInstance(currentBrowser, browserOptions);
    }

    public static WebDriver getWebDriver(String driverName) {
        String browserOptions = getBrowserOptions();

        return getDriverInstance(driverName, browserOptions);
    }

    public static WebDriver getWebDriver(String driverName, String browserOptions) {
        return getDriverInstance(driverName, browserOptions);
    }

    public static DevTools getDevTools() {
        if (webdriver == null) throw new NullPointerException("Web Driver is not initialized yet.");

        return ((HasDevTools) webdriver).getDevTools();
    }
}
