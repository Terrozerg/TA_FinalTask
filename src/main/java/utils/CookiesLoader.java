package utils;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.collections.Sets;
import stepDefinitions.StepDefinitions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

public class CookiesLoader {
    public static final String loginCookiesPath = "Cookies.data";
    public static final String capchaCookiesPath = "CapchaCookies.data";

    public static void loadCookies(WebDriver driver, String path){
        try {
            Set<Cookie> cookies = Sets.newHashSet(
                    Files.lines(Path.of(path))
                            .map(CookieParser::getCookie)
                            .collect(Collectors.toSet()));
            cookies.forEach(item -> driver.manage().addCookie(item));
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.navigate().refresh();
    }

    public static void saveCookies(WebDriver driver, String path){
        File file = new File(path);
        try
        {
            // Delete old file if exists
            file.delete();

            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for(Cookie ck : driver.manage().getCookies())
            {
                bufferedWriter.write((ck.getName()+";"+ck.getValue()+";"+ck.getDomain()+";"+ck.getPath()+";"+ck.getExpiry()+";"+ck.isSecure()));
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            fileWriter.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
