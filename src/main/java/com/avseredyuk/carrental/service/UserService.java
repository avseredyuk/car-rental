package com.avseredyuk.carrental.service;

import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.service.CrudService;

import java.util.List;

/**
 * Created by lenfer on 1/11/17.
 */
public interface UserService extends CrudService<User> {
    User getByLogin(String login);
    List<User> findAllLastRange(int startIndex, int size);
}
