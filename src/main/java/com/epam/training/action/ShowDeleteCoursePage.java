package com.epam.training.action;

import com.epam.training.dao.CourseDao;
import com.epam.training.entity.Course;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static com.epam.training.util.AppConstant.ATT_COURSES;
import static com.epam.training.util.AppConstant.DELETE_COURSE;

public class ShowDeleteCoursePage implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        CourseDao courseDao = new CourseDao();
        //TODO replace findAll() method with a correct
        List<Course> courses = courseDao.findAll();
        request.setAttribute(ATT_COURSES, courses);
        return new ActionResult(DELETE_COURSE);
    }
}
