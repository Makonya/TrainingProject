package com.epam.training.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

import com.epam.training.action.Action;
import com.epam.training.action.ActionFactory;
import com.epam.training.action.ActionResult;
import com.epam.training.action.View;
import com.epam.training.pool.ConnectionPool;
import org.apache.log4j.*;

public class ControllerServlet extends javax.servlet.http.HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ControllerServlet.class);
    private static ActionFactory actionFactory;

    @Override
    public void init() {
        LOGGER.info("The servlet started working.");
        actionFactory = new ActionFactory();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html; charset=UTF-8");
        Action action = actionFactory.getAction(req);
        ActionResult actionResult = action.execute(req, resp);
        View view = new View(req, resp);
        view.navigate(actionResult);


    }

    @Override
    public void destroy() {
        try {
            ConnectionPool.dispose();
        } catch (SQLException e) {
            LOGGER.warn("Connection pool wasn't disposed." + e.getMessage());
        }
        LOGGER.info("The servlet stopped working.");
    }
}
