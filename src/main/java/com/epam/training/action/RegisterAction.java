package com.epam.training.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.log4j.*;

import static com.epam.training.util.AppConstant.*;

public class RegisterAction implements Action {
    private static Logger logger = Logger.getLogger(RegisterAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }
}
