package com.avseredyuk.carrental.web.command.impl.order.strategy.impl;

import com.avseredyuk.carrental.domain.Automobile;
import com.avseredyuk.carrental.domain.DeliveryPlace;
import com.avseredyuk.carrental.domain.Order;
import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.command.impl.order.strategy.OrderStepProcessor;
import com.avseredyuk.carrental.web.util.ConfigurationManager;
import com.avseredyuk.carrental.web.util.ParametersVerifier;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.ResponseWrapper;
import com.avseredyuk.carrental.web.util.wrapper.SessionWrapper;

import java.util.Date;

import static com.avseredyuk.carrental.web.util.ConstantClass.*;

/**
 * Created by lenfer on 1/27/17.
 *
 * FourthStepProcessor class is used to actually persist Order from all the params that have been selected by user
 * and are stored in session. If params are OK and automobile is still non used we persist Order and show
 * greetings page (and don't forget to clear orders' params from session).
 * If some params in session are missing or someone has took automobile before user confirmed the order - that's an obvious error page.
 * Input: nothing but all params in session
 * Output: nothing
 *
 */
public class FourthStepProcessor implements OrderStepProcessor {
    @Override
    public String process(RequestWrapper req, ResponseWrapper resp) {
        SessionWrapper session = req.getSession();
        if (ParametersVerifier.checkAllNotNull(session.getAttribute(PLACE_FROM), session.getAttribute(PLACE_TO),
                session.getAttribute(AUTOMOBILE), session.getAttribute(USERLOGIN), session.getAttribute(DATE_FROM),
                session.getAttribute(DATE_TO), session.getAttribute(ORDER_SUM))) {
            DeliveryPlace placeFrom = (DeliveryPlace) session.getAttribute(PLACE_FROM);
            DeliveryPlace placeTo = (DeliveryPlace) session.getAttribute(PLACE_TO);
            Automobile automobile = (Automobile) session.getAttribute(AUTOMOBILE);
            User user = ServiceFactoryImplementation.getInstance().getUserService().getByLogin((String)  session.getAttribute(USERLOGIN));
            Date dateFrom = (Date) session.getAttribute(DATE_FROM);
            Date dateTo = (Date) session.getAttribute(DATE_TO);
            Integer sum = Integer.parseInt((String) session.getAttribute(ORDER_SUM));
            Order order = new Order(placeFrom, placeTo, automobile, user, dateFrom, dateTo, Order.OrderStatus.CREATED, sum);
            if (!ServiceFactoryImplementation.getInstance().getOrderService().persist(order)) {
                req.setAttribute(ERROR_STATUS, "makeorder.order.error.already.taken");
                return ConfigurationManager.getProperty("path.page.error.makeorder");
            } else {
                session.removeAttribute(PLACE_FROM);
                session.removeAttribute(PLACE_TO);
                session.removeAttribute(DATE_FROM);
                session.removeAttribute(DATE_TO);
                session.removeAttribute(AUTOMOBILE);
                session.removeAttribute(ORDER_SUM);
                return ConfigurationManager.getProperty("path.page.makeorder.5");
            }
        }
        return CommandFactory.getInstance().getByName(COMMAND_SHOW_NOT_FOUND).execute(req, resp);
    }
}
