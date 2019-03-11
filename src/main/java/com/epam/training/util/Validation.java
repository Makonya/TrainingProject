package com.epam.training.util;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static boolean checkParamValid(String paramName, String paramValue, String validator, HttpServletRequest request) {
        boolean valid = true;
        Pattern pattern = Pattern.compile(validator);
        Matcher matcher = pattern.matcher(paramValue);
        if (!matcher.matches()) {
            request.setAttribute(paramName, true);
            valid = false;
        }
        return valid;
    }
}
