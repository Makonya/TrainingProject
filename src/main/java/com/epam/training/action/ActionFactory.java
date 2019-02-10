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
        actionsMap.put("POST/login", new LoginAction());
        actionsMap.put("GET/welcome", new WelcomeAction());
    }

    public Action getAction(HttpServletRequest request){
        System.out.println(request.getMethod() + request.getPathInfo());
        return actionsMap.get(request.getMethod() + request.getPathInfo());
    }
}
