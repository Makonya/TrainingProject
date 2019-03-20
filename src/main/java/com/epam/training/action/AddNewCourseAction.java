package com.epam.training.action;

import com.epam.training.dao.CategoryDao;
import com.epam.training.dao.CourseDao;
import com.epam.training.entity.Category;
import com.epam.training.entity.Course;
import com.epam.training.util.LocaleUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.epam.training.util.AppConstant.*;
import static com.epam.training.util.Validation.checkParamValid;
import static com.epam.training.util.DateParser.parseDate;

public class AddNewCourseAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(EditCourseAction.class);
    private int localId;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        localId = LocaleUtil.getLocaleId(request);
        switch (request.getMethod()) {
            case METHOD_POST:
                int userId = (int) request.getSession().getAttribute(ATT_USER_ID);
                Course course = checkCourseParameters(request);
                if (course != null) {
                    CourseDao courseDao = new CourseDao();
                    course.setIdUser(userId);
                    if (courseDao.insert(course)) {
                        return new ActionResult(COURSES, true);
                    } else {
                        LOGGER.warn("New course wasn't inserted!");
                    }
                } else {
                    setCategories(request);
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

    private Course checkCourseParameters(HttpServletRequest request) {
        int correctness = 0;
        Course course = new Course();
        String startDate = request.getParameter(COURSE_START_DATE);
        if (!checkParamValid(COURSE_START_DATE_VAL_ERROR, startDate, DATE_VALIDATION, request)) {
            correctness++;
            request.setAttribute(INPUT_COURSE_DATE_START, startDate);
        } else {
            course.setStartDate(parseDate(startDate));
        }
        String endDate = request.getParameter(COURSE_END_DATE);
        if (!checkParamValid(COURSE_END_DATE_VAL_ERROR, endDate, DATE_VALIDATION, request)) {
            correctness++;
            request.setAttribute(INPUT_COURSE_DATE_END, endDate);
        } else {
            course.setEndDate(parseDate(endDate));
        }
        if (correctness == 0) {
            if (parseDate(endDate).before(parseDate(startDate))) {
                request.setAttribute(COURSE_START_END_DATE_VAL_ERROR, true);
                correctness++;
            }
        }
        course.setCourseName(request.getParameter(COURSE_NAME));
        course.setDescription(request.getParameter(DESCRIPTION));
        if (!checkParamValid(COURSE_NAME_VAL_ERROR, course.getCourseName(), COURSE_NAME_VALIDATION, request))
            correctness++;
        if (!checkParamValid(COURSE_DESCRIPTION_VAL_ERROR, course.getDescription(), DESCRIPTION_VALIDATION, request))
            correctness++;
        CategoryDao categoryDao = new CategoryDao();
        String category = request.getParameter(COURSE_CATEGORY);
        course.setIdCategory(categoryDao.findAllByName(category).getId());
        if (correctness == 0) {
            return course;
        } else {
            request.setAttribute(INPUT_COURSE_NAME, course.getCourseName());
            request.setAttribute(INPUT_COURSE_DESCRIPTION, course.getDescription());
            request.setAttribute(COURSE_CATEGORY, category);
        }
        return null;
    }
}