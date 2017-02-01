package com.avseredyuk.carrental.web.command.impl.order;

import com.avseredyuk.carrental.domain.*;
import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.ResponseWrapper;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lenfer on 1/19/17.
 */
public class CommandEditOrder implements Command {
    private static final Logger logger = Logger.getLogger(CommandEditOrder.class);

    @Override
    public String execute(RequestWrapper req, ResponseWrapper resp) {
        if (!ServiceFactoryImplementation.getInstance().getAuthorizationService().checkRole(User.Role.ADMINISTRATOR, req.getSession())) {
            logger.info("trying to access without permissions");
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_FORBIDDEN).execute(req, resp);
        }
        try {
            int orderId = Integer.parseInt(req.getParameter(ConstantClass.ORDER_ID));
            int placeFromId = Integer.parseInt(req.getParameter(ConstantClass.PLACES_FROM));
            int placeToId = Integer.parseInt(req.getParameter(ConstantClass.PLACES_TO));
            int automobileId = Integer.parseInt(req.getParameter(ConstantClass.AUTOMOBILES));
            int userId = Integer.parseInt(req.getParameter(ConstantClass.USERS));

            Damage damage = null;
            String damageIdString = req.getParameter(ConstantClass.DAMAGE_ID);
            String damageSumString = req.getParameter(ConstantClass.DAMAGE_SUM);
            String damageDescriptionString = req.getParameter(ConstantClass.DAMAGE_DESCRIPTION);
            String damagePaidString = req.getParameter(ConstantClass.DAMAGE_PAID);
            if (!"".equals(damageIdString)) {
                damage = new Damage(Integer.parseInt(damageIdString),
                        Integer.parseInt(damageSumString),
                        damageDescriptionString,
                        damagePaidString != null);
                if (!ServiceFactoryImplementation.getInstance().getDamageService().update(damage)) {
                    logger.info("invalid data on update damage");
                    req.getSession().setAttribute(ConstantClass.ERROR_STATUS, "error.edit.order");
                    doReturnIfPossible(req, resp, true);
                    return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_GET_ALL_ORDERS).execute(req, resp);
                }
            } else if (!"".equals(damageSumString) && !"".equals(damageDescriptionString)) {
                damage = new Damage(Integer.parseInt(damageSumString),
                        damageDescriptionString,
                        damagePaidString != null);
                if (!ServiceFactoryImplementation.getInstance().getDamageService().persist(damage)) {
                    logger.info("invalid data on persist damage");
                    req.getSession().setAttribute(ConstantClass.ERROR_STATUS, "error.edit.order");
                    doReturnIfPossible(req, resp, true);
                    return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_GET_ALL_ORDERS).execute(req, resp);
                }
            }

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date dateFrom = simpleDateFormat.parse(req.getParameter(ConstantClass.DATE_FROM));
            Date dateTo = simpleDateFormat.parse(req.getParameter(ConstantClass.DATE_TO));
            Order.OrderStatus orderStatus = Order.OrderStatus.valueOf(req.getParameter(ConstantClass.STATUS));
            int sum = Integer.parseInt(req.getParameter(ConstantClass.ORDER_SUM));

            DeliveryPlace placeFrom = new DeliveryPlace(placeFromId);
            DeliveryPlace placeTo = new DeliveryPlace(placeToId);
            Automobile automobile = new Automobile(automobileId);
            User user = new User(userId);
            Order order = new Order(orderId, placeFrom, placeTo, automobile, user, damage, dateFrom, dateTo, orderStatus, sum);
            if (!ServiceFactoryImplementation.getInstance().getOrderService().update(order)) {
                logger.info("invalid data on update order");
                req.getSession().setAttribute(ConstantClass.ERROR_STATUS, "error.edit.order");
            }
        } catch(NumberFormatException | ParseException e) {
            logger.info("invalid data on edit order", e);
            req.getSession().setAttribute(ConstantClass.ERROR_STATUS, "error.edit.order");
        }
        doReturnIfPossible(req, resp, true);
        return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_GET_ALL_ORDERS).execute(req, resp);
    }
}
