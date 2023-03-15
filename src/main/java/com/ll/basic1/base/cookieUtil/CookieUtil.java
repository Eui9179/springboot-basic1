package com.ll.basic1.base.cookieUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public class CookieUtil {
    private HttpServletRequest request;
    private HttpServletResponse response;

    public CookieUtil(HttpServletRequest request) {
        this.request = request;
    }

    public CookieUtil(HttpServletResponse response) {
        this.response = response;
    }

    public void setCookie(String key, String value) {
        response.addCookie(new Cookie(key, value));
    }

    public void setCookie(String key, long value) {
        response.addCookie(new Cookie(key, String.valueOf(value)));
    }

    public String getCookie(String key, String defaultValue) {
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(key))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(defaultValue);
    }

    public long getCookieAsLong(String key, long defaultValue) {
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(key))
                .map(Cookie::getValue)
                .mapToLong(Long::parseLong)
                .findFirst()
                .orElse(defaultValue);
    }

    public void removeCookie(String key) {
        Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(key))
                .forEach(cookie -> {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                });
    }
}
