package com.avseredyuk.carrental.web.util.wrapper.impl;

import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.SessionWrapper;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by lenfer on 1/29/17.
 */
public class RequestWrapperImplementation implements RequestWrapper {
    private HttpServletRequest req;

    public RequestWrapperImplementation(HttpServletRequest req) {
        this.req = req;
    }

    @Override
    public String getParameter(String key) {
        return req.getParameter(key);
    }

    @Override
    public void setAttribute(String key, Object value) {
        req.setAttribute(key, value);
    }

    @Override
    public SessionWrapper getSession() {
        return new SessionWrapperImplementation(req.getSession());
    }

    @Override
    public Map getParameterMap() {
        return req.getParameterMap();
    }
}
