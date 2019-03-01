package com.epam.training.action;

import com.epam.training.dao.CourseDao;
import com.epam.training.dao.CourseUserDao;
import com.epam.training.entity.Course;
import com.epam.training.entity.CourseUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.epam.training.util.AppConstant.*;

public class ShowMyCoursesAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        int userId = (int) request.getSession().getAttribute(ATT_USER_ID);
        String userRole = (String) request.getSession().getAttribute(ATT_ROLE);
        switch (userRole) {
            case STUDENT_ROLE:
                CourseUserDao courseUserDao = new CourseUserDao();
                List<CourseUser> courseUsers = courseUserDao.findByUserId(userId);
                request.setAttribute(ATT_MY_COURSES, courseUsers);
                break;
            case TEACHER_ROLE:
                CourseDao courseDao = new CourseDao();
                List<Course> userCourses = courseDao.findByUserId(userId);
                request.setAttribute(ATT_MY_COURSES, userCourses);
                break;
        }
        return new ActionResult(MY_COURSES);
    }
}
