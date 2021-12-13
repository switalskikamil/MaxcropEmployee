package com.maxcropdata.maxcropemployee.shared.utils;

import com.maxcropdata.maxcropemployee.model.account.Account;
import com.maxcropdata.maxcropemployee.model.registrationform.RegistrationForm;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by mnk on 2017-02-13.
 */

public class PasswordUtils {

    private static final String SALT_PREF = "810sdk10di2";

    public static String toSHA256(final String password) throws NoSuchAlgorithmException,
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

    private static String generateSaltForLogin(String login) {
        return SALT_PREF + (login.substring(0, login.lastIndexOf("#"))).hashCode();
    }

    private static String generateSaltForRegistrationForm(RegistrationForm registrationForm) {
        return SALT_PREF + (registrationForm.getName() + "." + registrationForm.getLastName()).toLowerCase().hashCode();
    }

    public static String generatePassword(String login, String plainPassword)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return PasswordUtils.toSHA256(plainPassword + generateSaltForLogin(login));
    }

    public static String generatePassword(RegistrationForm registrationForm)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {

        registrationForm.setGeneratedSalt(PasswordUtils.generateSaltForRegistrationForm(registrationForm));

        return PasswordUtils.toSHA256(
                registrationForm.getDesiredPlainPassword() +
                        registrationForm.getGeneratedSalt());
    }
}