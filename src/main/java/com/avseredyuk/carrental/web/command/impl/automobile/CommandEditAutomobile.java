package com.avseredyuk.carrental.web.command.impl.automobile;

import com.avseredyuk.carrental.domain.Automobile;
import com.avseredyuk.carrental.domain.DeliveryPlace;
import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.command.result.CommandResult;
import com.avseredyuk.carrental.web.util.ParamsValidatorUtil;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import org.apache.log4j.Logger;

import static com.avseredyuk.carrental.web.util.ConstantClass.*;

/**
 * Created by lenfer on 1/10/17.
 */
public class CommandEditAutomobile implements Command {
    private static final Logger logger = Logger.getLogger(CommandEditAutomobile.class);

    @Override
    public CommandResult execute(RequestWrapper req) {
        if (!ServiceFactoryImplementation.getInstance().getAuthorizationService().checkRole(User.Role.ADMINISTRATOR, req.getSession())) {
            logger.info("trying to access without permissions");
            return CommandFactory.getInstance().getByName(COMMAND_SHOW_FORBIDDEN).execute(req);
        }
        try {
            int automobileId = Integer.parseInt(req.getParameter(AUTOMOBILE_ID));
            String manufacturer = req.getParameter(MANUFACTURER);
            String model = req.getParameter(MODEL);
            Integer placeId = Integer.parseInt(req.getParameter(PLACES));
            DeliveryPlace deliveryPlace = ServiceFactoryImplementation.getInstance().getDeliveryPlaceService().read(placeId);

            Automobile.Category category = Automobile.Category.valueOf(req.getParameter(CATEGORY));
            Automobile.Fuel fuel = Automobile.Fuel.valueOf(req.getParameter(FUEL));
            Automobile.Transmission transmission = Automobile.Transmission.valueOf(req.getParameter(TRANSMISSION));
            int passengerCapacity = Integer.parseInt(req.getParameter(PASSENGER_CAPACITY));
            int cargoCapacity = Integer.parseInt(req.getParameter(CARGO_CAPACITY));
            int doorsCount = Integer.parseInt(req.getParameter(DOORS_COUNT));
            int pricePerDay = Integer.parseInt(req.getParameter(PRICE_PER_DAY));
            int yearOfProduction = Integer.parseInt(req.getParameter(YEAR_OF_PRODUCTION));
            if (!ParamsValidatorUtil.checkAllNonNegative(passengerCapacity, cargoCapacity,
                    doorsCount, pricePerDay, yearOfProduction)) {
                throw new NumberFormatException();
            }

            Automobile automobile = new Automobile(automobileId, manufacturer, model, yearOfProduction, deliveryPlace,
                    category, fuel, transmission, passengerCapacity, cargoCapacity, doorsCount, pricePerDay);
            if (!ServiceFactoryImplementation.getInstance().getAutomobileService().update(automobile)) {
                logger.info("failed to edit automobile");
                req.getSession().setAttribute(ERROR_STATUS, "error.edit.automobile");
            }
        } catch(NumberFormatException e) {
            logger.info("invalid id on edit automobile", e);
            req.getSession().setAttribute(ERROR_STATUS, "error.edit.automobile");
        }
        return commandResultSelector(req, true,
                CommandFactory.getInstance().getByName(COMMAND_GET_ALL_AUTOMOBILES));
    }
}
