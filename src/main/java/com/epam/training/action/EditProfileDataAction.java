package com.epam.training.action;

import com.epam.training.dao.UserDao;
import com.epam.training.entity.User;
import com.epam.training.util.Encryption;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.training.util.AppConstant.*;
import static com.epam.training.util.AppConstant.SURNAME_VALIDATION;
import static com.epam.training.util.Validation.checkParamValid;

public class EditProfileDataAction implements Action {
    private User user;
    private String password;
    private String passwordRepeat;
    private String name;
    private String surname;
    private String email;
    private int correctness = 0;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = (int) request.getSession().getAttribute(ATT_USER_ID);
        UserDao userDao = new UserDao();
        user = userDao.findById(userId);
        password = request.getParameter(PASSWORD);
        passwordRepeat = request.getParameter(PASSWORD_REPEAT);
        name = request.getParameter(NAME);
        surname = request.getParameter(SURNAME);
        email = request.getParameter(EMAIL);
        paramValidation(request);
        if (!password.equals(passwordRepeat)) {
            request.setAttribute(PASSWORD2_VAL_ERROR, true);
            correctness++;
        }
        if(correctness == 0){
            fillEditedUserParameters();
            request.setAttribute(EDIT_SUCCESS, userDao.update(user));
            setProfileValues(request);

        } else{
            correctness = 0;
            setEditedProfileValues(request);
        }
        return new ActionResult(PROFILE);
    }

    private void paramValidation(HttpServletRequest req) {
        if(!password.isEmpty() && !passwordRepeat.isEmpty()){
            if(!checkParamValid(PASSWORD_VAL_ERROR, password, PASSWORD_VALIDATION, req)) correctness++;
        }
        if(!checkParamValid(EMAIL_VAL_ERROR, email, EMAIL_VALIDATION, req)) correctness++;
        if(!checkParamValid(NAME_VAL_ERROR, name, NAME_VALIDATION, req)) correctness++;
        if(!checkParamValid(SURNAME_VAL_ERROR, surname, SURNAME_VALIDATION, req)) correctness++;
    }

    private void fillEditedUserParameters() {
        if(!password.isEmpty() && !passwordRepeat.isEmpty()){
            user.setPassword(Encryption.toEncrypt(password));
        }
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
    }

    void setProfileValues(HttpServletRequest request){
        request.setAttribute(INPUT_LOGIN, user.getLogin());
        request.setAttribute(INPUT_NAME, user.getName());
        request.setAttribute(INPUT_SURNAME, user.getSurname());
        request.setAttribute(INPUT_EMAIL, user.getEmail());
    }

    void setEditedProfileValues(HttpServletRequest request){
        request.setAttribute(INPUT_LOGIN, user.getLogin());
        request.setAttribute(INPUT_NAME, name);
        request.setAttribute(INPUT_SURNAME, surname);
        request.setAttribute(INPUT_EMAIL, email);
    }
}