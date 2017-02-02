package com.avseredyuk.carrental.service;

import com.avseredyuk.carrental.domain.DeliveryPlace;

import java.util.List;

/**
 * Created by lenfer on 1/13/17.
 */
public interface DeliveryPlaceService extends CrudService<DeliveryPlace> {
    List<DeliveryPlace> findAllOffices();
}
