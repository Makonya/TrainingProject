package com.epam.training.action;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import org.apache.log4j.*;

import static com.epam.training.util.AppConstant.*;
public class ChangeLanguageAction implements Action {
    private static Logger logger = Logger.getLogger(ChangeLanguageAction.class);
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = request.getParameter(LANG);
        System.out.println(language);
        Config.set(request.getSession(), Config.FMT_LOCALE, new Locale(language));
        Cookie cookie = new Cookie(LANG, language);
        cookie.setMaxAge(HOUR * MINUTE * SEC);
        response.addCookie(cookie);
        try {
            request.setCharacterEncoding(CHARACTER_ENCODING);
        } catch (UnsupportedEncodingException e) {
            logger.error("Can't set character encoding" ,e);
        }
        return new ActionResult(request.getHeader(REFERER), true);
    }
}
