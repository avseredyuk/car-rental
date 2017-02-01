package com.avseredyuk.carrental.service.impl;

import com.avseredyuk.carrental.dao.impl.factory.MySqlDaoFactory;
import com.avseredyuk.carrental.domain.DeliveryPlace;
import com.avseredyuk.carrental.service.DeliveryPlaceService;

import java.util.List;

/**
 * Created by lenfer on 1/13/17.
 */
public class DeliveryPlaceServiceImplementation implements DeliveryPlaceService {
    @Override
    public boolean persist(DeliveryPlace object) {
        return MySqlDaoFactory.getInstance().getDeliveryPlaceDao().persist(object);
    }

    @Override
    public DeliveryPlace read(int key) {
        return MySqlDaoFactory.getInstance().getDeliveryPlaceDao().read(key);
    }

    @Override
    public boolean update(DeliveryPlace object) {
        return MySqlDaoFactory.getInstance().getDeliveryPlaceDao().update(object);
    }

    @Override
    public boolean delete(DeliveryPlace object) {
        return MySqlDaoFactory.getInstance().getDeliveryPlaceDao().delete(object);
    }

    @Override
    public List<DeliveryPlace> findAll() {
        return MySqlDaoFactory.getInstance().getDeliveryPlaceDao().findAll();
    }

    @Override
    public List<DeliveryPlace> findAll(int startIndex, int size) {
        return MySqlDaoFactory.getInstance().getDeliveryPlaceDao().findAll(startIndex, size);
    }

    @Override
    public int getCount() {
        return MySqlDaoFactory.getInstance().getDeliveryPlaceDao().getCount();
    }
}
