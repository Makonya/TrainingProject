package com.epam.training.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static com.epam.training.util.AppConstant.*;

public class PropertyFileLoader {

    public static Properties load(final String filename) {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(DIRECTORY + RECOURSES_DIRECTORY + filename)) {
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
