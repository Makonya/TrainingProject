package com.epam.training.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.epam.training.dao.RoleDao;
import com.epam.training.dao.UserDao;
import com.epam.training.entity.Role;
import org.apache.log4j.*;

import static com.epam.training.util.AppConstant.*;

public class RegisterAction implements Action {
    private static Logger logger = Logger.getLogger(RegisterAction.class);
    private String login;
    private String password;
    private String passwordRepeat;
    private String name;
    private String surname;
    private String email;
    private int idRole;


    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RoleDao roleDao = new RoleDao();
        Role role = roleDao.findByName(STUDENT_ROLE);
        boolean correctness = false;
        UserDao userDao = new UserDao();
        setParameters(request);
        if(userDao.findByLogin(login)){
         request.setAttribute();
        }
        return null;
        /*        UserDao userDao = new UserDao();
        RoleDao roleDao = new RoleDao();

        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        User user = userDao.findByLoginAndPassword(login, Encryption.toEncrypt(password));

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute(ATT_USER_ID, user.getId());
            session.setAttribute(ATT_ROLE_ID, user.getIdRole());
            session.setAttribute(ATT_ROLE, roleDao.findById(user.getIdRole()).getName());
            logger.info("Customer by id = " + user.getId() + " and role = " + roleDao.findById(user.getIdRole()).getName() + " login in system");
            return new ActionResult(COURSES, true);
        } else {
            request.setAttribute(LOGIN_ERROR, true);
            logger.warn("Can't find customer by login and password");
            return new ActionResult(AUTHORIZATION);
        }*/
    }

    private void setParameters(HttpServletRequest request){
        login = request.getParameter(LOGIN);
        password = request.getParameter(PASSWORD);
        passwordRepeat = request.getParameter(PASSWORD_REPEAT);
        name = request.getParameter(NAME);
        email = request.getParameter(SURNAME);
        email = request.getParameter(EMAIL);
    }
}
