package com.epam.training.action;

import com.epam.training.dao.UserDao;
import com.epam.training.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.training.util.AppConstant.*;
import static com.epam.training.action.UserDataService.setProfileValues;

public class ShowProfileAction implements Action {

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        int userId = (int) request.getSession().getAttribute(ATT_USER_ID);
        UserDao userDao = new UserDao();
        User user = userDao.findById(userId);
        setProfileValues(request, user);
        return new ActionResult(PROFILE);
    }
}