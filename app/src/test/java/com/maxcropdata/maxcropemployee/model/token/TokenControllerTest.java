package com.maxcropdata.maxcropemployee.model.token;

import com.maxcropdata.maxcropemployee.model.account.Account;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class TokenControllerTest {

    @Test
    public void generateToken() {
        // given
        Account testAccount = new Account.Builder()
                .accountId(345)
                .expirationDate(new Date())
                .name("Jan")
                .lastName("Kowalski")
                .login("jan.kowalski$345")
                .password("8a78a8a8d")
                .build();


        try {
            // when
            Token token = TokenController.generateToken(testAccount);


            // then
            assertNotNull(token.getRequestDate());
            assertNotNull(token.getAuthorizationString());
            assertNotEquals(0, token.getIdAccount());
            assertEquals(token.getAuthorizationString().length(), 64);

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}