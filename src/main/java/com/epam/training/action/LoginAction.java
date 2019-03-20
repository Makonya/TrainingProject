package com.epam.training.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.training.dao.RoleDao;
import com.epam.training.dao.UserDao;
import com.epam.training.entity.User;
import com.epam.training.util.Encryption;
import org.apache.log4j.*;

import static com.epam.training.util.AppConstant.*;

public class LoginAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(LoginAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        UserDao userDao = new UserDao();
        RoleDao roleDao = new RoleDao();

        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        User user = userDao.findByLoginAndPassword(login, Encryption.toEncrypt(password));

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute(ATT_USER_ID, user.getId());
            session.setAttribute(ATT_ROLE_ID, user.getIdRole());
            session.setAttribute(ATT_ROLE, roleDao.findById(user.getIdRole()).getName());
            LOGGER.info("Customer by id = " + user.getId() + " and role = " + roleDao.findById(user.getIdRole()).getName() + " login in system");
            return new ActionResult(COURSES, true);
        } else {
            request.setAttribute(LOGIN_ERROR, true);
            LOGGER.warn("Can't find customer by login and password");
            return new ActionResult(AUTHORIZATION);
        }
    }
}