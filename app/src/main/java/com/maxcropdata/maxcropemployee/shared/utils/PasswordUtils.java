package com.maxcropdata.maxcropemployee.shared.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by mnk on 2017-02-13.
 */

public class PasswordUtils {

    public static String plainPasswordToPasswordHash(final String password) throws NoSuchAlgorithmException,
            UnsupportedEncodingException {
        if (password == null || "".equals(password)) {
            throw new IllegalArgumentException("password cannot be null or empty");
        }
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        final byte[] hash = digest.digest(password.getBytes("UTF-8"));
        final String passwordHash = bytesToHex(hash);
        return passwordHash;
    }

    private static String bytesToHex(byte[] bytes) {
        final char[] hexArray = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        final char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}