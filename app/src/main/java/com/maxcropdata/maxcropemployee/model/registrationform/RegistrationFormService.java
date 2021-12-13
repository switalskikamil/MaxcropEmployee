package com.maxcropdata.maxcropemployee.model.registrationform;

import com.maxcropdata.maxcropemployee.shared.interfaces.JSONAble;
import com.maxcropdata.maxcropemployee.shared.utils.JSONService;
import com.maxcropdata.maxcropemployee.shared.utils.PasswordUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.NoSuchAlgorithmException;

public class RegistrationFormService implements JSONAble<RegistrationForm> {



    /**
     * Generating salt based on name, last name and date of birth
     * method of generating must be identical on all devices as
     * the salt wont be stored on the server
     * @param registrationForm registration form that will have its salt filled
     */
    /*static void generateSalt(RegistrationForm registrationForm) {
        registrationForm.setGeneratedSalt(PasswordUtils.generateSaltForRegistrationForm(registrationForm));
    }*/

    static void hashPassword(RegistrationForm registrationForm) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        registrationForm.setDesiredHashedPassword(PasswordUtils.generatePassword(registrationForm));
    }




    public String toJSON(RegistrationForm registrationForm) throws IllegalAccessException, NoSuchFieldException {
        Field[] desireFields = new Field[] {
                RegistrationForm.class.getDeclaredField("name"),
                RegistrationForm.class.getDeclaredField("lastName"),
                RegistrationForm.class.getDeclaredField("employerCode"),
                RegistrationForm.class.getDeclaredField("dateOfBirth"),
                RegistrationForm.class.getDeclaredField("desiredHashedPassword")
        };

        return JSONService.formatAsJSON(registrationForm, desireFields);
    }


    @Override
    public RegistrationForm fromJSON(JSONObject json) throws JSONException, IllegalAccessException {
        return null;
    }
}
