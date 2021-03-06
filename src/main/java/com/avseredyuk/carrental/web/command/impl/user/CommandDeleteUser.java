package com.avseredyuk.carrental.web.command.impl.user;

import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.command.result.CommandResult;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import org.apache.log4j.Logger;

/**
 * Created by lenfer on 1/11/17.
 */
public class CommandDeleteUser implements Command {
    private static final Logger logger = Logger.getLogger(CommandDeleteUser.class);

    @Override
    public CommandResult execute(RequestWrapper req) {
        if (!ServiceFactoryImplementation.getInstance().getAuthorizationService().checkRole(User.Role.ADMINISTRATOR, req.getSession())) {
            logger.info("trying to access without permissions");
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_FORBIDDEN).execute(req);
        }
        try {
            int userId = Integer.parseInt(req.getParameter(ConstantClass.USER_ID));
            User user = new User(userId);
            if (!ServiceFactoryImplementation.getInstance().getUserService().delete(user)) {
                logger.info("failed to delete user");
                req.getSession().setAttribute(ConstantClass.ERROR_STATUS, "error.delete.user");
            }
        } catch(NumberFormatException e) {
            logger.info("invalid id on delete user", e);
            req.getSession().setAttribute(ConstantClass.ERROR_STATUS, "error.delete.user");
        }
        return commandResultSelector(req, false,
                CommandFactory.getInstance().getByName(ConstantClass.COMMAND_GET_ALL_USERS));
    }
}
