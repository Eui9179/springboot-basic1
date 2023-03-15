package com.ll.basic1.base.cookieUtil;

import jakarta.servlet.http.Cookie;

import java.util.Arrays;

public class CookieUtil {
    public static String resolveCookieValue(Cookie[] cookies, String cookieName) {
        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(cookieName))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);
    }
}
