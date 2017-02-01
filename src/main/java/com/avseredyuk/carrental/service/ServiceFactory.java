package com.avseredyuk.carrental.service;

import com.avseredyuk.carrental.service.impl.*;

/**
 * Created by lenfer on 1/8/17.
 */
public interface ServiceFactory {
    AutomobileServiceImplementation getAutomobileService();
    UserServiceImplementation getUserService();
    AuthorizationServiceImplementation getAuthorizationService();
    DeliveryPlaceServiceImplementation getDeliveryPlaceService();
    DamageServiceImplementation getDamageService();
    OrderServiceImplementation getOrderService();
}
