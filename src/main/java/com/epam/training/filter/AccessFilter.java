package com.epam.training.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import static com.epam.training.util.AppConstant.*;

public class AccessFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(AccessFilter.class);

    private String admin = "admin";
    private String teacher = "teacher";
    private String student = "student";

    private final Set<String> guestAccess = new HashSet<>();
    private final Set<String> commonAccess = new HashSet<>();
    private final Set<String> teacherAccess = new HashSet<>();
    private final Set<String> studentAccess = new HashSet<>();
    private final Set<String> adminAccess = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) {
        LOGGER.info("Initializing access roles");
        initGuest();
        initCommonAccess();
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
                LOGGER.warn("Can't get permission(guest) for path " + path);
                resp.sendRedirect(WELCOME);
                return;
            }
        } else if (req.getSession().getAttribute(ATT_ROLE).equals(teacher)) {
            if (!teacherAccess.contains(path)) {
                LOGGER.warn("Can't get permission(user) for path " + path);
                resp.sendRedirect(COURSES);
                return;
            }
        } else if (req.getSession().getAttribute(ATT_ROLE).equals(student)) {
            if (!studentAccess.contains(path)) {
                LOGGER.warn("Can't get permission(student) for path " + path);
                resp.sendRedirect(COURSES);
                return;
            }
        } else if (req.getSession().getAttribute(ATT_ROLE).equals(admin)) {
            req.getSession().getAttribute(ATT_USER_ID);
            if (!adminAccess.contains(path)) {
                LOGGER.warn("Can't get permission(admin) for path " + path);
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
        teacherAccess.addAll(commonAccess);
        teacherAccess.add("/editCourse");
        teacherAccess.add("/addMarks");
        teacherAccess.add("/myCourses");
        teacherAccess.add("/addNewCourse");
    }

    private void initStudent() {
        studentAccess.addAll(commonAccess);
        studentAccess.add("/addCourse");
        studentAccess.add("/addComment");
        studentAccess.add("/deleteCourse");
        studentAccess.add("/myCourses");
    }

    private void initAdmin() {
        adminAccess.addAll(commonAccess);
        adminAccess.add("/addCategory");
    }

    private void initCommonAccess() {
        commonAccess.add("/listOfCourses");
        commonAccess.add("/set-language");
        commonAccess.add("/changeCategory");
        commonAccess.add("/logout");
        commonAccess.add("/profile");
        commonAccess.add("/course");
    }

    @Override
    public void destroy() {
        admin = null;
        teacher = null;
        student = null;
        adminAccess.clear();
        teacherAccess.clear();
        studentAccess.clear();
        guestAccess.clear();
    }
}
