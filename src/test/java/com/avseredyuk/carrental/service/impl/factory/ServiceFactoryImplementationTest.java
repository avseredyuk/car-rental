package com.avseredyuk.carrental.service.impl.factory;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.Assert.*;

/**
 * Created by lenfer on 1/29/17.
 */
public class ServiceFactoryImplementationTest {
    @Test
    public void getAutomobileService() throws Exception {
        assertNotNull(ServiceFactoryImplementation.getInstance().getAutomobileService());
    }

    @Test
    public void getUserService() throws Exception {
        assertNotNull(ServiceFactoryImplementation.getInstance().getUserService());
    }

    @Test
    public void getAuthorizationService() throws Exception {
        assertNotNull(ServiceFactoryImplementation.getInstance().getAuthorizationService());
    }

    @Test
    public void getDeliveryPlaceService() throws Exception {
        assertNotNull(ServiceFactoryImplementation.getInstance().getDeliveryPlaceService());
    }

    @Test
    public void getDamageService() throws Exception {
        assertNotNull(ServiceFactoryImplementation.getInstance().getDamageService());
    }

    @Test
    public void getOrderService() throws Exception {
        assertNotNull(ServiceFactoryImplementation.getInstance().getOrderService());
    }

}