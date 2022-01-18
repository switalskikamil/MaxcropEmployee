package com.maxcropdata.maxcropemployee.model.registrationform;

import com.maxcropdata.maxcropemployee.shared.utils.Helper;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import static org.junit.Assert.*;

public class RegistrationFormServiceTest {

    final RegistrationFormService underTest = new RegistrationFormService();

    final RegistrationForm registrationForm = new RegistrationForm.Builder()
            .dateOfBirth(Helper.DATE_FORMAT.parse("2021-01-01"))
            .desiredPlainPassword("12345678")
            .name("miłosz")
            .lastName("adamczyk")
            .employerCode(301)
            .build();

    public RegistrationFormServiceTest() throws ParseException {
    }

    @Test
    public void isProducingProperHash() throws ParseException {
        // given
        String expectedHash = "b32d51ae20bd441834302a7a5bc36f1c3341fea55ad43aa19299bb1315dfb99d";


        try {
            // when
            RegistrationFormService.hashPassword(registrationForm);


            //then
            assertEquals(expectedHash, registrationForm.getDesiredHashedPassword());

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void isGeneratingAsProperJSON() throws ParseException {
        // given
        String expectedJSON = "{\"name\":\"miłosz\",\"lastName\":\"adamczyk\",\"employerCode\":301,\"dateOfBirth\":1609455600000,\"desiredHashedPassword\":\"b32d51ae20bd441834302a7a5bc36f1c3341fea55ad43aa19299bb1315dfb99d\"}";

        try {
            // when
            RegistrationFormController.processRegistrationForm(registrationForm);


            String actualJSON = underTest.toJSON(registrationForm);

            // then
            assertEquals(expectedJSON, actualJSON);

        }catch (Exception e) {
            fail(e.toString());
        }
    }

    @Test
    public void isReadingFromJSON() throws ParseException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchFieldException, IllegalAccessException, JSONException {
        // given


        // when
        RegistrationFormService.hashPassword(registrationForm);


        JSONObject actualJSON = new JSONObject(underTest.toJSON(registrationForm));

        RegistrationForm actualRegistrationForm = underTest.fromJSON(actualJSON);


        // then
        //that method will not be used
        assertNull(actualRegistrationForm);

    }


}