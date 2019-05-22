package com.epam.training.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.training.dao.RoleDao;
import com.epam.training.dao.UserDao;
import com.epam.training.entity.Role;
import com.epam.training.entity.User;
import com.epam.training.util.Encryption;
import org.apache.log4j.*;

import static com.epam.training.util.AppConstant.*;
import static com.epam.training.util.Validation.checkParamValid;

public class RegisterAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(RegisterAction.class);
    private String login;
    private String password;
    private String passwordRepeat;
    private String name;
    private String surname;
    private String email;
    private int idRole;
    private boolean correctness = true;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        RoleDao roleDao = new RoleDao();
        Role role = roleDao.findByName(STUDENT_ROLE);
        idRole = role.getId();
        UserDao userDao = new UserDao();
        setParameters(request);
        if (userDao.findByLogin(login)) {
            request.setAttribute(LOGIN_EXIST_ERROR, true);
            correctness = false;
        }
        if (!password.equals(passwordRepeat)) {
            request.setAttribute(PASSWORD2_VAL_ERROR, true);
            correctness = false;
        }
        paramValidation(request);
        if (!correctness) {
            correctness = true;
            LOGGER.warn("User validation failed!");
            setEditedProfileValues(request);
            return new ActionResult(REGISTER);
        } else {
            User user = fillNewUserParameters();
            userDao.insert(user);
            LOGGER.info("Added new user with id = " + user.getId());
            return new ActionResult(AUTHORIZATION);
        }
    }

    private void setParameters(HttpServletRequest request) {
        password = request.getParameter(PASSWORD);
        passwordRepeat = request.getParameter(PASSWORD_REPEAT);
        login = request.getParameter(LOGIN);
        name = request.getParameter(NAME);
        email = request.getParameter(EMAIL);
        surname = request.getParameter(SURNAME);

    }

    private void setEditedProfileValues(HttpServletRequest request) {
        request.setAttribute(INPUT_LOGIN, login);
        request.setAttribute(INPUT_NAME, name);
        request.setAttribute(INPUT_SURNAME, surname);
        request.setAttribute(INPUT_EMAIL, email);
    }

    private void paramValidation(HttpServletRequest req) {
        if (!checkParamValid(LOGIN_VAL_ERROR, login, LOGIN_VALIDATION, req)) correctness = false;
        if (!checkParamValid(PASSWORD_VAL_ERROR, password, PASSWORD_VALIDATION, req)) correctness = false;
        if (!checkParamValid(NAME_VAL_ERROR, name, NAME_VALIDATION, req)) correctness = false;
        if (!checkParamValid(SURNAME_VAL_ERROR, surname, SURNAME_VALIDATION, req)) correctness = false;
        if (!checkParamValid(EMAIL_VAL_ERROR, email, EMAIL_VALIDATION, req)) correctness = false;
    }

    private User fillNewUserParameters() {
        User user = new User();
        user.setLogin(login);
        user.setPassword(Encryption.toEncrypt(password));
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setIdRole(idRole);
        return user;
    }
}