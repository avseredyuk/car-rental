package com.avseredyuk.carrental.web.command.impl.auth;

import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.ParametersVerifier;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.ResponseWrapper;
import org.apache.log4j.Logger;

/**
 * Created by lenfer on 1/11/17.
 */
public class CommandLogin implements Command {
    private static final Logger logger = Logger.getLogger(CommandLogin.class);

    @Override
    public String execute(RequestWrapper req, ResponseWrapper resp) {
        String login = req.getParameter(ConstantClass.USERLOGIN);
        String password = req.getParameter(ConstantClass.USERPASSWORD);

        if ((!ParametersVerifier.checkAllNotEmpty(login, password))
                || (!ServiceFactoryImplementation
                .getInstance()
                .getAuthorizationService()
                .login(login, password, req.getSession()))) {
            logger.info(String.format("login failed with login %s", login));
            req.setAttribute(ConstantClass.ERROR_STATUS, "error.login");
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_LOGIN).execute(req, resp);
        } else {
            doReturnIfPossible(req, resp, false);
        }
        return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_INDEX).execute(req, resp);
    }
}
