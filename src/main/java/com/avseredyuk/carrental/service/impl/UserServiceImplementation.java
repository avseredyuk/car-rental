package com.avseredyuk.carrental.service.impl;

import com.avseredyuk.carrental.dao.impl.factory.MySqlDaoFactory;
import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.service.UserService;

import java.util.List;

/**
 * Created by lenfer on 1/11/17.
 */
public class UserServiceImplementation implements UserService {
    @Override
    public boolean persist(User object) {
        return MySqlDaoFactory.getInstance().getUserDao().persist(object);
    }

    @Override
    public User read(int key) {
        return MySqlDaoFactory.getInstance().getUserDao().read(key);
    }

    @Override
    public boolean update(User object) {
        return MySqlDaoFactory.getInstance().getUserDao().update(object);
    }

    @Override
    public boolean delete(User object) {
        return MySqlDaoFactory.getInstance().getUserDao().delete(object);
    }

    @Override
    public List<User> findAll() {
        return MySqlDaoFactory.getInstance().getUserDao().findAll();
    }

    @Override
    public List<User> findAll(int startIndex, int size) {
        return MySqlDaoFactory.getInstance().getUserDao().findAll(startIndex, size);
    }

    @Override
    public User getByLogin(String login) {
        return MySqlDaoFactory.getInstance().getUserDao().getByLogin(login);
    }

    @Override
    public int getCount() {
        return MySqlDaoFactory.getInstance().getUserDao().getCount();
    }

    @Override
    public List<User> findAllLastRange(int startIndex, int size) {
        return MySqlDaoFactory.getInstance().getUserDao().findAllLastRange(startIndex, size);
    }
}
