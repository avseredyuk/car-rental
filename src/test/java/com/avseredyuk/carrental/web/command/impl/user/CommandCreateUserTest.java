package com.avseredyuk.carrental.web.command.impl.user;

import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.util.Utils;
import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.SessionWrapper;
import org.junit.Before;
import org.junit.Test;

import static com.avseredyuk.carrental.web.util.ConstantClass.COMMAND_CREATE_USER;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by lenfer on 2/1/17.
 */
public class CommandCreateUserTest extends Utils {
    RequestWrapper req = mock(RequestWrapper.class);
    SessionWrapper session = mock(SessionWrapper.class);
    Command command = CommandFactory.getInstance().getByName(COMMAND_CREATE_USER);

    @Before
    public void setUp() throws Exception {
        resetDB();
    }

    @Test
    public void executeByGuest() throws Exception {
        when(req.getSession()).thenReturn(session);
        assertEquals(CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_FORBIDDEN).execute(req),
                command.execute(req));
    }

    @Test
    public void executeByClient() throws Exception {
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute(ConstantClass.USERROLE)).thenReturn(User.Role.CLIENT.name());
        assertEquals(CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_FORBIDDEN).execute(req),
                command.execute(req));
    }
}