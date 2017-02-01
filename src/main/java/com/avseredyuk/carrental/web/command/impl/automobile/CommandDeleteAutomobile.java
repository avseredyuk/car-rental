package com.avseredyuk.carrental.web.command.impl.automobile;

import com.avseredyuk.carrental.domain.Automobile;
import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.ResponseWrapper;
import org.apache.log4j.Logger;

/**
 * Created by lenfer on 1/9/17.
 */
public class CommandDeleteAutomobile implements Command {
    private static final Logger logger = Logger.getLogger(CommandDeleteAutomobile.class);

    @Override
    public String execute(RequestWrapper req, ResponseWrapper resp) {
        if (!ServiceFactoryImplementation.getInstance().getAuthorizationService().checkRole(User.Role.ADMINISTRATOR, req.getSession())) {
            logger.info("trying to access without permissions");
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_FORBIDDEN).execute(req, resp);
        }
        try {
            int automobileId = Integer.parseInt(req.getParameter(ConstantClass.AUTOMOBILE_ID));
            Automobile automobile = new Automobile(automobileId);
            if (!ServiceFactoryImplementation.getInstance().getAutomobileService().delete(automobile)) {
                logger.info("failed to delete automobile");
                req.getSession().setAttribute(ConstantClass.ERROR_STATUS, "error.delete.automobile");
            }
        } catch(NumberFormatException e) {
            logger.info("invalid id on delete automobile", e);
            req.getSession().setAttribute(ConstantClass.ERROR_STATUS, "error.delete.automobile");
        }
        doReturnIfPossible(req, resp, false);
        return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_GET_ALL_AUTOMOBILES).execute(req, resp);
    }
}
