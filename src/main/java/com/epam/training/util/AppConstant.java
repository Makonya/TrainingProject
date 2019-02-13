package com.epam.training.util;

public class AppConstant {
    public static final String DIRECTORY = "C://Users//User//IdeaProjects//TrainingProject//";
    public static final String RESOURCES_DIRECTORY = "src//main//resources//";

    //Name of pages for redirect
    public static final String COURSES =             "listOfCourses";
    public static final String WELCOME =             "welcome";
    public static final String AUTHORIZATION =             "authorization";
    public static final String REGISTER =             "registration";
    public static final String LIST_OF_COUSES =             "listOfCourses";
    //View
    public static final String PATH_TO_JSP = "/WEB-INF/jsp/";
    public static final String JSP_FORMAT = ".jsp";

    //SelectLanguageAction
    public static final String LANG =               "lang";
    public static final String REFERER =            "referer";
    public static final String CHARACTER_ENCODING = "UTF-8";
    public static final int HOUR = 24;
    public static final int MINUTE = 60;
    public static final int SEC = 60;

    //login
    public static final String LOGIN =                "login";
    public static final String PASSWORD =             "password";
    public static final String LOGIN_ERROR = "login_error";

    //register
    public static final String NAME = "name";
    public static final String PASSWORD_REPEAT = "password2";
    public static final String SURNAME = "surname";
    public static final String EMAIL = "email";
    public static final String LOGIN_VAL_ERROR = "login_val_error";
    public static final String LOGIN_EXIST_ERROR = "login_exist_error";
    public static final String PASSWORD_VAL_ERROR = "password_val_error";
    public static final String PASSWORD2_VAL_ERROR = "password2_val_error";
    public static final String NAME_VAL_ERROR = "name_val_error";
    public static final String SURNAME_VAL_ERROR = "surname_val_error";
    public static final String EMAIL_VAL_ERROR = "email_val_error";

    //listOfCourses
    public static final String ATT_CATEGORIES = "categories";
    public static final String ATT_COURSES = "courses";
    public static final String ATT_CATEGORY_ID = "categoryID";

    //Session
    public static final String ATT_USER_ID = "userId";
    public static final String ATT_ROLE =        "role";
    public static final String ATT_ROLE_ID =     "role_id";

    //
    public static final String STUDENT_ROLE = "student";

    //validation
    public static final String PASSWORD_VALIDATION = ".{6,}";
    public static final String LOGIN_VALIDATION = ".{4,}";
    public static final String NAME_VALIDATION = "\\p{L}{1,45}";
    public static final String SURNAME_VALIDATION = "\\p{L}{1,45}";
    public static final String EMAIL_VALIDATION = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";
    public static final String DESCRIPTION_VALIDATION = "^.{10,1000}$";
    public static final String COURSE_NAME_VALIDATION = "^.{2,140}$";
    public static final String DATE_VALIDATION = "\\d{4}-[01]\\d-[0-3]\\d";
}
