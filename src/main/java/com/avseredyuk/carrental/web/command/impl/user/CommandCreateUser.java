package com.avseredyuk.carrental.web.command.impl.user;

import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.HashingUtil;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.ResponseWrapper;
import org.apache.log4j.Logger;

/**
 * Created by lenfer on 1/11/17.
 */
public class CommandCreateUser implements Command {
    private static final Logger logger = Logger.getLogger(CommandCreateUser.class);

    @Override
    public String execute(RequestWrapper req, ResponseWrapper resp) {
        if (!ServiceFactoryImplementation.getInstance().getAuthorizationService().checkRole(User.Role.ADMINISTRATOR, req.getSession())) {
            logger.info("trying to access without permissions");
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_FORBIDDEN).execute(req, resp);
        }
        try {
            String login = req.getParameter(ConstantClass.USERLOGIN);
            String email = req.getParameter(ConstantClass.USEREMAIL);
            String name = req.getParameter(ConstantClass.USERNAME);
            String surname = req.getParameter(ConstantClass.USERSURNAME);
            String password = req.getParameter(ConstantClass.USERPASSWORD);
            User.Role role = User.Role.valueOf(req.getParameter(ConstantClass.USERROLE));

            User user = new User(login, email, HashingUtil.hashPassword(password), name, surname, role);
            if (!ServiceFactoryImplementation.getInstance().getUserService().persist(user)) {
                logger.info("failed to persist user");
                req.getSession().setAttribute(ConstantClass.ERROR_STATUS, "error.create.user");
            }
        } catch(NumberFormatException e) {
            logger.info("invalid data on create user", e);
            req.getSession().setAttribute(ConstantClass.ERROR_STATUS, "error.create.user");
        }
        doReturnIfPossible(req, resp, true);
        return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_GET_ALL_USERS).execute(req, resp);
    }
}
