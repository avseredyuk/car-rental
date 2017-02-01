package com.avseredyuk.carrental.web.command.impl.automobile;

import com.avseredyuk.carrental.domain.Automobile;
import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.ResponseWrapper;

/**
 * Created by lenfer on 1/9/17.
 */
public class CommandDeleteAutomobile implements Command {

    @Override
    public String execute(RequestWrapper req, ResponseWrapper resp) {
        if (!ServiceFactoryImplementation.getInstance().getAuthorizationService().checkRole(User.Role.ADMINISTRATOR, req.getSession())) {
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_FORBIDDEN).execute(req, resp);
        }
        try {
            int automobileId = Integer.parseInt(req.getParameter(ConstantClass.AUTOMOBILE_ID));
            Automobile automobile = new Automobile(automobileId);
            if (!ServiceFactoryImplementation.getInstance().getAutomobileService().delete(automobile)) {
                req.getSession().setAttribute(ConstantClass.ERROR_STATUS, "error.delete.automobile");
            }
        } catch(NumberFormatException e) {
            req.getSession().setAttribute(ConstantClass.ERROR_STATUS, "error.delete.automobile");
        }
        doReturnIfPossible(req, resp, false);
        return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_GET_ALL_AUTOMOBILES).execute(req, resp);
    }
}
