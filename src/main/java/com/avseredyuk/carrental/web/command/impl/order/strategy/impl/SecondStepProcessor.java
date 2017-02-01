package com.avseredyuk.carrental.web.command.impl.order.strategy.impl;

import com.avseredyuk.carrental.domain.Automobile;
import com.avseredyuk.carrental.domain.DeliveryPlace;
import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.command.impl.order.strategy.OrderStepProcessor;
import com.avseredyuk.carrental.web.exception.CommandExecutionException;
import com.avseredyuk.carrental.web.util.ConfigurationManager;
import com.avseredyuk.carrental.web.util.ParametersVerifier;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.ResponseWrapper;
import com.avseredyuk.carrental.web.util.wrapper.SessionWrapper;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.avseredyuk.carrental.web.util.ConstantClass.*;

/**
 * Created by lenfer on 1/27/17.
 *
 * SecondStepProcessor class is used when dates & places are set, so we need to show to user list of automobiles
 * that he can order (i.e. automobiles that are not already used in selected by user date range).
 * Input: Date dateFrom, Date dateTo, DeliveryPlace placeFrom, DeliveryPlace placeTo
 *      Input params are loaded from request OR from session (when there are no params in request) OR command
 *      CommandShowNotFound executes if there are no params in request & session
 * Output: List of Automobiles
 *
 */
public class SecondStepProcessor implements OrderStepProcessor {
    private static final Logger logger = Logger.getLogger(SecondStepProcessor.class);

    @Override
    public String process(RequestWrapper req, ResponseWrapper resp) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date dateFrom;
        Date dateTo;
        DeliveryPlace placeFrom;
        DeliveryPlace placeTo;
        SessionWrapper session = req.getSession();
        try {
            if (ParametersVerifier.checkAllNotNull(req.getParameter(DATE_FROM), req.getParameter(DATE_TO),
                    req.getParameter(PLACE_FROM), req.getParameter(PLACE_TO))) {
                dateFrom = f.parse(req.getParameter(DATE_FROM));
                dateTo = f.parse(req.getParameter(DATE_TO));

                Integer placeFromId = Integer.parseInt(req.getParameter(PLACE_FROM));
                Integer placeToId = Integer.parseInt(req.getParameter(PLACE_TO));
                placeFrom = ServiceFactoryImplementation.getInstance().getDeliveryPlaceService().read(placeFromId);
                placeTo = ServiceFactoryImplementation.getInstance().getDeliveryPlaceService().read(placeToId);
                if (!ParametersVerifier.checkAllNotNull(placeFrom, placeTo)) {
                    throw new CommandExecutionException();
                }
                session.setAttribute(DATE_FROM, dateFrom);
                session.setAttribute(DATE_TO, dateTo);
                session.setAttribute(PLACE_FROM, placeFrom);
                session.setAttribute(PLACE_TO, placeTo);
            } else if (ParametersVerifier.checkAllNotNull(session.getAttribute(DATE_FROM), session.getAttribute(DATE_TO),
                    session.getAttribute(PLACE_FROM), session.getAttribute(PLACE_TO))) {
                dateFrom = (Date) session.getAttribute(DATE_FROM);
                dateTo = (Date) session.getAttribute(DATE_TO);
            } else {
                return CommandFactory.getInstance().getByName(COMMAND_SHOW_NOT_FOUND).execute(req, resp);
            }

            if (!dateFrom.before(dateTo)) {
                throw new CommandExecutionException();
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateFrom);
            cal.add(Calendar.HOUR_OF_DAY, MINIMUM_DIFF_HOURS);
            if (dateTo.before(cal.getTime())){
                throw new CommandExecutionException();
            }

            List<Automobile> automobiles = ServiceFactoryImplementation
                    .getInstance()
                    .getAutomobileService()
                    .getFreeAutomobilesInDateRange(dateFrom, dateTo);
            req.setAttribute(AUTOMOBILES, automobiles);

        } catch (ParseException e) {
            logger.info("parse error", e);
            return CommandFactory.getInstance().getByName(COMMAND_SHOW_NOT_FOUND).execute(req, resp);
        } catch (CommandExecutionException e) {
            logger.info("invalid data on making order", e);
            req.setAttribute(ERROR_STATUS, "makeorder.order.error.invalid.dates");
            return ConfigurationManager.getProperty("path.page.error.makeorder");
        }

        return ConfigurationManager.getProperty("path.page.makeorder.2");
    }
}
