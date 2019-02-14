package com.epam.training.action;

import com.epam.training.dao.CategoryDao;
import com.epam.training.dao.CourseDao;
import com.epam.training.dao.LocaleDao;
import com.epam.training.entity.Category;
import com.epam.training.entity.Course;
import com.epam.training.entity.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.epam.training.util.AppConstant.*;

public class ListOfCoursesAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int localId = setLocale(request);
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categories = categoryDao.findAllByIdLocale(localId);
        request.setAttribute(ATT_CATEGORIES, categories);

        CourseDao courseDao = new CourseDao();
        List<Course> courses;
        String categoryId = getCookieValue(request, ATT_CATEGORY_ID);
        if(categoryId != null && Integer.parseInt(categoryId) != 0){
            courses = courseDao.findByCategoryId(Integer.parseInt(categoryId));
        } else {
            courses = courseDao.findAll();
        }
        request.setAttribute(ATT_COURSES, courses);

        return new ActionResult(LIST_OF_COURSES);
    }

    private int setLocale(HttpServletRequest request){
        String language = getCookieValue(request, LANG);
        if(language == null){
            language = "ru";
        }
        LocaleDao localeDao = new LocaleDao();
        Locale locale = localeDao.findByLocaleName(language);
        return locale.getId();
    }

    private String getCookieValue(HttpServletRequest req, String cookieName) {
        return Arrays.stream(req.getCookies())
                .filter(c -> c.getName().equals(cookieName))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);
    }
}
