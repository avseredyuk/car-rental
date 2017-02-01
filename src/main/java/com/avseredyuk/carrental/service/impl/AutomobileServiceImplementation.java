package com.avseredyuk.carrental.service.impl;

import com.avseredyuk.carrental.dao.impl.factory.MySqlDaoFactory;
import com.avseredyuk.carrental.domain.Automobile;
import com.avseredyuk.carrental.service.AutomobileService;

import java.util.Date;
import java.util.List;

/**
 * Created by lenfer on 1/8/17.
 */
public class AutomobileServiceImplementation implements AutomobileService {
    @Override
    public boolean persist(Automobile object) {
        return MySqlDaoFactory.getInstance().getAutomobileDao().persist(object);
    }

    @Override
    public Automobile read(int key) {
        return MySqlDaoFactory.getInstance().getAutomobileDao().read(key);
    }

    @Override
    public boolean update(Automobile object) {
        return MySqlDaoFactory.getInstance().getAutomobileDao().update(object);
    }

    @Override
    public boolean delete(Automobile object) {
        return MySqlDaoFactory.getInstance().getAutomobileDao().delete(object);
    }

    @Override
    public List<Automobile> findAll() {
        return MySqlDaoFactory.getInstance().getAutomobileDao().findAll();
    }

    @Override
    public List<Automobile> findAll(int startIndex, int size) {
        return MySqlDaoFactory.getInstance().getAutomobileDao().findAll(startIndex, size);
    }

    @Override
    public int getCount() {
        return MySqlDaoFactory.getInstance().getAutomobileDao().getCount();
    }

    @Override
    public List<Automobile> getFreeAutomobilesInDateRange(Date dateFrom, Date dateTo) {
        return MySqlDaoFactory.getInstance().getAutomobileDao().getFreeAutomobilesInDateRange(dateFrom, dateTo);
    }
}
