package com.epam.training.action;

import com.epam.training.dao.CategoryDao;
import com.epam.training.dao.CourseDao;
import com.epam.training.entity.Category;
import com.epam.training.entity.Course;
import com.epam.training.util.LocaleUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.epam.training.util.AppConstant.*;
import static com.epam.training.util.Validation.checkParamValid;

public class AddNewCourse implements Action {
    private static final Logger LOGGER = Logger.getLogger(EditCourseAction.class);
    private int userId;
    private String category;
    private String courseName;
    private String courseDescription;
    private String startDate;
    private String endDate;
    private int correctness = 0;
    private int localId;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        localId = LocaleUtil.getLocaleId(request);
        switch (request.getMethod()) {
            case METHOD_POST:
                userId = (int) request.getSession().getAttribute(ATT_USER_ID);
                CourseDao courseDao = new CourseDao();
                getParameters(request);
                parametersValidation(request);
                if (correctness == 0) {
                    Course course = fillValidatedParameters();
                    if (courseDao.insert(course)) {
                        return new ActionResult(COURSES, true);
                    } else {
                        LOGGER.warn("New course wasn't inserted!");
                    }
                } else {
                    correctness = 0;
                    setCategories(request);
                    setEditedAttributes(request);
                }
                break;
            case METHOD_GET:
                setCategories(request);
        }
        return new ActionResult(ADD_COURSE);
    }

    private void setCategories(HttpServletRequest request) {
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categories = categoryDao.findAllByIdLocale(localId);
        request.setAttribute(ATT_CATEGORIES, categories);
    }

    private void setEditedAttributes(HttpServletRequest request) {
        request.setAttribute(INPUT_COURSE_NAME, courseName);
        request.setAttribute(INPUT_COURSE_DESCRIPTION, courseDescription);
        request.setAttribute(INPUT_COURSE_DATE_START, startDate);
        request.setAttribute(INPUT_COURSE_DATE_END, endDate);
        request.setAttribute(COURSE_CATEGORY, category);
    }

    private void getParameters(HttpServletRequest request) {
        courseName = request.getParameter(COURSE_NAME);
        courseDescription = request.getParameter(DESCRIPTION);
        startDate = request.getParameter(COURSE_START_DATE);
        endDate = request.getParameter(COURSE_END_DATE);
        category = request.getParameter(COURSE_CATEGORY);
    }

    private Date parseDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = null;
        try {
            parsed = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new java.sql.Date(parsed != null ? parsed.getTime() : 0);
    }

    private void parametersValidation(HttpServletRequest request) {
        if (!checkParamValid(COURSE_NAME_VAL_ERROR, courseName, COURSE_NAME_VALIDATION, request)) correctness++;
        if (!checkParamValid(COURSE_DESCRIPTION_VAL_ERROR, courseDescription, DESCRIPTION_VALIDATION, request))
            correctness++;
        if (!checkParamValid(COURSE_START_DATE_VAL_ERROR, startDate, DATE_VALIDATION, request)) correctness++;
        if (!checkParamValid(COURSE_END_DATE_VAL_ERROR, endDate, DATE_VALIDATION, request)) correctness++;
        if (correctness == 0) {
            if (parseDate(endDate).before(parseDate(startDate))) {
                request.setAttribute(COURSE_START_END_DATE_VAL_ERROR, true);
                correctness++;
            }
        }
    }

    private Course fillValidatedParameters() {
        Course course = new Course();
        course.setIdUser(userId);
        course.setCourseName(courseName);
        course.setDescription(courseDescription);
        course.setStartDate(parseDate(startDate));
        course.setEndDate(parseDate(endDate));
        CategoryDao categoryDao = new CategoryDao();
        course.setIdCategory(categoryDao.findAllByName(category).getId());
        return course;
    }
}
