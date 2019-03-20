package com.epam.training.util;

public class AppConstant {
    //Directories
    public static final String DIRECTORY = "C://Users//User//IdeaProjects//TrainingProject//";
    public static final String RESOURCES_DIRECTORY = "src//main//resources//";

    //Name of pages for redirect
    public static final String COURSES = "listOfCourses";
    public static final String WELCOME = "welcome";
    public static final String AUTHORIZATION = "authorization";
    public static final String REGISTER = "registration";
    public static final String LIST_OF_COURSES = "listOfCourses";
    public static final String PROFILE = "profile";
    public static final String COURSE = "course";
    public static final String MY_COURSES = "myCourses";
    public static final String EDIT_COURSE = "editCourse";
    public static final String ADD_MARKS = "addMarks";
    public static final String ADD_COURSE = "addCourse";
    public static final String ADD_CATEGORY = "addCategory";
    public static final String DELETE_COURSE = "deleteCourse";

    //Locale
    public static final String DEFAULT_LANGUAGE = "ru";

    //Request method
    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";

    //View
    public static final String PATH_TO_JSP = "/WEB-INF/jsp/";
    public static final String JSP_FORMAT = ".jsp";

    //Select Language Action
    public static final String LANG = "lang";
    public static final String REFERER = "referer";
    public static final String CHARACTER_ENCODING = "UTF-8";
    public static final int HOUR = 24;
    public static final int MINUTE = 60;
    public static final int SEC = 60;

    //Login
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String LOGIN_ERROR = "login_error";

    //Register
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

    //List of courses
    public static final String ATT_CATEGORIES = "categories";
    public static final String ATT_COURSES = "courses";
    public static final String ATT_CATEGORY_ID = "categoryID";

    //Session
    public static final String ATT_USER_ID = "userId";
    public static final String ATT_ROLE = "role";
    public static final String ATT_ROLE_ID = "role_id";

    //Roles
    public static final String STUDENT_ROLE = "student";
    public static final String TEACHER_ROLE = "teacher";

    //Validation
    public static final String PASSWORD_VALIDATION = ".{6,}";
    public static final String LOGIN_VALIDATION = ".{4,}";
    public static final String NAME_VALIDATION = "\\p{L}{1,45}";
    public static final String SURNAME_VALIDATION = "\\p{L}{1,45}";
    public static final String EMAIL_VALIDATION = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";
    public static final String DESCRIPTION_VALIDATION = "^.{10,1000}$";
    public static final String COURSE_NAME_VALIDATION = "^.{2,140}$";
    public static final String CATEGORY_NAME_VALIDATION = "^.{2,45}$";
    public static final String DATE_VALIDATION = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";

    //Profile
    public static final String INPUT_LOGIN = "loginInput";
    public static final String INPUT_NAME = "nameInput";
    public static final String INPUT_SURNAME = "surnameInput";
    public static final String INPUT_EMAIL = "emailInput";
    public static final String EDIT_SUCCESS = "edit_success";

    //Course
    public static final String ATT_COURSE_ID = "courseID";
    public static final String ATT_COURSE_NAME = "courseName";
    public static final String ATT_COURSE_DATE_START = "courseStartDate";
    public static final String ATT_COURSE_DATE_END = "courseEndDate";
    public static final String ATT_COURSE_TEACHER = "courseTeacher";
    public static final String ATT_COURSE_DESCRIPTION = "courseDescription";
    public static final String ATT_COURSE_ADD = "student_add_course";
    public static final String ATT_COURSE_ADD_COMMENT = "student_add_comment";
    public static final String ATT_COURSE_EDIT = "editCourse";
    public static final String ATT_COURSE_MARK = "markStudents";
    public static final String ATT_COURSE_FEEDBACK = "feedback";
    public static final String ATT_COURSE_USERS = "courseUsers";
    public static final String ATT_COURSE_COMMENT_TEXT = "myComment";
    public static final String ATT_COURSE_DELETE_COMMENT = "student_delete_course";

    //My courses
    public static final String ATT_MY_COURSES = "courseUsers";
    public static final String CURRENT_DATE = "currentDate";

    //Edit course
    public static final String INPUT_COURSE_NAME = "nameInput";
    public static final String INPUT_COURSE_DESCRIPTION = "descriptionInput";
    public static final String INPUT_COURSE_DATE_START = "startDateInput";
    public static final String DESCRIPTION = "description";
    public static final String COURSE_NAME = "courseName";
    public static final String COURSE_START_DATE = "startDate";
    public static final String COURSE_END_DATE = "endDate";
    public static final String INPUT_COURSE_DATE_END = "endDateInput";
    public static final String COURSE_START_DATE_VAL_ERROR = "start_date_val_error";
    public static final String COURSE_END_DATE_VAL_ERROR = "end_date_val_error";
    public static final String COURSE_START_END_DATE_VAL_ERROR = "start_end_date_val_error";
    public static final String COURSE_DESCRIPTION_VAL_ERROR = "description_val_error";
    public static final String COURSE_NAME_VAL_ERROR = "name_val_error";
    public static final String COURSE_EDIT_SUCCESS = "course_edit_success";

    //Add marks
    public static final String ATT_MARKS = "marks";
    public static final String TOTAL = "total";
    public static final String MARK_USER = "userId";
    public static final String MARKS_UPDATE_SUCCESS = "update_success";
    public static final String MARKS_INSERT_SUCCESS = "insert_success";
    public static final String MARKS_NO_CHANGES = "no_changes";
    public static final String COURSE_CATEGORY = "category";

    //Add category
    public static final String ATT_LOCALES = "locales";
    public static final String CATEGORY_VAL_ERROR = "category_val_error";
    public static final String CATEGORY_NAME = "categoryName";
    public static final String CATEGORY_ADD_SUCCESS = "success_add_category";
    public static final String CATEGORY_EXIST_ERROR = "category_exist_error";

    //Delete course
    public static final String DELETE_COURSE_ERROR = "course_delete_error";
    public static final String DELETE_COURSE_SUCCESS = "course_delete_success";
}