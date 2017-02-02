package com.avseredyuk.carrental.web.command.impl.order.strategy.impl;

import com.avseredyuk.carrental.domain.Automobile;
import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.command.impl.order.strategy.OrderStepProcessor;
import com.avseredyuk.carrental.web.command.result.CommandResult;
import com.avseredyuk.carrental.web.exception.CommandExecutionException;
import com.avseredyuk.carrental.web.util.ConfigurationManager;
import com.avseredyuk.carrental.web.util.InvoiceUtil;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.SessionWrapper;
import org.apache.log4j.Logger;

import java.util.Date;
import static com.avseredyuk.carrental.web.util.ConstantClass.*;


/**
 * Created by lenfer on 1/27/17.
 *
 * ThirdStepProcessor class is used when client has selected automobile. We can calculate total orders' price.
 * Decision of which jsp to load next is made based on whether the user is logged in or not.
 * So if it's guest then page is used twice: first when we ask user to log in/register, second when we show orders' sum.
 * When it's logged in we skip first substep and show straightway orders' sum.
 * Input: automobile ID (from request - optional, from session - obligatory)
 * Output: Automobile, total sum of order (both to session)
 */
public class ThirdStepProcessor implements OrderStepProcessor {
    private static final Logger logger = Logger.getLogger(ThirdStepProcessor.class);

    @Override
    public CommandResult process(RequestWrapper req) {
        SessionWrapper session = req.getSession();
        String login = (String) session.getAttribute(USERLOGIN);
        try {
            Automobile automobile;
            if (req.getParameter(AUTOMOBILES) != null) {
                int automobileId = Integer.parseInt(req.getParameter(AUTOMOBILES));
                automobile = ServiceFactoryImplementation.getInstance().getAutomobileService().read(automobileId);
                if (automobile == null) {
                    throw new CommandExecutionException();
                }
                session.setAttribute(AUTOMOBILE, automobile);
            } else if (session.getAttribute(AUTOMOBILE) != null) {
                automobile = (Automobile) session.getAttribute(AUTOMOBILE);
            } else {
                logger.info("automobile not found on step3");
                return CommandFactory.getInstance().getByName(COMMAND_SHOW_NOT_FOUND).execute(req);
            }
            session.setAttribute(ORDER_SUM, InvoiceUtil.calculateSum(automobile,
                    (Date) session.getAttribute(DATE_FROM),
                    (Date) session.getAttribute(DATE_TO)).toString());

        } catch (CommandExecutionException e) {
            logger.info("automobile read error", e);
            return new CommandResult(ConfigurationManager.getProperty("path.page.error.makeorder"),
                    CommandResult.ActionType.FORWARD);
        }
        return login == null ? new CommandResult(ConfigurationManager.getProperty("path.page.makeorder.3"),
                CommandResult.ActionType.FORWARD) :
                new CommandResult(ConfigurationManager.getProperty("path.page.makeorder.4"),
                CommandResult.ActionType.FORWARD);
    }
}
