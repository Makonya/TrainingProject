package com.epam.training.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static com.epam.training.util.AppConstant.*;

public class AccessFilter implements Filter {
    private static Logger logger = Logger.getLogger(AccessFilter.class);

    private String admin = "admin";
    private String teacher = "teacher";
    private String student = "student";

    private final Set<String> guestAccess = new HashSet<>();
    private final Set<String> teacherAccess = new HashSet<>();
    private final Set<String> studentAccess = new HashSet<>();
    private final Set<String> adminAccess = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Initializing access roles");
        initGuest();
        initTeacher();
        initStudent();
        initAdmin();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String path = req.getPathInfo();
        if (req.getSession().getAttribute(ATT_ROLE) == null) {
            if (!guestAccess.contains(path)) {
                logger.warn("Can't get permission(guest) for path " + path);
                resp.sendRedirect(WELCOME);
                return;
            }
        } else if (req.getSession().getAttribute(ATT_ROLE).equals(teacher)) {
            if (!teacherAccess.contains(path)) {
                logger.warn("Can't get permission(user) for path " + path);
                resp.sendRedirect(COURSES);
                return;
            }
        } else if (req.getSession().getAttribute(ATT_ROLE).equals(student)) {
            if (!studentAccess.contains(path)) {
                logger.warn("Can't get permission(student) for path " + path);
                resp.sendRedirect(COURSES);
                return;
            }
        } else if (req.getSession().getAttribute(ATT_ROLE).equals(admin)) {
            req.getSession().getAttribute(ATT_USER_ID);
            if (!adminAccess.contains(path)) {
                logger.warn("Can't get permission(admin) for path " + path);
                resp.sendRedirect(COURSES);
                return;
            }
        } else {
            filterChain.doFilter(req, resp);
            return;
        }
        filterChain.doFilter(req, resp);
    }

    private void initGuest() {
        guestAccess.add("/welcome");
        guestAccess.add("/registration");
        guestAccess.add("/authorization");
        guestAccess.add("/set-language");
    }

    private void initTeacher() {
        teacherAccess.add("/listOfCourses");
        teacherAccess.add("/set-language");
    }

    private void initStudent() {
        studentAccess.add("/listOfCourses");
        studentAccess.add("/set-language");
    }

    private void initAdmin() {
        adminAccess.add("/listOfCourses");
        adminAccess.add("/set-language");
    }

    @Override
    public void destroy() {
        admin = null;
        teacher = null;
        student = null;
        adminAccess.removeAll(adminAccess);
        teacherAccess.removeAll(teacherAccess);
        studentAccess.removeAll(studentAccess);
        guestAccess.removeAll(guestAccess);
    }
}
