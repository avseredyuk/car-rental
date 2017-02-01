package com.avseredyuk.carrental.web.filter;

import com.avseredyuk.carrental.web.util.ConstantClass;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by lenfer on 1/26/17.
 */
public class ErrorStatusFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // do nothing
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        String errorString = (String) session.getAttribute(ConstantClass.ERROR_STATUS);
        if (errorString != null) {
            request.setAttribute(ConstantClass.ERROR_STATUS, errorString);
            session.removeAttribute(ConstantClass.ERROR_STATUS);
        }
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        // do nothing
    }
}
