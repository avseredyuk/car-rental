package com.avseredyuk.carrental.web.command.impl;

import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.util.ConfigurationManager;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.ResponseWrapper;

/**
 * Created by lenfer on 1/14/17.
 */
public class CommandShowNotFound implements Command {
    @Override
    public String execute(RequestWrapper req, ResponseWrapper resp) {
        return ConfigurationManager.getProperty("path.page.error.notfound");
    }
}
