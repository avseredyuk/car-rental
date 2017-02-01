package com.avseredyuk.carrental.web.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by lenfer on 1/11/17.
 */
public class CodepageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // do nothing
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        // do nothing
    }
}
