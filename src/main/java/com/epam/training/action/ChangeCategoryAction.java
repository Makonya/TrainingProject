package com.epam.training.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.training.util.AppConstant.*;

public class ChangeCategoryAction implements Action {

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        String categoryId = request.getParameter(ATT_CATEGORY_ID);
        Cookie cookie = new Cookie(ATT_CATEGORY_ID, categoryId);
        cookie.setMaxAge(HOUR * MINUTE * SEC);
        response.addCookie(cookie);
        return new ActionResult(request.getHeader(REFERER), true);
    }
}