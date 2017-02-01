package com.avseredyuk.carrental.util;

import java.util.ResourceBundle;

/**
 * Created by lenfer on 12/29/16.
 */
public class PropertiesUtil {
    private static final ResourceBundle queriesResourceBundle = ResourceBundle.getBundle("queriesdata");
    private static final ResourceBundle dbconnResourceBundle = ResourceBundle.getBundle("sqldata");

    public enum Source {
        QUERIES, DBCONN
    }

    private PropertiesUtil(){}

    public static String getProperty(String key, Source source) {
        return source.equals(Source.QUERIES) ?
                queriesResourceBundle.getString(key) : dbconnResourceBundle.getString(key);
    }
}
