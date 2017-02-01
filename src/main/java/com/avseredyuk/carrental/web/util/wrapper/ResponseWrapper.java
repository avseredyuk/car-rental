package com.avseredyuk.carrental.web.util.wrapper;

import java.io.IOException;

/**
 * Created by lenfer on 1/29/17.
 */
public interface ResponseWrapper {
    void sendRedirect(String url) throws IOException;
}
