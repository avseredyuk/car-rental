package com.avseredyuk.carrental.web.command.impl.auth;

import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.util.RandomUtil;
import com.avseredyuk.carrental.util.Utils;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.ResponseWrapper;
import com.avseredyuk.carrental.web.util.wrapper.SessionWrapper;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static com.avseredyuk.carrental.web.util.ConstantClass.*;

/**
 * Created by lenfer on 2/1/17.
 */
public class CommandUpdateProfileTest extends Utils {
    public static final String ADMIN_LOGIN = "qwerty";
    public static final String USERNAME_2 = "adsfdf";
    public static final String USER_SURNAME_2 = "afsgdhfjg";
    public static final String USER_EMAIL_2 = "asgdhfjgh";
    RequestWrapper req = mock(RequestWrapper.class);
    ResponseWrapper resp = mock(ResponseWrapper.class);
    SessionWrapper session = mock(SessionWrapper.class);

    @Before
    public void setUp() throws Exception {
        resetDB();
    }

    @Test
    public void executeByGuest() throws Exception {
        when(req.getSession()).thenReturn(session);
        assertEquals(CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_FORBIDDEN).execute(req, resp),
                CommandFactory.getInstance().getByName(COMMAND_UPDATE_PROFILE).execute(req, resp));
    }

    @Test
    public void executeEmptyParams() throws Exception {
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute(ConstantClass.USERROLE)).thenReturn(User.Role.ADMINISTRATOR.name());
        when(session.getAttribute(ConstantClass.USERLOGIN)).thenReturn(ADMIN_LOGIN);
        when(req.getParameter(USERNAME)).thenReturn("");
        when(req.getParameter(USERSURNAME)).thenReturn("");
        when(req.getParameter(USEREMAIL)).thenReturn("");
        CommandFactory.getInstance().getByName(COMMAND_UPDATE_PROFILE).execute(req, resp);
        verify(req).setAttribute(ERROR_STATUS, "error.update.profile");
    }

    @Test
    public void executeInvalidLoginInSession() throws Exception {
        User user = RandomUtil.getUser();
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute(ConstantClass.USERROLE)).thenReturn(User.Role.ADMINISTRATOR.name());
        when(session.getAttribute(ConstantClass.USERLOGIN)).thenReturn(ADMIN_LOGIN);
        when(req.getParameter(USERNAME)).thenReturn(user.getName());
        when(req.getParameter(USERSURNAME)).thenReturn(user.getSurname());
        when(req.getParameter(USEREMAIL)).thenReturn(user.getEmail());
        CommandFactory.getInstance().getByName(COMMAND_UPDATE_PROFILE).execute(req, resp);
        verify(req).setAttribute(ERROR_STATUS, "error.update.profile");
    }

    @Test
    public void executeValidDataEmptyPassword() throws Exception {
        User user = RandomUtil.getUser();
        ServiceFactoryImplementation.getInstance().getUserService().persist(user);
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute(ConstantClass.USERROLE)).thenReturn(User.Role.ADMINISTRATOR.name());
        when(session.getAttribute(ConstantClass.USERLOGIN)).thenReturn(user.getLogin());
        when(req.getParameter(USERNAME)).thenReturn(USERNAME_2);
        when(req.getParameter(USERSURNAME)).thenReturn(USER_SURNAME_2);
        when(req.getParameter(USEREMAIL)).thenReturn(USER_EMAIL_2);
        when(req.getParameter(USERPASSWORD)).thenReturn("");
        CommandFactory.getInstance().getByName(COMMAND_UPDATE_PROFILE).execute(req, resp);
        verify(req).setAttribute(ERROR_STATUS, "success.update.profile");
    }



}