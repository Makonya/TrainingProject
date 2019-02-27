package com.epam.training.action;

import com.epam.training.dao.LocaleDao;
import com.epam.training.entity.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.epam.training.util.AppConstant.*;

public class AddCategoryAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LocaleDao localeDao = new LocaleDao();
        List<Locale> locales = localeDao.findAll();
        request.setAttribute(ATT_LOCALES, locales);
        return new ActionResult(ADD_CATEGORY);
    }
}
