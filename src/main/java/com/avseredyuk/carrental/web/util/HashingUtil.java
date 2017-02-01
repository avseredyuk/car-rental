package com.avseredyuk.carrental.web.util;

import org.apache.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by lenfer on 1/22/17.
 */
public class HashingUtil {
    private static final Logger logger = Logger.getLogger(HashingUtil.class);
    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();

    private HashingUtil() {
    }

    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static String hashPassword(String in) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(in.getBytes(StandardCharsets.UTF_8));
            byte[] out = md.digest();
            return bytesToHex(out);
        } catch (NoSuchAlgorithmException e) {
            logger.error("Error hashing: not found error", e);
        }
        return "";
    }
}
