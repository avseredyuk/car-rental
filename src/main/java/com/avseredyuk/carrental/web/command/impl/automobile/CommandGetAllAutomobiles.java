package com.avseredyuk.carrental.web.command.impl.automobile;

import com.avseredyuk.carrental.domain.Automobile;
import com.avseredyuk.carrental.domain.DeliveryPlace;
import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.util.ConfigurationManager;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.PaginationInformation;
import com.avseredyuk.carrental.web.util.PaginationUtil;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.ResponseWrapper;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by lenfer on 1/6/17.
 */
public class CommandGetAllAutomobiles implements Command {
    private static final Logger logger = Logger.getLogger(CommandGetAllAutomobiles.class);

    @Override
    public String execute(RequestWrapper req, ResponseWrapper resp) {
        if (!ServiceFactoryImplementation.getInstance().getAuthorizationService().checkRole(User.Role.ADMINISTRATOR, req.getSession())) {
            logger.info("trying to access without permissions");
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_FORBIDDEN).execute(req, resp);
        }

        String page = ConfigurationManager.getProperty("path.page.getallautomobiles");

        PaginationInformation pageInfo = PaginationUtil.getPaginationInformation(req,
                ServiceFactoryImplementation.getInstance().getAutomobileService().getCount());

        List<Automobile> automobiles = ServiceFactoryImplementation.getInstance().getAutomobileService()
                .findAll(pageInfo.getStartIndex(), pageInfo.getItemsPerPage());
        req.setAttribute(ConstantClass.AUTOMOBILES, automobiles);
        List<DeliveryPlace> places = ServiceFactoryImplementation.getInstance().getDeliveryPlaceService().findAll();
        req.setAttribute(ConstantClass.PLACES, places);
        req.setAttribute(ConstantClass.PAGINATION_INFORMATION, pageInfo);
        req.setAttribute(ConstantClass.COMMAND, ConstantClass.COMMAND_GET_ALL_AUTOMOBILES.toLowerCase());

        return page;
    }
}

