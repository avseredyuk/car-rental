package com.avseredyuk.carrental.web.command.impl;

import com.avseredyuk.carrental.domain.*;
import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.result.CommandResult;
import com.avseredyuk.carrental.web.util.*;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;

import java.util.List;

import static com.avseredyuk.carrental.web.util.ConstantClass.*;

/**
 * Created by lenfer on 1/14/17.
 */
public class CommandShowIndex implements Command {
    @Override
    public CommandResult execute(RequestWrapper req) {
        if (!ServiceFactoryImplementation.getInstance().getAuthorizationService().checkRole(User.Role.ADMINISTRATOR, req.getSession())) {
            PaginationInformation pageInfo = PaginationUtil.getPaginationInformation(req,
                    ServiceFactoryImplementation.getInstance().getAutomobileService().getCount());

            List<Automobile> automobiles = ServiceFactoryImplementation.getInstance().getAutomobileService()
                    .findAll(pageInfo.getStartIndex(), pageInfo.getItemsPerPage());
            req.setAttribute(AUTOMOBILES, automobiles);
            req.setAttribute(PAGINATION_INFORMATION, pageInfo);
            req.setAttribute(COMMAND, ConstantClass.COMMAND_SHOW_INDEX.toLowerCase());

            return new CommandResult(ConfigurationManager.getProperty("path.page.index"),
                    CommandResult.ActionType.FORWARD);
        } else {
            List<User> users = ServiceFactoryImplementation.getInstance().getUserService()
                    .findAllLastRange(0, 5);
            req.setAttribute(ConstantClass.USERS, users);
            List<Order> orders = ServiceFactoryImplementation.getInstance().getOrderService()
                    .findAllLastRange(0, 5);
            req.setAttribute(ConstantClass.ORDERS, orders);
            return new CommandResult(ConfigurationManager.getProperty("path.page.adminindex"),
                    CommandResult.ActionType.FORWARD);
        }
    }
}
