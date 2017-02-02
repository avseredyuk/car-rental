package com.avseredyuk.carrental.web.command;

import com.avseredyuk.carrental.web.command.impl.auth.CommandUpdateProfile;
import com.avseredyuk.carrental.web.command.result.CommandResult;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by lenfer on 1/6/17.
 */
public interface Command {
    Logger logger = Logger.getLogger(Command.class);

    CommandResult execute(RequestWrapper req);

    default CommandResult commandResultSelector(RequestWrapper req, boolean uriEncoded, Command forwardCommand) {
        String returnPath = req.getParameter(ConstantClass.RETURN);
        if ((returnPath != null) && (!"".equals(returnPath))) {
            try {
                String uri;
                if (uriEncoded) {
                    uri = URLDecoder.decode(returnPath, "UTF-8");
                } else {
                    uri = returnPath;
                }
                return new CommandResult(uri, CommandResult.ActionType.REDIRECT);
            } catch (UnsupportedEncodingException e) {
                logger.error("Unsupported encoding at redirect", e);
            }
        }
        return forwardCommand.execute(req);
    }
}
