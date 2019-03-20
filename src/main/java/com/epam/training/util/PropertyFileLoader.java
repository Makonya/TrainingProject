package com.epam.training.util;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static com.epam.training.util.AppConstant.*;

public class PropertyFileLoader {
    private static final Logger LOGGER = Logger.getLogger(PropertyFileLoader.class);

    public static Properties load(final String filename) {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(DIRECTORY + RESOURCES_DIRECTORY + filename)) {
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            LOGGER.warn("Resource file wasn't found." + e.getMessage());
        } catch (IOException e) {
            LOGGER.warn("Errors occurred while reading resource file" + e.getMessage());
        }
        return properties;
    }
}