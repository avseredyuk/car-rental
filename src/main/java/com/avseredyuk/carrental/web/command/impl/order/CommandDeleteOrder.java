package com.avseredyuk.carrental.web.command.impl.order;

import com.avseredyuk.carrental.domain.Order;
import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.ResponseWrapper;
import org.apache.log4j.Logger;

/**
 * Created by lenfer on 1/18/17.
 */
public class CommandDeleteOrder implements Command {
    private static final Logger logger = Logger.getLogger(CommandDeleteOrder.class);

    @Override
    public String execute(RequestWrapper req, ResponseWrapper resp) {
        if (!ServiceFactoryImplementation.getInstance().getAuthorizationService().checkRole(User.Role.ADMINISTRATOR, req.getSession())) {
            logger.info("trying to access without permissions");
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_FORBIDDEN).execute(req, resp);
        }
        try {
            int orderId = Integer.parseInt(req.getParameter(ConstantClass.ORDER_ID));
            Order order = new Order(orderId);
            if (!ServiceFactoryImplementation.getInstance().getOrderService().delete(order)) {
                logger.info("failed to delete order");
                req.getSession().setAttribute(ConstantClass.ERROR_STATUS, "error.delete.order");
            }
        } catch(NumberFormatException e) {
            logger.info("invalid id on delete order", e);
            req.getSession().setAttribute(ConstantClass.ERROR_STATUS, "error.delete.order");
        }
        doReturnIfPossible(req, resp, false);
        return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_GET_ALL_ORDERS).execute(req, resp);
    }
}