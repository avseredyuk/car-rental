package com.avseredyuk.carrental.web.util;

/**
 * Created by lenfer on 1/30/17.
 */
public class ParamsValidatorUtil {
    private ParamsValidatorUtil() {
        // do nothing
    }

    public static boolean checkAllNotEmpty(String... params) {
        boolean result = true;
        for (int i = 0; i < params.length; i++) {
            if (("".equals(params[i])) || (params[i] == null)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static boolean checkAllNotNull(Object... params) {
        boolean result = true;
        for (int i = 0; i < params.length; i++) {
            if (params[i] == null) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static boolean checkAllNonNegative(int... params) {
        boolean result = true;
        for (int i = 0; i < params.length; i++) {
            if (params[i] < 0) {
                result = false;
                break;
            }
        }
        return result;
    }
}
