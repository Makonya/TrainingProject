package com.epam.training.util;

import com.epam.training.dao.LocaleDao;
import com.epam.training.entity.Locale;

import javax.servlet.http.HttpServletRequest;

import static com.epam.training.util.AppConstant.DEFAULT_LANGUAGE;
import static com.epam.training.util.AppConstant.LANG;

public class LocaleUtil {
    public static int getLocaleId(HttpServletRequest request) {
        String language = CookieGetter.getCookieValue(request, LANG);
        if (language == null) {
            language = DEFAULT_LANGUAGE;
        }
        LocaleDao localeDao = new LocaleDao();
        Locale locale = localeDao.findByLocaleName(language);
        return locale.getId();
    }
}
