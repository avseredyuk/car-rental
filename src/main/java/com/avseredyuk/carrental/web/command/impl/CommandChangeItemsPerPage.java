package com.avseredyuk.carrental.web.command.impl;

import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.ResponseWrapper;

/**
 * Created by lenfer on 1/27/17.
 */
public class CommandChangeItemsPerPage implements Command {

    @Override
    public String execute(RequestWrapper req, ResponseWrapper resp) {
        if(req.getParameter(ConstantClass.ITEMS_PER_PAGE) != null) {
            String itemsPerPage = req.getParameter(ConstantClass.ITEMS_PER_PAGE);
            req.getSession().setAttribute(ConstantClass.ITEMS_PER_PAGE, itemsPerPage);
        }
        doReturnIfPossible(req, resp, true);
        return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_INDEX).execute(req, resp);
    }
}
