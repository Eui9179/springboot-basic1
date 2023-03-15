package com.ll.basic1.base.cookieUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Arrays;

public class CookieUtil {
    public static String resolveCookieValue(Cookie[] cookies, String cookieName) {
        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(cookieName))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);
    }

    public static void expireCookie(Cookie[] cookies, HttpServletResponse response, String cookieName) {
        Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(cookieName))
                .forEach(cookie -> {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                });
    }
}
