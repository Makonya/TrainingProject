package com.epam.training.action;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

public class ChangeLanguageAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = req.getParameter(LANG);
        Config.set(req.getSession(), Config.FMT_LOCALE, new Locale(language));
        Cookie cookie = new Cookie(LANG, language);
        cookie.setMaxAge(HOUR * MINUTE * SEC);
        resp.addCookie(cookie);
        try {
            req.setCharacterEncoding(CHARACTER_ENCODING);
        } catch (UnsupportedEncodingException e) {
            log.error("Can't set character encoding" ,e);
        }
        return new ActionResult(req.getHeader(REFERER), true);
    }
}
