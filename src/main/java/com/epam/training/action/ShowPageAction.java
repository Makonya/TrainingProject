package com.epam.training.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowPageAction implements Action {
    private ActionResult page;

    public ShowPageAction(String page) {
        this.page = new ActionResult(page);
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return page;
    }
}
