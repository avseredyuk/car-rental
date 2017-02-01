package com.avseredyuk.carrental.web.servlet;

import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.exception.CommandRedirectException;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.ResponseWrapper;
import com.avseredyuk.carrental.web.util.wrapper.impl.RequestWrapperImplementation;
import com.avseredyuk.carrental.web.util.wrapper.impl.ResponseWrapperImplementation;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lenfer on 1/4/17.
 */
public class ControllerServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ControllerServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            RequestWrapper reqWrapper = new RequestWrapperImplementation(req);
            ResponseWrapper respWrapper = new ResponseWrapperImplementation(resp);
            String page = CommandFactory.getInstance().getCommand(reqWrapper).execute(reqWrapper, respWrapper);
            getServletContext().getRequestDispatcher(page).forward(req, resp);
        } catch (CommandRedirectException e) {
            logger.info("Doing redirect on request: " + req.getRequestURI(), e);
        }

    }
}
