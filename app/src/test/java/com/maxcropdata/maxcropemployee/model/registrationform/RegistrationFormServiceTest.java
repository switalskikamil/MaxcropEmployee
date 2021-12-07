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

    @Test
    public void isGeneratingValidSalt() throws ParseException {
        // given
        final RegistrationForm registrationForm = new RegistrationForm.Builder()
                .dateOfBirth(Helper.DATE_FORMAT.parse("2021-01-01"))
                .desiredPlainPassword("cojest123")
                .name("Kamil")
                .lastName("Świtalski")
                .employerCode(301)
                .build();

        String expectedSalt = "-1707259816";


        // when
        RegistrationFormService.generateSalt(registrationForm);


        // then
        assertEquals(expectedSalt, registrationForm.getGeneratedSalt());
    }

    @Test
    public void isProducingProperHash() throws ParseException {
        // given
        final RegistrationForm registrationForm = new RegistrationForm.Builder()
                .dateOfBirth(Helper.DATE_FORMAT.parse("2021-01-01"))
                .desiredPlainPassword("cojest123")
                .name("Kamil")
                .lastName("Świtalski")
                .employerCode(301)
                .build();

        String expectedHash = "b0515cfefdc4fd9077a2bfa8d417e3027b1f8378aab5b435db6442c459bfeff1";


        try {
            // when
            RegistrationFormService.generateSalt(registrationForm);

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
        final RegistrationForm registrationForm = new RegistrationForm.Builder()
                .dateOfBirth(Helper.DATE_FORMAT.parse("2021-01-01"))
                .desiredPlainPassword("cojest123")
                .name("Kamil")
                .lastName("Świtalski")
                .employerCode(301)
                .build();

        String expectedJSON = "{\"name\":\"Kamil\",\"lastName\":\"Świtalski\",\"employerCode\":301,\"dateOfBirth\":1609455600000,\"desiredHashedPassword\":\"b0515cfefdc4fd9077a2bfa8d417e3027b1f8378aab5b435db6442c459bfeff1\"}";

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
        final RegistrationForm registrationForm = new RegistrationForm.Builder()
                .dateOfBirth(Helper.DATE_FORMAT.parse("2021-01-01"))
                .desiredPlainPassword("cojest123")
                .name("Kamil")
                .lastName("Świtalski")
                .employerCode(301)
                .build();



        // when
        RegistrationFormService.generateSalt(registrationForm);

        RegistrationFormService.hashPassword(registrationForm);


        JSONObject actualJSON = new JSONObject(underTest.toJSON(registrationForm));

        RegistrationForm actualRegistrationForm = underTest.fromJSON(actualJSON);


        // then
        //that method will not be used
        assertNull(actualRegistrationForm);

    }
}