package com.avseredyuk.carrental.web.command.impl.auth;

import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.util.ConfigurationManager;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.ResponseWrapper;

/**
 * Created by lenfer on 1/11/17.
 */
public class CommandShowLogin implements Command {

    @Override
    public String execute(RequestWrapper req, ResponseWrapper resp) {
        return ConfigurationManager.getProperty("path.page.showlogin");
    }
}
