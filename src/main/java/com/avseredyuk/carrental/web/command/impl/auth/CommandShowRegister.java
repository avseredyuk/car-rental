package com.avseredyuk.carrental.web.command.impl.auth;

import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.result.CommandResult;
import com.avseredyuk.carrental.web.util.ConfigurationManager;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;

/**
 * Created by lenfer on 1/13/17.
 */
public class CommandShowRegister implements Command {
    @Override
    public CommandResult execute(RequestWrapper req) {
        return new CommandResult(ConfigurationManager.getProperty("path.page.showregister"),
                CommandResult.ActionType.FORWARD);
    }
}
