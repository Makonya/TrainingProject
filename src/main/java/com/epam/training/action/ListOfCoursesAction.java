package com.epam.training.action;

import com.epam.training.dao.CategoryDao;
import com.epam.training.dao.CourseDao;
import com.epam.training.entity.Category;
import com.epam.training.entity.Course;
import com.epam.training.util.CookieGetter;
import com.epam.training.util.LocaleUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.epam.training.util.AppConstant.*;

public class ListOfCoursesAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        int localId = LocaleUtil.getLocaleId(request);
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categories = categoryDao.findAllByIdLocale(localId);
        request.setAttribute(ATT_CATEGORIES, categories);

        CourseDao courseDao = new CourseDao();
        List<Course> courses;
        String categoryId = CookieGetter.getCookieValue(request, ATT_CATEGORY_ID);
        if (categoryId != null && Integer.parseInt(categoryId) != 0) {
            courses = courseDao.findByCategoryId(Integer.parseInt(categoryId));
        } else {
            courses = courseDao.findAll();
        }
        request.setAttribute(ATT_COURSES, courses);
        return new ActionResult(LIST_OF_COURSES);
    }
}
