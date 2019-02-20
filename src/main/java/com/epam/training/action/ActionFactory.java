package com.epam.training.action;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.epam.training.util.AppConstant.*;

public class ActionFactory {
    private Map<String, Action> actionsMap = new HashMap<>();

    public ActionFactory() {
        init();
    }

    private void init(){

        actionsMap.put("GET/welcome", new ShowPageAction(WELCOME));
        actionsMap.put("GET/authorization", new ShowPageAction(AUTHORIZATION));
        actionsMap.put("GET/registration", new ShowPageAction(REGISTER));
        actionsMap.put("GET/set-language", new ChangeLanguageAction());
        actionsMap.put("GET/changeCategory", new ChangeCategoryAction());
        actionsMap.put("GET/listOfCourses", new ListOfCoursesAction());
        actionsMap.put("GET/logout", new LogoutAction());
        actionsMap.put("GET/profile", new ShowProfileAction());
        actionsMap.put("GET/course", new ShowCourseAction());
        actionsMap.put("GET/addComment", new AddCommentAction());
        actionsMap.put("GET/addCourse", new AddCourseAction());
        actionsMap.put("GET/deleteCourse", new DeleteCourseAction());
        actionsMap.put("GET/myCourses", new ShowMyCoursesAction());
        actionsMap.put("GET/editCourse", new EditCourseAction());

        actionsMap.put("POST/authorization", new LoginAction());
        actionsMap.put("POST/registration", new RegisterAction());
        actionsMap.put("POST/profile", new EditProfileDataAction());
        actionsMap.put("POST/editCourse", new EditCourseAction());
    }

    public Action getAction(HttpServletRequest request){
        System.out.println(request.getMethod() + request.getPathInfo());
        return actionsMap.get(request.getMethod() + request.getPathInfo());
    }
}
