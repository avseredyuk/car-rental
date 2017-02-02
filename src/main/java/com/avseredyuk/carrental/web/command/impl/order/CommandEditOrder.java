package com.avseredyuk.carrental.web.command.impl.order;

import com.avseredyuk.carrental.domain.*;
import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.command.result.CommandResult;
import com.avseredyuk.carrental.web.exception.CommandExecutionException;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.ParamsValidatorUtil;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import org.apache.log4j.Logger;

import java.text.ParseException;

/**
 * Created by lenfer on 1/19/17.
 */
public class CommandEditOrder implements Command {
    private static final Logger logger = Logger.getLogger(CommandEditOrder.class);

    @Override
    public CommandResult execute(RequestWrapper req) {
        if (!ServiceFactoryImplementation.getInstance().getAuthorizationService().checkRole(User.Role.ADMINISTRATOR, req.getSession())) {
            logger.info("trying to access without permissions");
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_FORBIDDEN).execute(req);
        }
        try {
            int orderId = Integer.parseInt(req.getParameter(ConstantClass.ORDER_ID));
            int placeFromId = Integer.parseInt(req.getParameter(ConstantClass.PLACES_FROM));
            int placeToId = Integer.parseInt(req.getParameter(ConstantClass.PLACES_TO));
            int userId = Integer.parseInt(req.getParameter(ConstantClass.USERS));

            Damage damage = null;
            String damageIdString = req.getParameter(ConstantClass.DAMAGE_ID);
            String damageSumString = req.getParameter(ConstantClass.DAMAGE_SUM);
            String damageDescriptionString = req.getParameter(ConstantClass.DAMAGE_DESCRIPTION);
            String damagePaidString = req.getParameter(ConstantClass.DAMAGE_PAID);
            if (!"".equals(damageIdString)) {
                int damageSum = Integer.parseInt(damageSumString);
                if (!ParamsValidatorUtil.checkAllNonNegative(damageSum)) {
                    throw new NumberFormatException();
                }
                damage = new Damage(Integer.parseInt(damageIdString),
                        damageSum,
                        damageDescriptionString,
                        damagePaidString != null);
                if (!ServiceFactoryImplementation.getInstance().getDamageService().update(damage)) {
                    logger.info("invalid data on update damage");
                    req.getSession().setAttribute(ConstantClass.ERROR_STATUS, "error.edit.order");
                    return commandResultSelector(req, true,
                            CommandFactory.getInstance().getByName(ConstantClass.COMMAND_GET_ALL_ORDERS));
                }
            } else if (!"".equals(damageSumString) && !"".equals(damageDescriptionString)) {
                int damageSum = Integer.parseInt(damageSumString);
                if (!ParamsValidatorUtil.checkAllNonNegative(damageSum)) {
                    throw new NumberFormatException();
                }
                damage = new Damage(damageSum,
                        damageDescriptionString,
                        damagePaidString != null);
                if (!ServiceFactoryImplementation.getInstance().getDamageService().persist(damage)) {
                    logger.info("invalid data on persist damage");
                    req.getSession().setAttribute(ConstantClass.ERROR_STATUS, "error.edit.order");
                    return commandResultSelector(req, true,
                            CommandFactory.getInstance().getByName(ConstantClass.COMMAND_GET_ALL_ORDERS));
                }
            }

            Order order = ServiceFactoryImplementation.getInstance().getOrderService().read(orderId);
            if (order == null) {
                throw new CommandExecutionException();
            }
            order.setStatus(Order.OrderStatus.valueOf(req.getParameter(ConstantClass.STATUS)));
            order.setPlaceFrom(new DeliveryPlace(placeFromId));
            order.setPlaceTo(new DeliveryPlace(placeToId));
            order.setUser(new User(userId));
            order.setDamage(damage);

            if (!ServiceFactoryImplementation.getInstance().getOrderService().update(order)) {
                logger.info("invalid data on update order");
                req.getSession().setAttribute(ConstantClass.ERROR_STATUS, "error.edit.order");
            }
        } catch (NumberFormatException | CommandExecutionException e) {
            logger.info("invalid data on edit order", e);
            req.getSession().setAttribute(ConstantClass.ERROR_STATUS, "error.edit.order");
        }
        return commandResultSelector(req, true,
                CommandFactory.getInstance().getByName(ConstantClass.COMMAND_GET_ALL_ORDERS));
    }
}
