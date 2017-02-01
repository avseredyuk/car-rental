package com.avseredyuk.carrental.service;

import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.SessionWrapper;

/**
 * Created by lenfer on 1/11/17.
 */
public interface AuthorizationService {
    boolean login(String login, String password, SessionWrapper session);
    void logout(RequestWrapper req);
    boolean checkRole(User.Role role, SessionWrapper session);
}
