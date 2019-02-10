package com.epam.training.action;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WelcomeAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestDispatch = "/WEB-INF/jsp/welcome.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(requestDispatch);
        requestDispatcher.forward(request, response);
    }
}
