package com.ll.basic1.base.cookie;

import com.ll.basic1.base.rsdata.RsData;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public class CookieResolver {
    private HttpServletRequest request;
    private HttpServletResponse response;

    public CookieResolver(HttpServletRequest request) {
        this.request = request;
    }

    public CookieResolver(HttpServletResponse response) {
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
        String cookieValue = getCookie(key, defaultValue + "");
        try {
            return Long.parseLong(cookieValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public boolean removeCookie(String key) {
        if (request.getCookies() == null) {
            return false;
        }
        Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(key))
                .forEach(cookie -> {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                });

        return Arrays.stream(request.getCookies())
                .anyMatch(cookie -> cookie.getName().equals(key));
    }
}
