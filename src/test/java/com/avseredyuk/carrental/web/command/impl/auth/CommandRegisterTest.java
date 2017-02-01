package com.avseredyuk.carrental.web.command.impl.auth;

import com.avseredyuk.carrental.util.Utils;
import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.util.RandomUtil;
import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.ResponseWrapper;
import com.avseredyuk.carrental.web.util.wrapper.SessionWrapper;
import org.junit.Before;
import org.junit.Test;

import static com.avseredyuk.carrental.web.util.ConstantClass.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by lenfer on 1/31/17.
 */
public class CommandRegisterTest extends Utils {
    RequestWrapper req = mock(RequestWrapper.class);
    ResponseWrapper resp = mock(ResponseWrapper.class);
    SessionWrapper session = mock(SessionWrapper.class);
    Command command = CommandFactory.getInstance().getByName(COMMAND_REGISTER);

    @Before
    public void setUp() throws Exception {
        resetDB();
    }

    @Test
    public void executeEmptyParams() throws Exception {
        when(req.getParameter(USERLOGIN)).thenReturn("");
        when(req.getParameter(USEREMAIL)).thenReturn("");
        when(req.getParameter(USERPASSWORD)).thenReturn("");
        when(req.getParameter(USERNAME)).thenReturn("");
        when(req.getParameter(USERSURNAME)).thenReturn("");
        command.execute(req, resp);
        verify(req).setAttribute(ERROR_STATUS, "error.registration.empty");
    }

    @Test
    public void executeAlreadyExisting() throws Exception {
        User user = RandomUtil.getUser();
        ServiceFactoryImplementation.getInstance().getUserService().persist(user);
        when(req.getParameter(USERLOGIN)).thenReturn(user.getLogin());
        when(req.getParameter(USEREMAIL)).thenReturn(user.getEmail());
        when(req.getParameter(USERPASSWORD)).thenReturn(user.getPassword());
        when(req.getParameter(USERNAME)).thenReturn(user.getName());
        when(req.getParameter(USERSURNAME)).thenReturn(user.getSurname());
        command.execute(req, resp);
        verify(req).setAttribute(ERROR_STATUS, "error.registration.persist");
    }

    @Test
    public void executeValid() throws Exception {
        User user = RandomUtil.getUser();
        when(req.getParameter(USERLOGIN)).thenReturn(user.getLogin());
        when(req.getParameter(USEREMAIL)).thenReturn(user.getEmail());
        when(req.getParameter(USERPASSWORD)).thenReturn(user.getPassword());
        when(req.getParameter(USERNAME)).thenReturn(user.getName());
        when(req.getParameter(USERSURNAME)).thenReturn(user.getSurname());
        when(req.getSession()).thenReturn(session);
        command.execute(req, resp);

        verify(req, atLeast(1)).getSession();
        verify(session).setAttribute(USERROLE, User.Role.CLIENT.toString());
        verify(session).setAttribute(USERLOGIN, user.getLogin());

        assertNotNull(ServiceFactoryImplementation.getInstance().getUserService().getByLogin(user.getLogin()));
    }

}