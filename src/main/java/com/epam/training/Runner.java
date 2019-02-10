package com.epam.training;

import com.epam.training.dao.UserDao;
import org.apache.log4j.*;

public class Runner {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        userDao.findAll();
    }
}
