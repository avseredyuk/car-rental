package com.avseredyuk.carrental.web.command.impl.automobile;

import com.avseredyuk.carrental.domain.Automobile;
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
 * Created by lenfer on 1/10/17.
 */
public class CommandEditAutomobile implements Command {
    private static final Logger logger = Logger.getLogger(CommandEditAutomobile.class);

    @Override
    public String execute(RequestWrapper req, ResponseWrapper resp) {
        if (!ServiceFactoryImplementation.getInstance().getAuthorizationService().checkRole(User.Role.ADMINISTRATOR, req.getSession())) {
            logger.info("trying to access without permissions");
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_FORBIDDEN).execute(req, resp);
        }
        try {
            int automobileId = Integer.parseInt(req.getParameter(ConstantClass.AUTOMOBILE_ID));
            int yearOfProduction = Integer.parseInt(req.getParameter(ConstantClass.YEAR_OF_PRODUCTION));
            String manufacturer = req.getParameter(ConstantClass.MANUFACTURER);
            String model = req.getParameter(ConstantClass.MODEL);
            Integer placeId = Integer.parseInt(req.getParameter(ConstantClass.PLACES));
            DeliveryPlace deliveryPlace = ServiceFactoryImplementation.getInstance().getDeliveryPlaceService().read(placeId);

            Automobile.Category category = Automobile.Category.valueOf(req.getParameter(ConstantClass.CATEGORY));
            Automobile.Fuel fuel = Automobile.Fuel.valueOf(req.getParameter(ConstantClass.FUEL));
            Automobile.Transmission transmission = Automobile.Transmission.valueOf(req.getParameter(ConstantClass.TRANSMISSION));
            int passengerCapacity = Integer.parseInt(req.getParameter(ConstantClass.PASSENGER_CAPACITY));
            int cargoCapacity = Integer.parseInt(req.getParameter(ConstantClass.CARGO_CAPACITY));
            int doorsCount = Integer.parseInt(req.getParameter(ConstantClass.DOORS_COUNT));
            int pricePerDay = Integer.parseInt(req.getParameter(ConstantClass.PRICE_PER_DAY));

            Automobile automobile = new Automobile(automobileId, manufacturer, model, yearOfProduction, deliveryPlace,
                    category, fuel, transmission, passengerCapacity, cargoCapacity, doorsCount, pricePerDay);
            if (!ServiceFactoryImplementation.getInstance().getAutomobileService().update(automobile)) {
                logger.info("failed to edit automobile");
                req.getSession().setAttribute(ConstantClass.ERROR_STATUS, "error.edit.automobile");
            }
        } catch(NumberFormatException e) {
            logger.info("invalid id on edit automobile", e);
            req.getSession().setAttribute(ConstantClass.ERROR_STATUS, "error.edit.automobile");
        }
        doReturnIfPossible(req, resp, true);
        return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_GET_ALL_AUTOMOBILES).execute(req, resp);
    }
}
