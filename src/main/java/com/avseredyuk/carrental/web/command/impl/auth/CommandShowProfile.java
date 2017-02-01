package com.avseredyuk.carrental.web.command.impl.auth;

import com.avseredyuk.carrental.domain.Order;
import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.exception.CommandExecutionException;
import com.avseredyuk.carrental.web.util.ConfigurationManager;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.PaginationInformation;
import com.avseredyuk.carrental.web.util.PaginationUtil;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.ResponseWrapper;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by lenfer on 1/25/17.
 */
public class CommandShowProfile implements Command {
    private static final Logger logger = Logger.getLogger(CommandShowProfile.class);

    @Override
    public String execute(RequestWrapper req, ResponseWrapper resp) {
        if (!ServiceFactoryImplementation.getInstance().getAuthorizationService().checkRole(User.Role.CLIENT, req.getSession())) {
            logger.info("trying to access without permissions");
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_FORBIDDEN).execute(req, resp);
        }
        try {
            User user = ServiceFactoryImplementation.getInstance().getUserService().getByLogin(
                    (String) req.getSession().getAttribute(ConstantClass.USERLOGIN));
            if (user == null) {
                throw new CommandExecutionException();
            }

            PaginationInformation pageInfo = PaginationUtil.getPaginationInformation(req,
                    ServiceFactoryImplementation.getInstance().getOrderService().countAllByUser(user));

            List<Order> userOrders = ServiceFactoryImplementation
                    .getInstance()
                    .getOrderService()
                    .findAllByUserSortedByDateRange(user, pageInfo.getStartIndex(), pageInfo.getItemsPerPage());
            req.setAttribute(ConstantClass.USER, user);
            req.setAttribute(ConstantClass.ORDERS, userOrders);
            req.setAttribute(ConstantClass.PAGINATION_INFORMATION, pageInfo);
            req.setAttribute(ConstantClass.COMMAND, ConstantClass.COMMAND_SHOW_PROFILE.toLowerCase());
        } catch (CommandExecutionException e) {
            logger.info("error on show profile", e);
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_NOT_FOUND).execute(req, resp);
        }
        return ConfigurationManager.getProperty("path.page.showprofile");
    }
}
