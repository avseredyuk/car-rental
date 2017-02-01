package com.avseredyuk.carrental.web.filter;

import com.avseredyuk.carrental.web.util.ConstantClass;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by lenfer on 1/26/17.
 */
public class RequestParametersFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // do nothing
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        req.setAttribute(ConstantClass.REQUEST_URI,
                URLEncoder.encode(request.getQueryString() != null ? "?" + request.getQueryString() : "", "UTF-8"));

        req.setAttribute(ConstantClass.REFERER, request.getHeader(ConstantClass.REFERER));

        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        // do nothing
    }
}
