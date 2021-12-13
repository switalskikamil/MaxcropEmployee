package com.maxcropdata.maxcropemployee.model.registrationform;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class RegistrationFormController {

    public static RegistrationForm processRegistrationForm(RegistrationForm registrationForm) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        // generate salt
        //RegistrationFormService.generateSalt(registrationForm);

        // generate password
        RegistrationFormService.hashPassword(registrationForm);

        return registrationForm;
    }
}
