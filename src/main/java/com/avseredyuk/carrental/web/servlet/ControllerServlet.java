package com.avseredyuk.carrental.web.servlet;

import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.command.result.CommandResult;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.impl.RequestWrapperImplementation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lenfer on 1/4/17.
 */
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestWrapper reqWrapper = new RequestWrapperImplementation(req);
        CommandResult result = CommandFactory.getInstance().getCommand(reqWrapper).execute(reqWrapper);
        if (result.getType() == CommandResult.ActionType.FORWARD) {
            getServletContext().getRequestDispatcher(result.getResult()).forward(req, resp);
        } else {
            resp.sendRedirect(result.getResult());
        }



    }
}
