package com.avseredyuk.carrental.web.util.wrapper.impl;

import com.avseredyuk.carrental.web.util.wrapper.ResponseWrapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lenfer on 1/29/17.
 */
public class ResponseWrapperImplementation implements ResponseWrapper {
    private HttpServletResponse resp;

    public ResponseWrapperImplementation(HttpServletResponse resp) {
        this.resp = resp;
    }

    @Override
    public void sendRedirect(String url) throws IOException {
        resp.sendRedirect(url);
    }
}
