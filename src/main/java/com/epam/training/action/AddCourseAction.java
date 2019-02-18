package com.epam.training.action;

import com.epam.training.dao.CourseUserDao;
import com.epam.training.entity.CourseUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.training.util.AppConstant.ATT_COURSE_ID;
import static com.epam.training.util.AppConstant.ATT_USER_ID;
import static com.epam.training.util.AppConstant.REFERER;

public class AddCourseAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseId = request.getParameter(ATT_COURSE_ID);
        int userId = (int) request.getSession().getAttribute(ATT_USER_ID);
        CourseUser courseUser = new CourseUser();
        courseUser.setIdUser(userId);
        courseUser.setIdCourse(Integer.parseInt(courseId));
        CourseUserDao courseUserDao = new CourseUserDao();
        courseUserDao.insert(courseUser);
        return new ActionResult(request.getHeader(REFERER), true);
    }
}
