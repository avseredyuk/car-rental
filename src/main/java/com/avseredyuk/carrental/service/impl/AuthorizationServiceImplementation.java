package com.avseredyuk.carrental.service.impl;

import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.service.AuthorizationService;
import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.HashingUtil;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.SessionWrapper;
import org.apache.log4j.Logger;

/**
 * Created by lenfer on 1/11/17.
 */
public class AuthorizationServiceImplementation implements AuthorizationService {
    private static final Logger logger = Logger.getLogger(AuthorizationServiceImplementation.class);

    @Override
    public boolean login(String login, String password, SessionWrapper session) {
        User user = ServiceFactoryImplementation.getInstance().getUserService().getByLogin(login);
        if ((user != null) && (user.getPassword().equals(HashingUtil.hashPassword(password)))) {
            session.setAttribute(ConstantClass.USERROLE, user.getRole().toString());
            session.setAttribute(ConstantClass.USERLOGIN, user.getLogin());
            return true;
        } else {
            logger.error(String.format("Error logging in with login %s and password %s", login, password));
            return false;
        }
    }

    @Override
    public void logout(RequestWrapper req) {
        SessionWrapper session = req.getSession();
        String sessionLocale = (String) session.getAttribute(ConstantClass.LOCALE);
        session.invalidate();
        session = req.getSession();
        session.setAttribute(ConstantClass.LOCALE, sessionLocale);
    }

    @Override
    public boolean checkRole(User.Role role, SessionWrapper session) {
        String sessionRole = (String) session.getAttribute(ConstantClass.USERROLE);
        if(sessionRole == null) {
            return false;
        } else {
            if (sessionRole.equals(User.Role.CLIENT.name())) {
                return role.name().equals(sessionRole);
            } else {
                return true;
            }
        }
    }
}
