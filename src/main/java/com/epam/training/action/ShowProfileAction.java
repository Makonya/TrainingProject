package com.epam.training.action;

import com.epam.training.dao.UserDao;
import com.epam.training.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.training.util.AppConstant.*;

public class ShowProfileAction implements Action {
    private User user;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = (int) request.getSession().getAttribute(ATT_USER_ID);
        UserDao userDao = new UserDao();
        user = userDao.findById(userId);
        setProfileValues(request, user);
        return new ActionResult(PROFILE);
    }

    void setProfileValues(HttpServletRequest request, User user){
        request.setAttribute(INPUT_LOGIN, user.getLogin());
        request.setAttribute(INPUT_NAME, user.getName());
        request.setAttribute(INPUT_SURNAME, user.getSurname());
        request.setAttribute(INPUT_EMAIL, user.getEmail());
    }
}
