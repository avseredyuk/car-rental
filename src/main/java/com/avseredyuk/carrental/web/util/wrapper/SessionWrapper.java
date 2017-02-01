package com.avseredyuk.carrental.web.util.wrapper;

/**
 * Created by lenfer on 1/29/17.
 */
public interface SessionWrapper {
    Object getAttribute(String key);
    void setAttribute(String key, Object value);
    void removeAttribute(String key);
    void invalidate();
}
