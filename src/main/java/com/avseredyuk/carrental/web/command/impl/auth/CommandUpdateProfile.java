package com.avseredyuk.carrental.web.command.impl.auth;

import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.command.result.CommandResult;
import com.avseredyuk.carrental.web.exception.CommandExecutionException;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.HashingUtil;
import com.avseredyuk.carrental.web.util.ParamsValidatorUtil;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import org.apache.log4j.Logger;

/**
 * Created by lenfer on 1/25/17.
 */
public class CommandUpdateProfile implements Command {
    private static final Logger logger = Logger.getLogger(CommandUpdateProfile.class);

    @Override
    public CommandResult execute(RequestWrapper req) {
        if (!ServiceFactoryImplementation.getInstance().getAuthorizationService().checkRole(User.Role.CLIENT, req.getSession())) {
            logger.info("trying to access without permissions");
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_FORBIDDEN).execute(req);
        }
        try {
            String email = req.getParameter(ConstantClass.USEREMAIL);
            String name = req.getParameter(ConstantClass.USERNAME);
            String surname = req.getParameter(ConstantClass.USERSURNAME);
            if (!ParamsValidatorUtil.checkAllNotEmpty(email, name, surname)) {
                throw new CommandExecutionException();
            }
            String password = req.getParameter(ConstantClass.USERPASSWORD);

            String login = (String) req.getSession().getAttribute(ConstantClass.USERLOGIN);

            User user = ServiceFactoryImplementation.getInstance().getUserService().getByLogin(login);
            if (user == null) {
                throw new CommandExecutionException();
            }
            user.setEmail(email);
            user.setName(name);
            user.setSurname(surname);
            if (!password.isEmpty()) {
                user.setPassword(HashingUtil.hashPassword(password));
            }
            if (!ServiceFactoryImplementation.getInstance().getUserService().update(user)) {
                req.setAttribute(ConstantClass.ERROR_STATUS, "error.update.profile");
            } else {
                req.setAttribute(ConstantClass.ERROR_STATUS, "success.update.profile");
            }
        } catch(CommandExecutionException e) {
            logger.info("invalid data on update profile", e);
            req.setAttribute(ConstantClass.ERROR_STATUS, "error.update.profile");
        }
        return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_PROFILE).execute(req);
    }
}
