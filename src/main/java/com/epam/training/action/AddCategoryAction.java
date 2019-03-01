package com.epam.training.action;

import com.epam.training.dao.CategoryDao;
import com.epam.training.dao.LocaleDao;
import com.epam.training.entity.Category;
import com.epam.training.entity.Locale;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

import static com.epam.training.util.AppConstant.*;
import static com.epam.training.util.Validation.checkParamValid;

public class AddCategoryAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(AddCategoryAction.class);
    private int correctness = 0;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        LocaleDao localeDao = new LocaleDao();
        List<Locale> locales = localeDao.findAll();
        request.setAttribute(ATT_LOCALES, locales);
        if (request.getMethod().equals(METHOD_POST)) {
            CategoryDao categoryDao = new CategoryDao();
            List<Category> categories = new LinkedList<>();
            int categoryId = categoryDao.getLastId() + 1;
            for (int i = 1; i <= locales.size(); i++) {
                Category category = new Category();
                category.setCategoryName(request.getParameter(CATEGORY_NAME + i));
                category.setIdLocale(locales.get(i - 1).getId());
                category.setId(categoryId);
                categories.add(category);
            }
            for (Category category : categories) {
                if (!checkParamValid(CATEGORY_VAL_ERROR, category.getCategoryName(), CATEGORY_NAME_VALIDATION, request))
                    correctness++;
                if (categoryDao.findAllByName(category.getCategoryName()) != null) {
                    request.setAttribute(CATEGORY_EXIST_ERROR, true);
                    correctness++;
                }
            }
            if (correctness == 0) {
                if (categoryDao.insert(categories)) {
                    request.setAttribute(CATEGORY_ADD_SUCCESS, true);
                } else {
                    LOGGER.warn("Unknown error occurred while inserting new category!");
                }
            } else {
                correctness = 0;
            }
        }
        return new ActionResult(ADD_CATEGORY);
    }
}
