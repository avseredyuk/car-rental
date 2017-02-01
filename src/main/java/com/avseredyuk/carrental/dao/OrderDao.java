package com.avseredyuk.carrental.dao;

import com.avseredyuk.carrental.domain.Order;
import com.avseredyuk.carrental.domain.User;

import java.util.List;

/**
 * Created by lenfer on 12/22/16.
 */
public interface OrderDao extends CrudDao<Order> {
    List<Order> findAllLastRange(int startIndex, int size);
    List<Order> findAllByUserSortedByDateRange(User user, int startIndex, int size);
    int countAllByUser(User user);
}
