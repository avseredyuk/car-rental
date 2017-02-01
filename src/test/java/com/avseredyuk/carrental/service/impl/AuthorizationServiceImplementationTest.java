package com.avseredyuk.carrental.service.impl;

import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.service.AuthorizationService;
import com.avseredyuk.carrental.service.UserService;
import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.util.RandomUtil;
import com.avseredyuk.carrental.web.util.HashingUtil;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.SessionWrapper;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static com.avseredyuk.carrental.web.util.ConstantClass.*;

/**
 * Created by lenfer on 2/1/17.
 */
public class AuthorizationServiceImplementationTest {
    public static final String SESSION_LOCALE = "qwerty";
    public static final String USER_PASSWORD_2 = "asfdgg";
    RequestWrapper req = mock(RequestWrapper.class);
    SessionWrapper session = mock(SessionWrapper.class);
    AuthorizationService authService = ServiceFactoryImplementation.getInstance().getAuthorizationService();
    UserService userService = ServiceFactoryImplementation.getInstance().getUserService();

    @Test
    public void logout() throws Exception {
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute(LOCALE)).thenReturn(SESSION_LOCALE);
        authService.logout(req);
        verify(session).invalidate();
        verify(session).setAttribute(LOCALE, SESSION_LOCALE);
    }

    @Test
    public void checkRoleNull() throws Exception {
        when(session.getAttribute(USERROLE)).thenReturn(null);
        assertFalse(authService.checkRole(User.Role.CLIENT, session));
        assertFalse(authService.checkRole(User.Role.ADMINISTRATOR, session));
    }

    @Test
    public void checkRoleClient() throws Exception {
        when(session.getAttribute(USERROLE)).thenReturn(User.Role.CLIENT.name());
        assertTrue(authService.checkRole(User.Role.CLIENT, session));
        assertFalse(authService.checkRole(User.Role.ADMINISTRATOR, session));
    }

    @Test
    public void checkRoleAdministrator() throws Exception {
        when(session.getAttribute(USERROLE)).thenReturn(User.Role.ADMINISTRATOR.name());
        assertTrue(authService.checkRole(User.Role.CLIENT, session));
        assertTrue(authService.checkRole(User.Role.ADMINISTRATOR, session));
    }

    @Test
    public void loginInvalidLogin() throws Exception {
        User user = RandomUtil.getUser();
        assertFalse(authService.login(user.getLogin(), user.getPassword(), session));
    }

    @Test
    public void loginInvalidPassword() throws Exception {
        User user = RandomUtil.getUser();
        userService.persist(user);
        assertFalse(authService.login(user.getLogin(), USER_PASSWORD_2, session));
    }

    @Test
    public void loginDataValid() throws Exception {
        User user = RandomUtil.getUser();
        String cleartextPassword = user.getPassword();
        user.setPassword(HashingUtil.hashPassword(cleartextPassword));
        userService.persist(user);
        assertTrue(authService.login(user.getLogin(), cleartextPassword, session));
    }

}