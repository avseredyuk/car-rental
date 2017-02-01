package com.avseredyuk.carrental.web.command.impl.place;

import com.avseredyuk.carrental.domain.DeliveryPlace;
import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.util.ConfigurationManager;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.PaginationInformation;
import com.avseredyuk.carrental.web.util.PaginationUtil;
import com.avseredyuk.carrental.web.util.wrapper.*;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by lenfer on 1/13/17.
 */
public class CommandGetAllPlaces implements Command {
    private static final Logger logger = Logger.getLogger(CommandGetAllPlaces.class);

    @Override
    public String execute(RequestWrapper req, ResponseWrapper resp) {
        if (!ServiceFactoryImplementation.getInstance().getAuthorizationService().checkRole(User.Role.ADMINISTRATOR, req.getSession())) {
            logger.info("trying to access without permissions");
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_FORBIDDEN).execute(req, resp);
        }

        String page = ConfigurationManager.getProperty("path.page.getallplaces");

        PaginationInformation pageInfo = PaginationUtil.getPaginationInformation(req,
                ServiceFactoryImplementation.getInstance().getDeliveryPlaceService().getCount());

        List<DeliveryPlace> places = ServiceFactoryImplementation.getInstance().getDeliveryPlaceService()
                .findAll(pageInfo.getStartIndex(), pageInfo.getItemsPerPage());
        req.setAttribute(ConstantClass.PLACES, places);

        req.setAttribute(ConstantClass.PAGINATION_INFORMATION, pageInfo);
        req.setAttribute(ConstantClass.COMMAND, ConstantClass.COMMAND_GET_ALL_PLACES.toLowerCase());

        return page;
    }
}