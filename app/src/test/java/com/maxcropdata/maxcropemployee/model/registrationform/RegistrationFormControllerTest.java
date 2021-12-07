package com.maxcropdata.maxcropemployee.model.registrationform;

import com.maxcropdata.maxcropemployee.shared.utils.Helper;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import static org.junit.Assert.*;

public class RegistrationFormControllerTest {

    @Test
    public void processRegistrationForm() throws ParseException, UnsupportedEncodingException, NoSuchAlgorithmException {
        // given
        final RegistrationForm registrationForm = new RegistrationForm.Builder()
                .dateOfBirth(Helper.DATE_FORMAT.parse("2021-01-01"))
                .desiredPlainPassword("cojest123")
                .name("Kamil")
                .lastName("Świtalski")
                .employerCode(301)
                .build();

        String expectedSalt = "-1707259816";
        String expectedPasswordHash = "b0515cfefdc4fd9077a2bfa8d417e3027b1f8378aab5b435db6442c459bfeff1";

        // when
        RegistrationFormController.processRegistrationForm(registrationForm);

        // then
        assertEquals(expectedPasswordHash, registrationForm.getDesiredHashedPassword());
        assertEquals(expectedSalt, registrationForm.getGeneratedSalt());


    }
}