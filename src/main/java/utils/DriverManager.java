package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DriverManager {
    public static final String browserPath = "src/main/resources/browsers";


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
        String sysProp = System.getProperty("browser");

        return Objects.requireNonNull(browsers).stream().filter(item -> item.equals(sysProp))
                .findFirst().orElse("chrome");
    }

    public static WebDriver getWebDriverForBrowser() {
        String currentBrowser = getCurrentBrowser();

        return switch (currentBrowser) {
            case "safari" -> new SafariDriver();
            case "firefox" -> new FirefoxDriver();
            default -> new ChromeDriver();
        };
    }

}
