package com.avseredyuk.carrental.web.command.impl.automobile;

import com.avseredyuk.carrental.domain.Automobile;
import com.avseredyuk.carrental.domain.DeliveryPlace;
import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.command.result.CommandResult;
import com.avseredyuk.carrental.web.exception.CommandExecutionException;
import com.avseredyuk.carrental.web.util.ConfigurationManager;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by lenfer on 1/9/17.
 */
public class CommandGetAutomobile implements Command {
    private static final Logger logger = Logger.getLogger(CommandGetAutomobile.class);

    @Override
    public CommandResult execute(RequestWrapper req) {
        if (!ServiceFactoryImplementation.getInstance().getAuthorizationService().checkRole(User.Role.ADMINISTRATOR, req.getSession())) {
            logger.info("trying to access without permissions");
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_FORBIDDEN).execute(req);
        }
        try {
            int automobileId = Integer.parseInt(req.getParameter(ConstantClass.AUTOMOBILE_ID));
            Automobile automobile = ServiceFactoryImplementation.getInstance().getAutomobileService().read(automobileId);
            if (automobile == null) {
                throw new CommandExecutionException();
            }
            req.setAttribute(ConstantClass.AUTOMOBILE, automobile);
            List<DeliveryPlace> places = ServiceFactoryImplementation.getInstance().getDeliveryPlaceService().findAll();
            req.setAttribute(ConstantClass.PLACES, places);

        } catch(NumberFormatException | CommandExecutionException e) {
            logger.info("invalid data on get automobile", e);
            req.setAttribute(ConstantClass.ERROR_STATUS, "error.get.automobile");
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_GET_ALL_AUTOMOBILES).execute(req);
        }
        return new CommandResult(ConfigurationManager.getProperty("path.page.getautomobile"),
                CommandResult.ActionType.FORWARD);
    }
}
