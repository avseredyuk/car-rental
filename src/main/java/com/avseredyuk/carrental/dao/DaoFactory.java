package com.avseredyuk.carrental.dao;

import com.avseredyuk.carrental.dao.impl.*;

/**
 * Created by lenfer on 12/22/16.
 */
public interface DaoFactory {
    MySqlAutomobileDao getAutomobileDao();
    MySqlUserDao getUserDao();
    MySqlDamageDao getDamageDao();
    MySqlOrderDao getOrderDao();
    MySqlDeliveryPlaceDao getDeliveryPlaceDao();
}
