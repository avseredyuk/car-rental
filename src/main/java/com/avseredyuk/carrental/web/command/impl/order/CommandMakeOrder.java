package com.avseredyuk.carrental.web.command.impl.order;

import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.command.impl.order.strategy.OrderSteps;
import com.avseredyuk.carrental.web.command.result.CommandResult;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import org.apache.log4j.Logger;

/**
 * Created by lenfer on 1/19/17.
 *
 * Command 'CommandMakeOrder' selects which page to display by request parameter
 * "step" (ConstantClass.ORDERSTEP), which is processed via 'OrderStepProcessor' interface with its implementations.
 * Accordingly (by step) OrderStepProcessors' implementations select which data to retrieve from Services
 * and set them to request and/or session.
 * All the params that are set on each step are saved in session, so when coming back to one of the previous steps
 * user doesn't have to enter same things again.
 */
public class CommandMakeOrder implements Command {
    private static final Logger logger = Logger.getLogger(CommandMakeOrder.class);

    @Override
    public CommandResult execute(RequestWrapper req) {
        String step = req.getParameter(ConstantClass.ORDERSTEP);
        try {
            if (step == null) {
                throw new IllegalArgumentException();
            }
            return OrderSteps.valueOf(step.toUpperCase()).getProcessor().process(req);
        } catch (IllegalArgumentException e) {
            logger.error("error parsing steps at making order" + e);
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_NOT_FOUND).execute(req);
        }
    }
}
