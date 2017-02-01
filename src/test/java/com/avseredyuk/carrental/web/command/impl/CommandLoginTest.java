package com.avseredyuk.carrental.web.command.impl;

import com.avseredyuk.carrental.util.Utils;
import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.util.RandomUtil;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.exception.CommandRedirectException;
import com.avseredyuk.carrental.web.util.HashingUtil;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.ResponseWrapper;
import com.avseredyuk.carrental.web.util.wrapper.SessionWrapper;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static com.avseredyuk.carrental.web.util.ConstantClass.*;

/**
 * Created by lenfer on 1/30/17.
 */
public class CommandLoginTest extends Utils {
    public static final String REDIRECT_URL = "someurl";
    RequestWrapper req = mock(RequestWrapper.class);
    ResponseWrapper resp = mock(ResponseWrapper.class);
    SessionWrapper session = mock(SessionWrapper.class);

    @Before
    public void setUp() throws Exception {
        resetDB();
    }

    @Test
    public void executeEmptyParams() throws Exception {
        when(req.getParameter(USERLOGIN)).thenReturn("");
        when(req.getParameter(USERPASSWORD)).thenReturn("");

        CommandFactory.getInstance().getByName(COMMAND_LOGIN).execute(req, resp);

        verify(req).setAttribute(ERROR_STATUS, "error.login");
        verify(req, never()).getSession();
    }

    @Test
    public void executeInvalidParameters() throws Exception {
        User user = RandomUtil.getUser();
        when(req.getParameter(USERLOGIN)).thenReturn(user.getLogin());
        when(req.getParameter(USERPASSWORD)).thenReturn(user.getPassword());
        when(req.getSession()).thenReturn(session);

        CommandFactory.getInstance().getByName(COMMAND_LOGIN).execute(req, resp);

        verify(req).setAttribute(ERROR_STATUS, "error.login");
        verify(req).getSession();
        verify(session, never()).setAttribute(USERROLE, user.getRole().toString());
        verify(session, never()).setAttribute(USERLOGIN, user.getLogin());
    }

    @Test(expected=CommandRedirectException.class)
    public void executeValidParametersWithReturn() throws Exception {
        User user = RandomUtil.getUser();
        String cleartextPassword = user.getPassword();
        user.setPassword(HashingUtil.hashPassword(cleartextPassword));
        ServiceFactoryImplementation.getInstance().getUserService().persist(user);

        when(req.getParameter(USERLOGIN)).thenReturn(user.getLogin());
        when(req.getParameter(USERPASSWORD)).thenReturn(cleartextPassword);
        when(req.getSession()).thenReturn(session);
        when(req.getParameter(RETURN)).thenReturn(REDIRECT_URL);

        CommandFactory.getInstance().getByName(COMMAND_LOGIN).execute(req, resp);

        verify(req).getSession();
        verify(session).setAttribute(USERROLE, user.getRole().toString());
        verify(session).setAttribute(USERLOGIN, user.getLogin());
        verify(resp).sendRedirect(REDIRECT_URL);
    }

    @Test
    public void executeValidParameters() throws Exception {
        User user = RandomUtil.getUser();
        String cleartextPassword = user.getPassword();
        user.setPassword(HashingUtil.hashPassword(cleartextPassword));
        ServiceFactoryImplementation.getInstance().getUserService().persist(user);

        when(req.getParameter(USERLOGIN)).thenReturn(user.getLogin());
        when(req.getParameter(USERPASSWORD)).thenReturn(cleartextPassword);
        when(req.getSession()).thenReturn(session);

        CommandFactory.getInstance().getByName(COMMAND_LOGIN).execute(req, resp);

        verify(req, atLeast(1)).getSession();
        verify(session).setAttribute(USERROLE, user.getRole().toString());
        verify(session).setAttribute(USERLOGIN, user.getLogin());
    }

}