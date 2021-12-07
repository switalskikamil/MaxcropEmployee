package com.maxcropdata.maxcropemployee.model.account;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class AccountServiceTest {

    AccountService underTests = new AccountService();
    Account testAccount = new Account.Builder()
            .accountId(345)
            .expirationDate(new Date())
            .name("Jan")
            .lastName("Kowalski")
            .login("jan.kowalski$345")
            .password("8a78a8a8d")
            .build();

    @Test
    public void isProducingProperJSONFile() throws IllegalAccessException {
        // given
        // test account

        // when
        String jsonFile = underTests.toJSON(testAccount);

        // then
        try {
            new JSONObject(jsonFile);
        } catch (JSONException e) {
            fail(e.toString());
        }
    }

    @Test
    public void isProperlyReadingFromJSON() {
        try {
            // given
            JSONObject expectedJSON = new JSONObject(
                    underTests.toJSON(
                            testAccount
                    )
            );


            // when
            Account actualAccount = underTests.fromJSON(expectedJSON);

            // then
            assertEquals(testAccount, actualAccount);

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}