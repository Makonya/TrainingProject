package com.epam.training.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileLoader {
    public static final String DIRECTORY = "src/main/resources/";

    public static Properties load(final String filename) {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(DIRECTORY + filename)) {
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
