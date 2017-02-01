package com.avseredyuk.carrental.web.util.wrapper.impl;

import com.avseredyuk.carrental.web.util.wrapper.SessionWrapper;

import javax.servlet.http.HttpSession;

/**
 * Created by lenfer on 1/29/17.
 */
public class SessionWrapperImplementation implements SessionWrapper {
    private HttpSession session;

    public SessionWrapperImplementation(HttpSession session) {
        this.session = session;
    }

    @Override
    public Object getAttribute(String key) {
        return session.getAttribute(key);
    }

    @Override
    public void setAttribute(String key, Object value) {
        session.setAttribute(key, value);
    }

    @Override
    public void removeAttribute(String key) {
        session.removeAttribute(key);
    }

    @Override
    public void invalidate() {
        session.invalidate();
    }
}
