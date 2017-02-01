package com.avseredyuk.carrental.web.command.impl.user;

import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.web.command.Command;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.HashingUtil;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.ResponseWrapper;

/**
 * Created by lenfer on 1/11/17.
 */
public class CommandEditUser implements Command {
    @Override
    public String execute(RequestWrapper req, ResponseWrapper resp) {
        if (!ServiceFactoryImplementation.getInstance().getAuthorizationService().checkRole(User.Role.ADMINISTRATOR, req.getSession())) {
            return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_SHOW_FORBIDDEN).execute(req, resp);
        }
        try {
            int userId = Integer.parseInt(req.getParameter(ConstantClass.USER_ID));
            String login = req.getParameter(ConstantClass.USERLOGIN);
            String email = req.getParameter(ConstantClass.USEREMAIL);
            String name = req.getParameter(ConstantClass.USERNAME);
            String surname = req.getParameter(ConstantClass.USERSURNAME);
            String password = req.getParameter(ConstantClass.USERPASSWORD);
            User.Role role = User.Role.valueOf(req.getParameter(ConstantClass.USERROLE));

            User user = ServiceFactoryImplementation.getInstance().getUserService().read(userId);
            user.setLogin(login);
            user.setEmail(email);
            user.setName(name);
            user.setSurname(surname);
            user.setRole(role);
            if (!password.isEmpty()) {
                user.setPassword(HashingUtil.hashPassword(password));
            }
            if (!ServiceFactoryImplementation.getInstance().getUserService().update(user)) {
                req.getSession().setAttribute(ConstantClass.ERROR_STATUS, "error.edit.user");
            }
        } catch(NumberFormatException e) {
            req.getSession().setAttribute(ConstantClass.ERROR_STATUS, "error.edit.user");
        }
        doReturnIfPossible(req, resp, true);
        return CommandFactory.getInstance().getByName(ConstantClass.COMMAND_GET_ALL_USERS).execute(req, resp);
    }
}
