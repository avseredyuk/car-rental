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

/**
 * Created by lenfer on 1/13/17.
 */
public class CommandRegister implements Command {

    @Override
    public String execute(RequestWrapper req, ResponseWrapper resp) {
        String login = req.getParameter(ConstantClass.USERLOGIN);
        String email = req.getParameter(ConstantClass.USEREMAIL);
        String password = req.getParameter(ConstantClass.USERPASSWORD);
        String name = req.getParameter(ConstantClass.USERNAME);
        String surname = req.getParameter(ConstantClass.USERSURNAME);

        if (!ParametersVerifier.checkAllNotEmpty(login, email, password, name, surname)) {
            req.setAttribute(ConstantClass.ERROR_STATUS, "error.registration.empty");
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_REGISTER).execute(req, resp);
        }
        User user = new User(login, email, HashingUtil.hashPassword(password), name, surname, User.Role.CLIENT);
        if (!ServiceFactoryImplementation
                .getInstance()
                .getUserService()
                .persist(user)) {
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
