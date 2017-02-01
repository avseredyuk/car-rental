package com.avseredyuk.carrental.web.command.impl.auth;

import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.HashingUtil;
import com.avseredyuk.carrental.web.util.ParametersVerifier;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.ResponseWrapper;
import org.apache.log4j.Logger;

/**
 * Created by lenfer on 1/13/17.
 */
public class CommandRegister implements Command {
    private static final Logger logger = Logger.getLogger(CommandRegister.class);

    @Override
    public String execute(RequestWrapper req, ResponseWrapper resp) {
        String login = req.getParameter(ConstantClass.USERLOGIN);
        String email = req.getParameter(ConstantClass.USEREMAIL);
        String password = req.getParameter(ConstantClass.USERPASSWORD);
        String name = req.getParameter(ConstantClass.USERNAME);
        String surname = req.getParameter(ConstantClass.USERSURNAME);

        if (!ParametersVerifier.checkAllNotEmpty(login, email, password, name, surname)) {
            logger.info("registering with empty data");
            req.setAttribute(ConstantClass.ERROR_STATUS, "error.registration.empty");
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_REGISTER).execute(req, resp);
        }
        User user = new User(login, email, HashingUtil.hashPassword(password), name, surname, User.Role.CLIENT);
        if (!ServiceFactoryImplementation
                .getInstance()
                .getUserService()
                .persist(user)) {
            logger.info("registering with invalid data, user persist failed");
            req.setAttribute(ConstantClass.ERROR_STATUS, "error.registration.persist");
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_REGISTER).execute(req, resp);
        } else {
            ServiceFactoryImplementation
                    .getInstance()
                    .getAuthorizationService()
                    .login(login, password, req.getSession());
            doReturnIfPossible(req, resp, false);
        }
        return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_INDEX).execute(req, resp);
    }
}
