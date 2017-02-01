package com.avseredyuk.carrental.web.command.impl.place;

import com.avseredyuk.carrental.domain.DeliveryPlace;
import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.ResponseWrapper;

/**
 * Created by lenfer on 1/13/17.
 */
public class CommandDeletePlace implements Command {
    @Override
    public String execute(RequestWrapper req, ResponseWrapper resp) {
        if (!ServiceFactoryImplementation.getInstance().getAuthorizationService().checkRole(User.Role.ADMINISTRATOR, req.getSession())) {
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_FORBIDDEN).execute(req, resp);
        }
        try {
            int placeId = Integer.parseInt(req.getParameter(ConstantClass.PLACE_ID));
            DeliveryPlace deliveryPlace = new DeliveryPlace(placeId);
            if (!ServiceFactoryImplementation.getInstance().getDeliveryPlaceService().delete(deliveryPlace)) {
                req.getSession().setAttribute(ConstantClass.ERROR_STATUS, "error.delete.place");
            }
        } catch(NumberFormatException e) {
            req.getSession().setAttribute(ConstantClass.ERROR_STATUS, "error.delete.place");
        }
        doReturnIfPossible(req, resp, false);
        return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_GET_ALL_PLACES).execute(req, resp);
    }
}
