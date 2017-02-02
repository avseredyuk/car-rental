package com.avseredyuk.carrental.web.command.impl.place;

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

/**
 * Created by lenfer on 1/13/17.
 */
public class CommandGetPlace implements Command {
    private static final Logger logger = Logger.getLogger(CommandGetPlace.class);

    @Override
    public CommandResult execute(RequestWrapper req) {
        if (!ServiceFactoryImplementation.getInstance().getAuthorizationService().checkRole(User.Role.ADMINISTRATOR, req.getSession())) {
            logger.info("trying to access without permissions");
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_FORBIDDEN).execute(req);
        }
        try {
            int placeId = Integer.parseInt(req.getParameter(ConstantClass.PLACE_ID));
            DeliveryPlace deliveryPlace = ServiceFactoryImplementation.getInstance().getDeliveryPlaceService().read(placeId);
            if (deliveryPlace == null) {
                throw new CommandExecutionException();
            }
            req.setAttribute(ConstantClass.PLACE, deliveryPlace);

        } catch(NumberFormatException | CommandExecutionException e) {
            logger.info("invalid data on get place", e);
            req.setAttribute(ConstantClass.ERROR_STATUS, "error.get.place");
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_GET_ALL_PLACES).execute(req);
        }
        return new CommandResult(ConfigurationManager.getProperty("path.page.getplace"),
                CommandResult.ActionType.FORWARD);
    }
}
