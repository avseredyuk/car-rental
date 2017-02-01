package com.avseredyuk.carrental.dao.impl.factory;

import com.avseredyuk.carrental.dao.DaoFactory;
import com.avseredyuk.carrental.dao.impl.*;

/**
 * Created by lenfer on 12/22/16.
 */
public class MySqlDaoFactory implements DaoFactory {
    private static final MySqlDaoFactory instance = new MySqlDaoFactory();
    private final MySqlAutomobileDao mySqlAutomobileDao = new MySqlAutomobileDao();
    private final MySqlUserDao mySqlUserDao = new MySqlUserDao();
    private final MySqlDamageDao mySqlDamageDao = new MySqlDamageDao();
    private final MySqlOrderDao mySqlOrderDao = new MySqlOrderDao();
    private final MySqlDeliveryPlaceDao mySqlDeliveryPlaceDao = new MySqlDeliveryPlaceDao();

    private MySqlDaoFactory() {}

    public static MySqlDaoFactory getInstance() {
        return instance;
    }

    @Override
    public MySqlAutomobileDao getAutomobileDao() {
        return mySqlAutomobileDao;
    }

    @Override
    public MySqlUserDao getUserDao() {
        return mySqlUserDao;
    }

    @Override
    public MySqlDamageDao getDamageDao() {
        return mySqlDamageDao;
    }

    @Override
    public MySqlOrderDao getOrderDao() {
        return mySqlOrderDao;
    }

    @Override
    public MySqlDeliveryPlaceDao getDeliveryPlaceDao() {
        return mySqlDeliveryPlaceDao;
    }
}
