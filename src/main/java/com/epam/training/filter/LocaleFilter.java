package com.epam.training.filter;

import com.epam.training.util.CookieGetter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;

import static com.epam.training.util.AppConstant.*;

public class LocaleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            String language = CookieGetter.getCookieValue(req, LANG);
            if (language != null) {
                Locale locale = new Locale(language);
                Config.set(req.getSession(), Config.FMT_LOCALE, locale);
            } else {
                Locale currentLocale = Locale.getDefault();
                Config.set(req.getSession(), Config.FMT_LOCALE, currentLocale);
                Cookie cookie = new Cookie(LANG, currentLocale.getLanguage());
                cookie.setMaxAge(HOUR * MINUTE * SEC);
                resp.addCookie(cookie);
            }
        }

        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }
}
