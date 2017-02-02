package com.avseredyuk.carrental.web.command.impl;

import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.command.result.CommandResult;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;


/**
 * Created by lenfer on 1/26/17.
 */
public class CommandChangeLocale implements Command {

    @Override
    public CommandResult execute(RequestWrapper req) {
        if(req.getParameter(ConstantClass.LOCALE) != null) {
            String locale = req.getParameter(ConstantClass.LOCALE);
            req.getSession().setAttribute(ConstantClass.LOCALE, locale);
        }
        return commandResultSelector(req, true,
                CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_INDEX));
    }
}
