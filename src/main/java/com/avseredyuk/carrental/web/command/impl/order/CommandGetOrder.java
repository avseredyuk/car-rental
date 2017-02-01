package com.avseredyuk.carrental.web.command.impl.order;

import com.avseredyuk.carrental.domain.DeliveryPlace;
import com.avseredyuk.carrental.domain.Order;
import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.exception.CommandExecutionException;
import com.avseredyuk.carrental.web.util.ConfigurationManager;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.ResponseWrapper;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by lenfer on 1/19/17.
 */
public class CommandGetOrder implements Command {
    private static final Logger logger = Logger.getLogger(CommandGetOrder.class);

    @Override
    public String execute(RequestWrapper req, ResponseWrapper resp) {
        if (!ServiceFactoryImplementation.getInstance().getAuthorizationService().checkRole(User.Role.ADMINISTRATOR, req.getSession())) {
            logger.info("trying to access without permissions");
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_FORBIDDEN).execute(req, resp);
        }
        String page = ConfigurationManager.getProperty("path.page.getorder");

        try {
            int orderId = Integer.parseInt(req.getParameter(ConstantClass.ORDER_ID));
            Order order = ServiceFactoryImplementation.getInstance().getOrderService().read(orderId);
            if (order == null) {
                throw new CommandExecutionException();
            }
            req.setAttribute(ConstantClass.ORDER, order);

            List<DeliveryPlace> places = ServiceFactoryImplementation.getInstance().getDeliveryPlaceService().findAll();
            req.setAttribute(ConstantClass.PLACES, places);
            List<User> users = ServiceFactoryImplementation.getInstance().getUserService().findAll();
            req.setAttribute(ConstantClass.USERS, users);

        } catch(NumberFormatException | CommandExecutionException e) {
            logger.info("invalid data on get order", e);
            req.setAttribute(ConstantClass.ERROR_STATUS, "error.get.order");
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_GET_ALL_ORDERS).execute(req, resp);
        }
        return page;
    }
}
