package com.epam.training.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.epam.training.action.Action;
import com.epam.training.action.ActionFactory;
import com.epam.training.action.ActionResult;
import com.epam.training.action.View;
import org.apache.log4j.*;

public class ControllerServlet extends javax.servlet.http.HttpServlet {
    private static Logger logger = Logger.getLogger(ControllerServlet.class);
    private static ActionFactory actionFactory;

    @Override
    public void init() throws ServletException {
        logger.info("The servlet started working.");
        actionFactory = new ActionFactory();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        Action action = actionFactory.getAction(req);
        ActionResult actionResult = action.execute(req, resp);
        View view = new View(req, resp);
        view.navigate(actionResult);


    }

    @Override
    public void destroy() {
        logger.info("The servlet stopped working.");
    }
}