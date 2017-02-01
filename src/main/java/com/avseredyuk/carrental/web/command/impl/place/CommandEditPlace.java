package com.avseredyuk.carrental.web.command.impl.place;

import com.avseredyuk.carrental.domain.DeliveryPlace;
import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.ResponseWrapper;
import org.apache.log4j.Logger;

/**
 * Created by lenfer on 1/13/17.
 */
public class CommandEditPlace implements Command {
    private static final Logger logger = Logger.getLogger(CommandEditPlace.class);

    @Override
    public String execute(RequestWrapper req, ResponseWrapper resp) {
        if (!ServiceFactoryImplementation.getInstance().getAuthorizationService().checkRole(User.Role.ADMINISTRATOR, req.getSession())) {
            logger.info("trying to access without permissions");
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_FORBIDDEN).execute(req, resp);
        }
        try {
            int placeId = Integer.parseInt(req.getParameter(ConstantClass.PLACE_ID));
            String name = req.getParameter(ConstantClass.PLACENAME);
            String address = req.getParameter(ConstantClass.PLACEADDRESS);
            DeliveryPlace.DeliveryPlaceType deliveryPlaceType = DeliveryPlace.DeliveryPlaceType.valueOf(req.getParameter(ConstantClass.PLACETYPE));

            DeliveryPlace deliveryPlace = new DeliveryPlace(placeId, name, address, deliveryPlaceType);
            if (!ServiceFactoryImplementation.getInstance().getDeliveryPlaceService().update(deliveryPlace)) {
                logger.info("failed to edit place");
                req.getSession().setAttribute(ConstantClass.ERROR_STATUS, "error.edit.place");
            }
        } catch(NumberFormatException e) {
            logger.info("invalid id on edit place", e);
            req.getSession().setAttribute(ConstantClass.ERROR_STATUS, "error.edit.place");
        }
        doReturnIfPossible(req, resp, true);
        return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_GET_ALL_PLACES).execute(req, resp);
    }
}
