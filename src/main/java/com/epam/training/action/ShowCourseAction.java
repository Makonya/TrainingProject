package com.epam.training.action;

import com.epam.training.dao.CourseDao;
import com.epam.training.entity.Course;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.training.util.AppConstant.*;

public class ShowCourseAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseId = request.getParameter(ATT_COURSE_ID);
        CourseDao courseDao = new CourseDao();
        Course course = courseDao.findById(Integer.parseInt(courseId));
        return new ActionResult(COURSE);
    }
}
