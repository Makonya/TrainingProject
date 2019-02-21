package com.epam.training.action;

import com.epam.training.dao.CourseDao;
import com.epam.training.entity.Course;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static com.epam.training.util.AppConstant.*;
import static com.epam.training.util.Validation.checkParamValid;

public class EditCourseAction implements Action {
    private static Logger logger = Logger.getLogger(EditCourseAction.class);
    private int courseId;
    private String courseName;
    private String courseDescription;
    private String startDate;
    private String endDate;
    private int correctness = 0;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        courseId = Integer.parseInt(request.getParameter(ATT_COURSE_ID));
        CourseDao courseDao = new CourseDao();

        switch (request.getMethod()){
            case METHOD_POST:
                getParameters(request);
                parametersValidation(request);
                if(correctness == 0){
                    Course course = fillValidatedParameters();
                    if(courseDao.update(course)){
                        request.setAttribute(COURSE_EDIT_SUCCESS, true);
                        setAttributes(request, course);
                    } else {
                        logger.warn("Course data wasn't updated!");
                    }
                } else{
                    correctness = 0;
                    setEditedAttributes(request);
                }
                break;
            case METHOD_GET:
                Course course = courseDao.findById(courseId);
                setAttributes(request, course);
                break;
        }
        return new ActionResult(EDIT_COURSE);
    }

    private void setAttributes(HttpServletRequest request, Course course){
        request.setAttribute(INPUT_COURSE_NAME, course.getCourseName());
        request.setAttribute(INPUT_COURSE_DESCRIPTION, course.getDescription());
        request.setAttribute(INPUT_COURSE_DATE_START, course.getStartDate());
        request.setAttribute(INPUT_COURSE_DATE_END, course.getEndDate());
        request.setAttribute(ATT_COURSE_ID, course.getId());
    }

    private void setEditedAttributes(HttpServletRequest request){
        request.setAttribute(INPUT_COURSE_NAME, courseName);
        request.setAttribute(INPUT_COURSE_DESCRIPTION, courseDescription);
        request.setAttribute(INPUT_COURSE_DATE_START, startDate);
        request.setAttribute(INPUT_COURSE_DATE_END, endDate);
        request.setAttribute(ATT_COURSE_ID, courseId);
    }

    private void getParameters(HttpServletRequest request){
        courseName = request.getParameter(COURSE_NAME);
        courseDescription = request.getParameter(DESCRIPTION);
        startDate = request.getParameter(COURSE_START_DATE);
        endDate = request.getParameter(COURSE_END_DATE);
    }

    private Date parseDate(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = null;
        try {
            parsed = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new java.sql.Date(parsed.getTime());
    }

    private void parametersValidation(HttpServletRequest request){
        if(!checkParamValid(COURSE_NAME_VAL_ERROR, courseName, COURSE_NAME_VALIDATION, request)) correctness++;
        if(!checkParamValid(COURSE_DESCRIPTION_VAL_ERROR, courseDescription, DESCRIPTION_VALIDATION, request)) correctness++;
        if(!checkParamValid(COURSE_START_DATE_VAL_ERROR, startDate, DATE_VALIDATION, request)) correctness++;
        if(!checkParamValid(COURSE_END_DATE_VAL_ERROR, endDate, DATE_VALIDATION, request)) correctness++;
        if(correctness == 0){
            if(parseDate(endDate).before(parseDate(startDate))){
                request.setAttribute(COURSE_START_END_DATE_VAL_ERROR, true);
                correctness++;
            }
        }
    }

    private Course fillValidatedParameters(){
        Course course = new Course();
        course.setId(courseId);
        course.setCourseName(courseName);
        course.setDescription(courseDescription);
        course.setStartDate(parseDate(startDate));
        course.setEndDate(parseDate(endDate));
        return course;
    }
}
