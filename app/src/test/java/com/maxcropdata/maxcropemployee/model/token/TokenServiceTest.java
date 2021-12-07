package com.maxcropdata.maxcropemployee.model.token;

import com.maxcropdata.maxcropemployee.model.account.Account;

import org.json.JSONObject;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class TokenServiceTest {

    TokenService underTests = new TokenService();

    @Test
    public void isProducingProperJSONFile() {
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

            Token token = TokenController.generateToken(testAccount);

            // when
            String jsonFile = underTests.toJSON(token);

            // then

            new JSONObject(jsonFile);
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    @Test
    public void fromJSON() {
    }
}