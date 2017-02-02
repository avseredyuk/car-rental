package com.avseredyuk.carrental.web.command.impl.order.strategy.impl;

import com.avseredyuk.carrental.domain.DeliveryPlace;
import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.web.command.impl.order.strategy.OrderStepProcessor;
import com.avseredyuk.carrental.web.command.result.CommandResult;
import com.avseredyuk.carrental.web.util.ConfigurationManager;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;

import java.util.Date;
import java.util.List;

import static com.avseredyuk.carrental.web.util.ConstantClass.*;

/**
 * Created by lenfer on 1/27/17.
 *
 * FirstStepProcessor class is used when 'MakeOrder' page is initially loaded with page parameter = 'step1'.
 * Input: nothing
 * Output: List of DeliveryPlaces, current date
 */
public class FirstStepProcessor implements OrderStepProcessor {
    @Override
    public CommandResult process(RequestWrapper req) {
        List<DeliveryPlace> places = ServiceFactoryImplementation.getInstance().getDeliveryPlaceService().findAll();
        req.setAttribute(PLACES, places);
        req.setAttribute(DATE_NOW, new Date());
        return new CommandResult(ConfigurationManager.getProperty("path.page.makeorder.1"),
                CommandResult.ActionType.FORWARD);
    }
}