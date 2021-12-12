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

        String expectedSalt = "810sdk10di21332455691";
        String expectedPasswordHash = "e989e070abc0e1d5b0fba29881ef4c9db34765fbaf57c148553a19fa5e490db7";

        // when
        RegistrationFormController.processRegistrationForm(registrationForm);

        // then
        assertEquals(expectedPasswordHash, registrationForm.getDesiredHashedPassword());
        assertEquals(expectedSalt, registrationForm.getGeneratedSalt());


    }
}