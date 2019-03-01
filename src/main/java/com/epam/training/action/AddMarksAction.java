package com.epam.training.action;

import com.epam.training.dao.CourseDao;
import com.epam.training.dao.CourseUserDao;
import com.epam.training.dao.MarkDao;
import com.epam.training.entity.CourseUser;
import com.epam.training.entity.Mark;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static com.epam.training.util.AppConstant.*;

public class AddMarksAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        int courseId = Integer.parseInt(request.getParameter(ATT_COURSE_ID));
        CourseUserDao courseUserDao = new CourseUserDao();
        List<CourseUser> courseUsers = courseUserDao.findByCourseId(courseId);
        CourseDao courseDao = new CourseDao();
        if (request.getMethod().equals(METHOD_POST)) {
            List<Mark> marksToInsert = new ArrayList<>();
            List<Mark> marksToUpdate = new ArrayList<>();
            MarkDao markDao = new MarkDao();
            for (int i = 1; i <= courseUsers.size(); i++) {
                if (!request.getParameter(TOTAL + i).equals("-")) {
                    int user = Integer.parseInt(request.getParameter(MARK_USER + i));
                    int total = Integer.parseInt(request.getParameter(TOTAL + i));
                    Mark mark = new Mark();
                    mark.setIdUser(user);
                    mark.setIdCourse(courseId);
                    mark.setTotal(total);
                    if (markDao.findByCourseUserId(mark)) {
                        if (courseUsers.get(i - 1).getTempMark() != mark.getTotal()) {
                            marksToUpdate.add(mark);
                        }
                    } else {
                        marksToInsert.add(mark);
                    }
                }
            }
            if (marksToInsert.isEmpty() && marksToUpdate.isEmpty()) {
                if (markDao.insert(marksToInsert)) request.setAttribute(MARKS_NO_CHANGES, true);
            } else {
                if (!marksToInsert.isEmpty()) {
                    if (markDao.insert(marksToInsert)) request.setAttribute(MARKS_INSERT_SUCCESS, true);
                }
                if (!marksToUpdate.isEmpty()) {
                    if (markDao.update(marksToUpdate)) request.setAttribute(MARKS_UPDATE_SUCCESS, true);
                }
            }
            courseUsers = courseUserDao.findByCourseId(courseId);
        }
        request.setAttribute(ATT_COURSE_NAME, courseDao.findById(courseId).getCourseName());
        request.setAttribute(ATT_MARKS, courseUsers);
        request.setAttribute(ATT_COURSE_ID, courseId);
        return new ActionResult(ADD_MARKS);
    }
}
