package com.epam.training.action;

import com.epam.training.dao.CourseDao;
import com.epam.training.dao.CourseUserDao;
import com.epam.training.entity.Course;
import com.epam.training.entity.CourseUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.epam.training.util.AppConstant.ATT_COURSES;
import static com.epam.training.util.AppConstant.DELETE_COURSE;

public class ShowDeleteCoursePage implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        CourseDao courseDao = new CourseDao();
        CourseUserDao courseUserDao = new CourseUserDao();
        List<CourseUser> courseUserList = courseUserDao.findAll();
        if(!courseUserList.isEmpty()){
            Set<Integer> activeCoursesIds = new HashSet<>();
            for (CourseUser courseUser : courseUserList) {
                activeCoursesIds.add(courseUser.getIdCourse());
            }
            List<Course> allCourses = courseDao.findAll();
            Set<Integer> allCoursesIds = new HashSet<>();
            for (Course course : allCourses) {
                allCoursesIds.add(course.getId());
            }
            allCoursesIds.removeAll(activeCoursesIds);
            List<Course> emptyCourses = courseDao.findByIds(allCoursesIds);
            request.setAttribute(ATT_COURSES, emptyCourses);
        }
        return new ActionResult(DELETE_COURSE);
    }
}
