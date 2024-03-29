package com.maxcropdata.maxcropemployee.model.registrationform;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class RegistrationFormController {

    public static RegistrationForm processRegistrationForm(RegistrationForm registrationForm)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {
        // generate password
        RegistrationFormService.hashPassword(registrationForm);

        return registrationForm;
    }

    public static String convertToJSONPayload(RegistrationForm registrationForm)
            throws NoSuchFieldException, IllegalAccessException {
        return RegistrationFormService.getInstance().toJSON(registrationForm);
    }
}
