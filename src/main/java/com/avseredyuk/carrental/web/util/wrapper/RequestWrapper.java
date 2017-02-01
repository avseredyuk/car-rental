package com.avseredyuk.carrental.web.util.wrapper;

import java.util.Map;

/**
 * Created by lenfer on 1/29/17.
 */
public interface RequestWrapper {
    String getParameter(String key);
    void setAttribute(String key, Object value);
    SessionWrapper getSession();
    Map getParameterMap();
}
