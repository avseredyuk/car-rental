package com.avseredyuk.carrental.web.command.impl.user;

import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.command.result.CommandResult;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.HashingUtil;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import org.apache.log4j.Logger;

/**
 * Created by lenfer on 1/11/17.
 */
public class CommandEditUser implements Command {
    private static final Logger logger = Logger.getLogger(CommandEditUser.class);

    @Override
    public CommandResult execute(RequestWrapper req) {
        if (!ServiceFactoryImplementation.getInstance().getAuthorizationService().checkRole(User.Role.ADMINISTRATOR, req.getSession())) {
            logger.info("trying to access without permissions");
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_FORBIDDEN).execute(req);
        }
        try {
            int userId = Integer.parseInt(req.getParameter(ConstantClass.USER_ID));
            String login = req.getParameter(ConstantClass.USERLOGIN);
            String email = req.getParameter(ConstantClass.USEREMAIL);
            String name = req.getParameter(ConstantClass.USERNAME);
            String surname = req.getParameter(ConstantClass.USERSURNAME);
            String password = req.getParameter(ConstantClass.USERPASSWORD);
            User.Role role = User.Role.valueOf(req.getParameter(ConstantClass.USERROLE));

            User user = ServiceFactoryImplementation.getInstance().getUserService().read(userId);
            user.setLogin(login);
            user.setEmail(email);
            user.setName(name);
            user.setSurname(surname);
            user.setRole(role);
            if (!password.isEmpty()) {
                user.setPassword(HashingUtil.hashPassword(password));
            }
            if (!ServiceFactoryImplementation.getInstance().getUserService().update(user)) {
                logger.info("failed to edit user");
                req.getSession().setAttribute(ConstantClass.ERROR_STATUS, "error.edit.user");
            }
        } catch(NumberFormatException e) {
            logger.info("invalid id on edit user", e);
            req.getSession().setAttribute(ConstantClass.ERROR_STATUS, "error.edit.user");
        }
        return commandResultSelector(req, true,
                CommandFactory.getInstance().getByName(ConstantClass.COMMAND_GET_ALL_USERS));
    }
}
