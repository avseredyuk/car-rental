package com.avseredyuk.carrental.web.command.impl.auth;

import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.command.result.CommandResult;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.ParamsValidatorUtil;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import org.apache.log4j.Logger;

/**
 * Created by lenfer on 1/11/17.
 */
public class CommandLogin implements Command {
    private static final Logger logger = Logger.getLogger(CommandLogin.class);

    @Override
    public CommandResult execute(RequestWrapper req) {
        String login = req.getParameter(ConstantClass.USERLOGIN);
        String password = req.getParameter(ConstantClass.USERPASSWORD);

        if ((!ParamsValidatorUtil.checkAllNotEmpty(login, password))
                || (!ServiceFactoryImplementation
                .getInstance()
                .getAuthorizationService()
                .login(login, password, req.getSession()))) {
            logger.info(String.format("login failed with login %s", login));
            req.setAttribute(ConstantClass.ERROR_STATUS, "error.login");
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_LOGIN).execute(req);
        } else {
            return commandResultSelector(req, false,
                    CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_INDEX));
        }
    }
}
