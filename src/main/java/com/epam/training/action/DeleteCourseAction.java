package com.epam.training.action;

import com.epam.training.dao.CourseUserDao;
import com.epam.training.entity.CourseUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.training.action.CourseUserService.getCurrentUserCourse;
import static com.epam.training.util.AppConstant.ATT_COURSE_ID;
import static com.epam.training.util.AppConstant.ATT_USER_ID;
import static com.epam.training.util.AppConstant.REFERER;

public class DeleteCourseAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        CourseUser courseUser = getCurrentUserCourse(request);
        CourseUserDao courseUserDao = new CourseUserDao();
        courseUserDao.delete(courseUser);
        return new ActionResult(request.getHeader(REFERER), true);
    }
}
