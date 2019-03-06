package com.epam.training.action;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.epam.training.util.AppConstant.*;

public class ActionFactory {
    private static final Map<String, Action> ACTIONS_MAP = new HashMap<>();

    public ActionFactory() {
        init();
    }

    private void init() {

        ACTIONS_MAP.put("GET/welcome", new ShowPageAction(WELCOME));
        ACTIONS_MAP.put("GET/authorization", new ShowPageAction(AUTHORIZATION));
        ACTIONS_MAP.put("GET/registration", new ShowPageAction(REGISTER));
        ACTIONS_MAP.put("GET/set-language", new ChangeLanguageAction());
        ACTIONS_MAP.put("GET/changeCategory", new ChangeCategoryAction());
        ACTIONS_MAP.put("GET/listOfCourses", new ListOfCoursesAction());
        ACTIONS_MAP.put("GET/logout", new LogoutAction());
        ACTIONS_MAP.put("GET/profile", new ShowProfileAction());
        ACTIONS_MAP.put("GET/course", new ShowCourseAction());
        ACTIONS_MAP.put("GET/addComment", new AddCommentAction());
        ACTIONS_MAP.put("GET/addCourse", new AddCourseToMyCoursesAction());
        ACTIONS_MAP.put("GET/deleteCourseFromMyCourses", new DeleteCourseFromMyCoursesAction());
        ACTIONS_MAP.put("GET/myCourses", new ShowMyCoursesAction());
        ACTIONS_MAP.put("GET/editCourse", new EditCourseAction());
        ACTIONS_MAP.put("GET/addMarks", new AddMarksAction());
        ACTIONS_MAP.put("GET/addNewCourse", new AddNewCourseAction());
        ACTIONS_MAP.put("GET/addCategory", new AddCategoryAction());
        ACTIONS_MAP.put("GET/deleteCoursePage", new ShowDeleteCoursePage());
        ACTIONS_MAP.put("GET/deleteCourse", new DeleteCourseAction());

        ACTIONS_MAP.put("POST/authorization", new LoginAction());
        ACTIONS_MAP.put("POST/registration", new RegisterAction());
        ACTIONS_MAP.put("POST/profile", new EditProfileDataAction());
        ACTIONS_MAP.put("POST/editCourse", new EditCourseAction());
        ACTIONS_MAP.put("POST/addMarks", new AddMarksAction());
        ACTIONS_MAP.put("POST/addNewCourse", new AddNewCourseAction());
        ACTIONS_MAP.put("POST/addCategory", new AddCategoryAction());
    }

    public Action getAction(HttpServletRequest request) {
        System.out.println(request.getMethod() + request.getPathInfo());
        return ACTIONS_MAP.get(request.getMethod() + request.getPathInfo());
    }
}
