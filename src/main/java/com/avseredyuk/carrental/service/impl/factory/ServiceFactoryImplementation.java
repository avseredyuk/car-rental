package com.avseredyuk.carrental.service.impl.factory;

import com.avseredyuk.carrental.service.ServiceFactory;
import com.avseredyuk.carrental.service.impl.*;

/**
 * Created by lenfer on 1/8/17.
 */
public class ServiceFactoryImplementation implements ServiceFactory {
    private static final ServiceFactoryImplementation instance = new ServiceFactoryImplementation();
    private final AutomobileServiceImplementation automobileServiceImplementation = new AutomobileServiceImplementation();
    private final UserServiceImplementation userServiceImplementation = new UserServiceImplementation();
    private final AuthorizationServiceImplementation authorizationServiceImplementation = new AuthorizationServiceImplementation();
    private final DeliveryPlaceServiceImplementation deliveryPlaceServiceImplementation = new DeliveryPlaceServiceImplementation();
    private final DamageServiceImplementation damageServiceImplementation = new DamageServiceImplementation();
    private final OrderServiceImplementation orderServiceImplementation = new OrderServiceImplementation();

    private ServiceFactoryImplementation() {}

    public static ServiceFactoryImplementation getInstance() {
        return instance;
    }

    @Override
    public AutomobileServiceImplementation getAutomobileService() {
        return automobileServiceImplementation;
    }

    @Override
    public UserServiceImplementation getUserService() {
        return userServiceImplementation;
    }

    @Override
    public AuthorizationServiceImplementation getAuthorizationService() {
        return authorizationServiceImplementation;
    }

    @Override
    public DeliveryPlaceServiceImplementation getDeliveryPlaceService() {
        return deliveryPlaceServiceImplementation;
    }

    @Override
    public DamageServiceImplementation getDamageService() {
        return damageServiceImplementation;
    }

    @Override
    public OrderServiceImplementation getOrderService() {
        return orderServiceImplementation;
    }

}
