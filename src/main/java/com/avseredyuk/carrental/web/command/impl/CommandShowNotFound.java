package com.avseredyuk.carrental.web.command.impl;

import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.result.CommandResult;
import com.avseredyuk.carrental.web.util.ConfigurationManager;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;

/**
 * Created by lenfer on 1/14/17.
 */
public class CommandShowNotFound implements Command {
    @Override
    public CommandResult execute(RequestWrapper req) {
        return new CommandResult(ConfigurationManager.getProperty("path.page.error.notfound"),
                CommandResult.ActionType.FORWARD);
    }
}
