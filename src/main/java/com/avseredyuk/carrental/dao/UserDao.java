package com.avseredyuk.carrental.dao;

import com.avseredyuk.carrental.domain.User;

import java.util.List;

/**
 * Created by lenfer on 12/22/16.
 */
public interface UserDao extends CrudDao<User> {
    User getByLogin(String login);
    List<User> findAllLastRange(int startIndex, int size);
}
