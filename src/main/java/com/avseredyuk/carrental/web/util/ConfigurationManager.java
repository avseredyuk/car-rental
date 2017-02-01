package com.avseredyuk.carrental.web.util;

import java.util.ResourceBundle;

/**
 * Created by lenfer on 1/6/17.
 */
public class ConfigurationManager {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
    private ConfigurationManager() { }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
