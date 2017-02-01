package com.avseredyuk.carrental.web.command;

import com.avseredyuk.carrental.web.exception.CommandRedirectException;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.ResponseWrapper;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URLDecoder;

/**
 * Created by lenfer on 1/6/17.
 */
public interface Command {
    String execute(RequestWrapper req, ResponseWrapper resp);

    default void doReturnIfPossible(RequestWrapper req, ResponseWrapper resp, boolean uriEncoded) {
        String returnPath = req.getParameter(ConstantClass.RETURN);
        if ((returnPath != null) && (!"".equals(returnPath))) {
            try {
                if (uriEncoded) {
                    resp.sendRedirect(URLDecoder.decode(returnPath, "UTF-8"));
                } else {
                    resp.sendRedirect(returnPath);
                }
                throw new CommandRedirectException();
            } catch (IOException e) {
                Logger.getLogger(Command.class).error("exception on redirect: " + e);
            }
        }
    }
}
