package com.epam.training.action;

import com.epam.training.dao.CourseDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.training.util.AppConstant.*;

public class DeleteCourseAction implements Action {

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        int courseId = Integer.parseInt(request.getParameter(ATT_COURSE_ID));
        CourseDao courseDao = new CourseDao();
        if (courseDao.delete(courseId)) {
            request.setAttribute(DELETE_COURSE_SUCCESS, true);
        } else {
            request.setAttribute(DELETE_COURSE_ERROR, true);
        }
        return new ActionResult(request.getHeader(REFERER), true);
    }
}
