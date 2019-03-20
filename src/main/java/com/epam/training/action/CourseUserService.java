package com.epam.training.action;

import com.epam.training.entity.CourseUser;

import javax.servlet.http.HttpServletRequest;

import static com.epam.training.util.AppConstant.ATT_COURSE_ID;
import static com.epam.training.util.AppConstant.ATT_USER_ID;

class CourseUserService {

    static CourseUser getCurrentUserCourse(HttpServletRequest request) {
        String courseId = request.getParameter(ATT_COURSE_ID);
        int userId = (int) request.getSession().getAttribute(ATT_USER_ID);
        CourseUser courseUser = new CourseUser();
        courseUser.setIdUser(userId);
        courseUser.setIdCourse(Integer.parseInt(courseId));
        return courseUser;
    }
}