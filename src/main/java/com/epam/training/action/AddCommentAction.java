package com.epam.training.action;

import com.epam.training.dao.FeedbackDao;
import com.epam.training.entity.Feedback;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;


import static com.epam.training.util.AppConstant.*;

public class AddCommentAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commentText = request.getParameter(ATT_COURSE_COMMENT_TEXT);
        String courseId = request.getParameter(ATT_COURSE_ID);
        Integer userId = (Integer) request.getSession().getAttribute(ATT_USER_ID);
        java.util.Date currentDate = new java.util.Date();
        java.sql.Date sqlDate = new Date(currentDate.getTime());
        Feedback feedback = new Feedback();
        feedback.setFeedbackText(commentText);
        feedback.setFeedbackDate(sqlDate);
        feedback.setIdCourse(Integer.parseInt(courseId));
        feedback.setIdUser(userId);
        FeedbackDao feedbackDao = new FeedbackDao();
        if(feedbackDao.insert(feedback)){
            request.setAttribute(ATT_COURSE_ADD_COMMENT_SUCCESS, true);
        }
        return new ActionResult(request.getHeader(REFERER), true);
    }
}