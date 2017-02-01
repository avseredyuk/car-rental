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
public class CommandCreatePlace implements Command {
    @Override
    public String execute(RequestWrapper req, ResponseWrapper resp) {
        if (!ServiceFactoryImplementation.getInstance().getAuthorizationService().checkRole(User.Role.ADMINISTRATOR, req.getSession())) {
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_FORBIDDEN).execute(req, resp);
        }
        try {
            String name = req.getParameter(ConstantClass.PLACENAME);
            String address = req.getParameter(ConstantClass.PLACEADDRESS);
            DeliveryPlace.DeliveryPlaceType deliveryPlaceType = DeliveryPlace.DeliveryPlaceType.valueOf(req.getParameter(ConstantClass.PLACETYPE));

            DeliveryPlace deliveryPlace = new DeliveryPlace(name, address, deliveryPlaceType);
            if (!ServiceFactoryImplementation.getInstance().getDeliveryPlaceService().persist(deliveryPlace)) {
                req.getSession().setAttribute(ConstantClass.ERROR_STATUS, "error.create.place");
            }
        } catch(NumberFormatException e) {
            req.getSession().setAttribute(ConstantClass.ERROR_STATUS, "error.create.place");
        }
        doReturnIfPossible(req, resp, true);
        return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_GET_ALL_PLACES).execute(req, resp);
    }
}
