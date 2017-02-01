package com.avseredyuk.carrental.web.command.impl.user;

import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.exception.CommandExecutionException;
import com.avseredyuk.carrental.web.util.ConfigurationManager;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.ResponseWrapper;
import org.apache.log4j.Logger;

/**
 * Created by lenfer on 1/11/17.
 */
public class CommandGetUser implements Command {
    private static final Logger logger = Logger.getLogger(CommandGetUser.class);

    @Override
    public String execute(RequestWrapper req, ResponseWrapper resp) {
        if (!ServiceFactoryImplementation.getInstance().getAuthorizationService().checkRole(User.Role.ADMINISTRATOR, req.getSession())) {
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_FORBIDDEN).execute(req, resp);
        }
        String page = ConfigurationManager.getProperty("path.page.getuser");

        try {
            int userId = Integer.parseInt(req.getParameter(ConstantClass.USER_ID));
            User user = ServiceFactoryImplementation.getInstance().getUserService().read(userId);
            if(user == null) {
                throw new CommandExecutionException();
            }
            req.setAttribute(ConstantClass.USER, user);

        } catch(NumberFormatException | CommandExecutionException e) {
            logger.info("invalid data on get user: " + e);
            req.setAttribute(ConstantClass.ERROR_STATUS, "error.get.user");
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_GET_ALL_USERS).execute(req, resp);
        }
        return page;
    }
}
