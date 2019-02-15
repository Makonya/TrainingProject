package com.epam.training.action;

import com.epam.training.dao.CourseDao;
import com.epam.training.dao.CourseUserDao;
import com.epam.training.dao.FeedbackDao;
import com.epam.training.dao.UserDao;
import com.epam.training.entity.Course;
import com.epam.training.entity.Feedback;
import com.epam.training.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static com.epam.training.util.AppConstant.*;

public class ShowCourseAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseId = request.getParameter(ATT_COURSE_ID);
        CourseDao courseDao = new CourseDao();
        Course course = courseDao.findById(Integer.parseInt(courseId));
        setCourseAttributes(request, course);
        Date courseEndDate = course.getEndDate();
        Date currentDate = new Date();
        int userId = (int) request.getSession().getAttribute(ATT_USER_ID);
        CourseUserDao courseUserDao = new CourseUserDao();

        if(courseEndDate.before(currentDate)){
            if(courseUserDao.findByUserId(userId)){
                request.setAttribute(ATT_COURSE_ADD_COMMENT, true);
            }
            if(userId==course.getIdUser()){
                request.setAttribute(ATT_COURSE_MARK , true);
            }
        } else {
            if(!courseUserDao.findByUserId(userId)){
                request.setAttribute(ATT_COURSE_ADD , true);
            }
            if(userId==course.getIdUser()){
                request.setAttribute(ATT_COURSE_EDIT , true);
            }
        }
        FeedbackDao feedbackDao = new FeedbackDao();
        List<Feedback> feedback = feedbackDao.findByCourseId(Integer.parseInt(courseId));
        request.setAttribute(ATT_COURSE_FEEDBACK, feedback);
        return new ActionResult(COURSE);
    }

    private void setCourseAttributes(HttpServletRequest request, Course course){
        request.setAttribute(ATT_COURSE_ID, course.getId());
        request.setAttribute(ATT_COURSE_NAME , course.getCourseName());
        request.setAttribute(ATT_COURSE_DATE_START , course.getStartDate());
        request.setAttribute(ATT_COURSE_DATE_END , course.getEndDate());
        request.setAttribute(ATT_COURSE_TEACHER , getTeacherName(course));
        request.setAttribute(ATT_COURSE_DESCRIPTION , course.getDescription());
    }

    private String getTeacherName(Course course){
        int teacherID = course.getIdUser();
        UserDao userDao = new UserDao();
        User user = userDao.findById(teacherID);
        return user.getName() + " " + user.getSurname();
    }
}
