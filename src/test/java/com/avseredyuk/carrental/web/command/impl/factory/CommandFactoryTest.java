package com.avseredyuk.carrental.web.command.impl.factory;

import com.avseredyuk.carrental.util.Utils;
import com.avseredyuk.carrental.web.command.impl.CommandChangeItemsPerPage;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static com.avseredyuk.carrental.web.util.ConstantClass.*;

/**
 * Created by lenfer on 2/1/17.
 */
public class CommandFactoryTest extends Utils{
    public static final String UNKNOWN_COMMAND = "agshdgf";
    RequestWrapper req = mock(RequestWrapper.class);
    CommandFactory factory = CommandFactory.getInstance();

    @Before
    public void setUp() throws Exception {
        resetDB();
    }

    @Test
    public void getCommandEmpty() throws Exception {
        when(req.getParameter(COMMAND)).thenReturn(null);
        assertEquals(factory.getByName(COMMAND_SHOW_INDEX),
                factory.getCommand(req));
    }

    @Test
    public void getCommandInvalid() throws Exception {
        when(req.getParameter(COMMAND)).thenReturn(UNKNOWN_COMMAND);
        assertEquals(factory.getByName(COMMAND_SHOW_NOT_FOUND),
                factory.getCommand(req));
    }

    @Test
    public void getCommandValid() throws Exception {
        when(req.getParameter(COMMAND)).thenReturn(COMMAND_CHANGE_ITEMSPERPAGE);
        assertTrue(factory.getCommand(req) instanceof CommandChangeItemsPerPage);
    }

}