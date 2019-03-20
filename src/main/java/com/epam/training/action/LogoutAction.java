package com.epam.training.action;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.epam.training.util.AppConstant.*;

public class LogoutAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(LogoutAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute(ATT_USER_ID);
        String roleName = (String) session.getAttribute(ATT_ROLE);
        LOGGER.info("User with id = " + id + " and role = " + roleName + " logout");
        session.invalidate();
        return new ActionResult(AUTHORIZATION, true);
    }
}