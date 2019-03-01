package com.epam.training.action;

import com.epam.training.dao.UserDao;
import com.epam.training.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.training.util.AppConstant.*;

public class ShowProfileAction implements Action {

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        int userId = (int) request.getSession().getAttribute(ATT_USER_ID);
        UserDao userDao = new UserDao();
        User user = userDao.findById(userId);
        setProfileValues(request, user);
        return new ActionResult(PROFILE);
    }

    //TODO delete duplicate code
    private void setProfileValues(HttpServletRequest request, User user) {
        request.setAttribute(INPUT_LOGIN, user.getLogin());
        request.setAttribute(INPUT_NAME, user.getName());
        request.setAttribute(INPUT_SURNAME, user.getSurname());
        request.setAttribute(INPUT_EMAIL, user.getEmail());
    }
}
