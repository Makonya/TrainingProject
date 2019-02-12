package com.epam.training;

import com.epam.training.dao.UserDao;
import com.epam.training.util.Encryption;
import org.apache.log4j.*;

public class Runner {
    public static void main(String[] args) {
        System.out.println(Encryption.toEncrypt("admin"));
    }
}
