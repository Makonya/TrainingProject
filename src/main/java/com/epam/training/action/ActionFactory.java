package com.epam.training.action;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private Map<String, Action> actionsMap = new HashMap<>();

    public ActionFactory() {
        init();
    }

    private void init(){

        actionsMap.put("GET/welcome", new ShowPageAction("welcome"));
        actionsMap.put("GET/authorization", new ShowPageAction("authorization"));
        actionsMap.put("GET/registration", new ShowPageAction("registration"));
        actionsMap.put("GET/set-language", new ChangeLanguageAction());
        actionsMap.put("GET/listOfCourses", new ShowPageAction("listOfCourses"));

        actionsMap.put("POST/authorization", new LoginAction());
        actionsMap.put("POST/registration", new RegisterAction());
    }

    public Action getAction(HttpServletRequest request){
        System.out.println(request.getMethod() + request.getPathInfo());
        return actionsMap.get(request.getMethod() + request.getPathInfo());
    }
}
