package com.avseredyuk.carrental.web.command.impl.user;

import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.ResponseWrapper;

/**
 * Created by lenfer on 1/11/17.
 */
public class CommandDeleteUser implements Command {

    @Override
    public String execute(RequestWrapper req, ResponseWrapper resp) {
        if (!ServiceFactoryImplementation.getInstance().getAuthorizationService().checkRole(User.Role.ADMINISTRATOR, req.getSession())) {
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_FORBIDDEN).execute(req, resp);
        }
        try {
            int userId = Integer.parseInt(req.getParameter(ConstantClass.USER_ID));
            User user = new User(userId);
            if (!ServiceFactoryImplementation.getInstance().getUserService().delete(user)) {
                req.getSession().setAttribute(ConstantClass.ERROR_STATUS, "error.delete.user");
            }
        } catch(NumberFormatException e) {
            req.getSession().setAttribute(ConstantClass.ERROR_STATUS, "error.delete.user");
        }
        doReturnIfPossible(req, resp, false);
        return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_GET_ALL_USERS).execute(req, resp);
    }
}
