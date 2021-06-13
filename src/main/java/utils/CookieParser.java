package utils;

import org.openqa.selenium.Cookie;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class CookieParser {
    public static Cookie getCookie(String line) {
        StringTokenizer token = new StringTokenizer(line, ";");
        String name = token.nextToken();
        String value = token.nextToken();
        String domain = token.nextToken();
        String path = token.nextToken();
        Date expiry = null;

        domain = domain.charAt(0) == '.'? domain.substring(1) : domain;

        String val = token.nextToken();
        if (!val.equals("null")) {
            DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy", Locale.ENGLISH);
            try {
                expiry = formatter.parse(val);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        boolean isSecure = Boolean.parseBoolean(token.nextToken());

        return new Cookie(name, value, domain, path, expiry, isSecure);
    }
}


