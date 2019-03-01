package com.epam.training.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.log4j.*;

import static com.epam.training.util.AppConstant.*;

public class View {
    private static final Logger logger = Logger.getLogger(View.class);

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public View(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void navigate(ActionResult result) {
        try {
            if (result.isRedirect()) {
                response.sendRedirect(result.getView());
            } else {
                String path = PATH_TO_JSP + result.getView() + JSP_FORMAT;
                request.getRequestDispatcher(path).forward(request, response);
            }
        } catch (ServletException | IOException e) {
            logger.error("Can't work redirect dispatcher" ,e);
        }
    }
}
