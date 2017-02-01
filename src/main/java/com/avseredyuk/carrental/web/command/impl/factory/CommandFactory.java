package com.avseredyuk.carrental.web.command.impl.factory;

import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.impl.*;
import com.avseredyuk.carrental.web.command.impl.auth.*;
import com.avseredyuk.carrental.web.command.impl.automobile.*;
import com.avseredyuk.carrental.web.command.impl.order.*;
import com.avseredyuk.carrental.web.command.impl.place.*;
import com.avseredyuk.carrental.web.command.impl.user.*;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static com.avseredyuk.carrental.web.util.ConstantClass.*;

/**
 * Created by lenfer on 1/6/17.
 */
public class CommandFactory {
    private static final Logger logger = Logger.getLogger(CommandFactory.class);
    private static final CommandFactory instance = new CommandFactory();
    private final Map<String, Command> commands = new HashMap<>();

    private CommandFactory() {
        commands.put(COMMAND_SHOW_INDEX, new CommandShowIndex());
        commands.put(COMMAND_GET_ALL_AUTOMOBILES, new CommandGetAllAutomobiles());
        commands.put(COMMAND_GET_AUTOMOBILE, new CommandGetAutomobile());
        commands.put(COMMAND_DELETE_AUTOMOBILE, new CommandDeleteAutomobile());
        commands.put(COMMAND_EDIT_AUTOMOBILE, new CommandEditAutomobile());
        commands.put(COMMAND_CREATE_AUTOMOBILE, new CommandCreateAutomobile());
        commands.put(COMMAND_GET_ALL_USERS, new CommandGetAllUsers());
        commands.put(COMMAND_DELETE_USER, new CommandDeleteUser());
        commands.put(COMMAND_GET_USER, new CommandGetUser());
        commands.put(COMMAND_EDIT_USER, new CommandEditUser());
        commands.put(COMMAND_CREATE_USER, new CommandCreateUser());
        commands.put(COMMAND_SHOW_LOGIN, new CommandShowLogin());
        commands.put(COMMAND_LOGIN, new CommandLogin());
        commands.put(COMMAND_LOGOUT, new CommandLogout());
        commands.put(COMMAND_SHOW_REGISTER, new CommandShowRegister());
        commands.put(COMMAND_REGISTER, new CommandRegister());
        commands.put(COMMAND_GET_ALL_PLACES, new CommandGetAllPlaces());
        commands.put(COMMAND_CREATE_PLACE, new CommandCreatePlace());
        commands.put(COMMAND_DELETE_PLACE, new CommandDeletePlace());
        commands.put(COMMAND_GET_PLACE, new CommandGetPlace());
        commands.put(COMMAND_EDIT_PLACE, new CommandEditPlace());
        commands.put(COMMAND_SHOW_NOT_FOUND, new CommandShowNotFound());
        commands.put(COMMAND_GET_ALL_ORDERS, new CommandGetAllOrders());
        commands.put(COMMAND_SHOW_FORBIDDEN, new CommandShowForbidden());
        commands.put(COMMAND_DELETE_ORDER, new CommandDeleteOrder());
        commands.put(COMMAND_MAKE_ORDER, new CommandMakeOrder());
        commands.put(COMMAND_GET_ORDER, new CommandGetOrder());
        commands.put(COMMAND_EDIT_ORDER, new CommandEditOrder());
        commands.put(COMMAND_SHOW_PROFILE, new CommandShowProfile());
        commands.put(COMMAND_UPDATE_PROFILE, new CommandUpdateProfile());
        commands.put(COMMAND_CHANGE_LOCALE, new CommandChangeLocale());
        commands.put(COMMAND_CHANGE_ITEMSPERPAGE, new CommandChangeItemsPerPage());
    }

    public static CommandFactory getInstance() {
        return instance;
    }

    public Command getByName(String commandName) {
        return commands.get(commandName.toUpperCase());
    }

    public Command getCommand(RequestWrapper req) {
        String action = req.getParameter(ConstantClass.COMMAND);
        if (action == null) {
            logger.info("incorrect command");
            return getByName(COMMAND_SHOW_INDEX);
        }
        Command command = getByName(action);
        if (command != null) {
            return command;
        } else {
            logger.info("incorrect command: " + action);
            return getByName(COMMAND_SHOW_NOT_FOUND);
        }
    }
}






