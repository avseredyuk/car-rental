package com.avseredyuk.carrental.service.impl;

import com.avseredyuk.carrental.dao.impl.factory.MySqlDaoFactory;
import com.avseredyuk.carrental.domain.Damage;
import com.avseredyuk.carrental.service.DamageService;

import java.util.List;

/**
 * Created by lenfer on 1/14/17.
 */
public class DamageServiceImplementation implements DamageService {
    @Override
    public boolean persist(Damage object) {
        return MySqlDaoFactory.getInstance().getDamageDao().persist(object);
    }

    @Override
    public Damage read(int key) {
        return MySqlDaoFactory.getInstance().getDamageDao().read(key);
    }

    @Override
    public boolean update(Damage object) {
        return MySqlDaoFactory.getInstance().getDamageDao().update(object);
    }

    @Override
    public boolean delete(Damage object) {
        return MySqlDaoFactory.getInstance().getDamageDao().delete(object);
    }

    @Override
    public List<Damage> findAll() {
        return MySqlDaoFactory.getInstance().getDamageDao().findAll();
    }

    @Override
    public List<Damage> findAll(int startIndex, int size) {
        return MySqlDaoFactory.getInstance().getDamageDao().findAll(startIndex, size);
    }

    @Override
    public int getCount() {
        return MySqlDaoFactory.getInstance().getDamageDao().getCount();
    }
}
