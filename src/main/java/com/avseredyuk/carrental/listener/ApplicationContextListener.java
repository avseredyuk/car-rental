package com.avseredyuk.carrental.listener;

import com.avseredyuk.carrental.dao.impl.pool.PoolWorker;
import com.avseredyuk.carrental.domain.Automobile;
import com.avseredyuk.carrental.domain.DeliveryPlace;
import com.avseredyuk.carrental.domain.Order;
import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.web.util.ConstantClass;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by lenfer on 1/31/17.
 */
public class ApplicationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        context.setAttribute(ConstantClass.CATEGORY_VALUES, Automobile.Category.values());
        context.setAttribute(ConstantClass.FUEL_VALUES, Automobile.Fuel.values());
        context.setAttribute(ConstantClass.TRANSMISSION_VALUES, Automobile.Transmission.values());
        context.setAttribute(ConstantClass.ROLES_VALUES, User.Role.values());
        context.setAttribute(ConstantClass.STATUS_VALUES, Order.OrderStatus.values());
        context.setAttribute(ConstantClass.PLACETYPE_VALUES, DeliveryPlace.DeliveryPlaceType.values());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        PoolWorker.getInstance().close();
    }
}
