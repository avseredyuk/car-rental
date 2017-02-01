package com.avseredyuk.carrental.web.command.impl;

import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.ResponseWrapper;


/**
 * Created by lenfer on 1/26/17.
 */
public class CommandChangeLocale implements Command {

    @Override
    public String execute(RequestWrapper req, ResponseWrapper resp) {
        if(req.getParameter(ConstantClass.LOCALE) != null) {
            String locale = req.getParameter(ConstantClass.LOCALE);
            req.getSession().setAttribute(ConstantClass.LOCALE, locale);
        }
        doReturnIfPossible(req, resp, true);
        return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_INDEX).execute(req, resp);
    }
}
