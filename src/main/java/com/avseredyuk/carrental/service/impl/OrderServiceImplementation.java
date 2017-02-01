package com.avseredyuk.carrental.service.impl;

import com.avseredyuk.carrental.dao.impl.factory.MySqlDaoFactory;
import com.avseredyuk.carrental.domain.Automobile;
import com.avseredyuk.carrental.domain.Order;
import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.service.OrderService;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by lenfer on 1/14/17.
 */
public class OrderServiceImplementation implements OrderService {
    private static final Logger logger = Logger.getLogger(OrderServiceImplementation.class);

    @Override
    public synchronized boolean persist(Order order) {
        if (MySqlDaoFactory.getInstance()
                .getAutomobileDao()
                .getFreeAutomobilesInDateRange(order.getDateFrom(), order.getDateTo())
                .stream()
                .map(Automobile::getId)
                .filter(id -> id.equals(order.getAutomobile().getId()))
                .count() != 0) {
            return MySqlDaoFactory.getInstance().getOrderDao().persist(order);
        } else {
            logger.error(String.format("Error persisting order %s", order));
            return false;
        }
    }

    @Override
    public Order read(int key) {
        return MySqlDaoFactory.getInstance().getOrderDao().read(key);
    }

    @Override
    public boolean update(Order object) {
        return MySqlDaoFactory.getInstance().getOrderDao().update(object);
    }

    @Override
    public boolean delete(Order object) {
        return MySqlDaoFactory.getInstance().getOrderDao().delete(object);
    }

    @Override
    public List<Order> findAll() {
        return MySqlDaoFactory.getInstance().getOrderDao().findAll();
    }

    @Override
    public List<Order> findAll(int startIndex, int size) {
        return MySqlDaoFactory.getInstance().getOrderDao().findAll(startIndex, size);
    }

    @Override
    public int getCount() {
        return MySqlDaoFactory.getInstance().getOrderDao().getCount();
    }

    @Override
    public List<Order> findAllLastRange(int startIndex, int size) {
        return MySqlDaoFactory.getInstance().getOrderDao().findAllLastRange(startIndex, size);
    }

    @Override
    public List<Order> findAllByUserSortedByDateRange(User user, int startIndex, int size) {
        return MySqlDaoFactory.getInstance().getOrderDao().findAllByUserSortedByDateRange(user, startIndex, size);
    }

    @Override
    public int countAllByUser(User user) {
        return MySqlDaoFactory.getInstance().getOrderDao().countAllByUser(user);
    }
}
