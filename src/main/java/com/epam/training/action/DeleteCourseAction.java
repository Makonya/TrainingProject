package com.epam.training.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.training.util.AppConstant.DELETE_COURSE;

public class DeleteCourseAction implements Action {

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        return new ActionResult(DELETE_COURSE);
    }
}
