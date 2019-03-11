package com.epam.training.action;

import com.epam.training.entity.User;

import javax.servlet.http.HttpServletRequest;

import static com.epam.training.util.AppConstant.*;
import static com.epam.training.util.AppConstant.INPUT_EMAIL;

class UserDataService {

    static void setProfileValues(HttpServletRequest request, User user) {
        request.setAttribute(INPUT_LOGIN, user.getLogin());
        request.setAttribute(INPUT_NAME, user.getName());
        request.setAttribute(INPUT_SURNAME, user.getSurname());
        request.setAttribute(INPUT_EMAIL, user.getEmail());
    }
}
