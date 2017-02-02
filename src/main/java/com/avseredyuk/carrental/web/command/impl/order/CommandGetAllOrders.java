package com.avseredyuk.carrental.web.command.impl.order;

import com.avseredyuk.carrental.domain.Order;
import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.command.result.CommandResult;
import com.avseredyuk.carrental.web.util.ConfigurationManager;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.PaginationInformation;
import com.avseredyuk.carrental.web.util.PaginationUtil;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by lenfer on 1/14/17.
 */
public class CommandGetAllOrders implements Command {
    private static final Logger logger = Logger.getLogger(CommandGetAllOrders.class);

    @Override
    public CommandResult execute(RequestWrapper req) {
        if (!ServiceFactoryImplementation.getInstance().getAuthorizationService().checkRole(User.Role.ADMINISTRATOR, req.getSession())) {
            logger.info("trying to access without permissions");
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_FORBIDDEN).execute(req);
        }
        PaginationInformation pageInfo = PaginationUtil.getPaginationInformation(req,
                ServiceFactoryImplementation.getInstance().getOrderService().getCount());

        List<Order> orders = ServiceFactoryImplementation.getInstance().getOrderService()
                .findAll(pageInfo.getStartIndex(), pageInfo.getItemsPerPage());
        req.setAttribute(ConstantClass.ORDERS, orders);

        req.setAttribute(ConstantClass.PAGINATION_INFORMATION, pageInfo);
        req.setAttribute(ConstantClass.COMMAND, ConstantClass.COMMAND_GET_ALL_ORDERS.toLowerCase());

        return new CommandResult(ConfigurationManager.getProperty("path.page.getallorders"),
                CommandResult.ActionType.FORWARD);
    }
}
