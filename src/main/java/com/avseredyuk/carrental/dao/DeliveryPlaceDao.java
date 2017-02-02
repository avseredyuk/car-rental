package com.avseredyuk.carrental.dao;

import com.avseredyuk.carrental.dao.CrudDao;
import com.avseredyuk.carrental.domain.DeliveryPlace;

import java.util.List;

/**
 * Created by lenfer on 1/13/17.
 */
public interface DeliveryPlaceDao extends CrudDao<DeliveryPlace> {
    List<DeliveryPlace> findAllOffices();
}
