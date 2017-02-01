package com.avseredyuk.carrental.web.filter;

import com.avseredyuk.carrental.web.util.ConstantClass;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by lenfer on 1/18/17.
 */
public class LocaleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // do nothing
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        String sessionLocale = (String) session.getAttribute(ConstantClass.LOCALE);
        if(sessionLocale == null) {
            switch(request.getLocale().toString()) {
                case "en_US":
                    session.setAttribute(ConstantClass.LOCALE, "en_US");
                    break;
                case "ru_RU":
                case "uk_UA":
                default:
                    session.setAttribute(ConstantClass.LOCALE, "ru_RU");
                    break;
            }
        }
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        // do nothing
    }
}
