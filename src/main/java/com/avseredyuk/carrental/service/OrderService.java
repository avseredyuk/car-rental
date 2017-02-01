package com.avseredyuk.carrental.service;

import com.avseredyuk.carrental.domain.Order;
import com.avseredyuk.carrental.domain.User;

import java.util.List;

/**
 * Created by lenfer on 1/14/17.
 */
public interface OrderService extends CrudService<Order> {
    List<Order> findAllLastRange(int startIndex, int size);
    List<Order> findAllByUserSortedByDateRange(User user, int startIndex, int size);
    int countAllByUser(User user);
}
