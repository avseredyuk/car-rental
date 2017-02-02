package com.avseredyuk.carrental.web.command.impl.auth;

import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.command.result.CommandResult;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;

/**
 * Created by lenfer on 1/12/17.
 */
public class CommandLogout implements Command {
    @Override
    public CommandResult execute(RequestWrapper req) {
        ServiceFactoryImplementation.getInstance().getAuthorizationService().logout(req);
        return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_INDEX).execute(req);
    }
}
