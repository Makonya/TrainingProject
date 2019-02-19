package com.epam.training.action;

import com.epam.training.dao.CourseUserDao;
import com.epam.training.entity.CourseUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.epam.training.util.AppConstant.*;

public class ShowMyCoursesAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = (int) request.getSession().getAttribute(ATT_USER_ID);
        CourseUserDao courseUserDao = new CourseUserDao();
        List<CourseUser> courseUsers = courseUserDao.findByUserId(userId);
        request.setAttribute(ATT_MY_COURSES, courseUsers);
        return new ActionResult(MY_COURSES);
    }
}
