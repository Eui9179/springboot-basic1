package com.ll.basic1.base.session;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@AllArgsConstructor
@Component
@RequestScope
public class SessionResolver {
    private HttpServletRequest request;

    public void setSession(String name, long value) {
        HttpSession session = request.getSession();
        session.setAttribute(name, value);
    }

    public long getSessionAsLong(String name, long defaultValue) {
        try {
            return (long) request.getSession().getAttribute(name);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    private String getSessionAsStr(String name, String defaultValue) {
        try {
            String value = (String) request.getSession().getAttribute(name);
            if (value == null) return defaultValue;
            return value;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public boolean removeSession(String name) {
        HttpSession session = request.getSession();

        if (session.getAttribute(name) == null) return false;

        session.removeAttribute(name);
        return true;
    }

}
