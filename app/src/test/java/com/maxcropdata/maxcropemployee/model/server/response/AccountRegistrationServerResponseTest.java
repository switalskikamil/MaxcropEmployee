package com.maxcropdata.maxcropemployee.model.server.response;

import org.junit.Test;

import java.net.HttpURLConnection;
import java.util.Date;

import static org.junit.Assert.*;

public class AccountRegistrationServerResponseTest {


    final String testResponse = "{\"login\":\"john.smith#234\",\"workerId\":234,\"accountId\":2132,\"expirationDate\":1723672372333}";
    AccountRegistrationServerResponse underTest ;

    @Test
    public void isReadingStatusOkResponseProperly() {
        // given
        underTest = new AccountRegistrationServerResponse(HttpURLConnection.HTTP_OK, testResponse);


        // when
        try {
            underTest.readResponse();
        } catch (Exception e) {
            fail(e.getMessage());
        }

        // then
        assertEquals("john.smith#234", underTest.getAccount().getLogin());
        assertEquals(234, underTest.getAccount().getWorkerId());
        assertEquals(2132, underTest.getAccount().getAccountId());
        assertEquals(1723672372333L, underTest.getAccount().getExpirationDate().getTime());

    }

    @Test(expected = RequestUnathorizedException.class)
    public void isReadingStatusUnauthorizedResponseProperly()
            throws RequestUnathorizedException,
            ResponseMalformedException,
            UexpectedResponseStatusException,
            AccountAlreadyExistsException {
        // given
        underTest = new AccountRegistrationServerResponse(HttpURLConnection.HTTP_UNAUTHORIZED, testResponse);

        // then
        underTest.readResponse();

        assertNull(underTest.getAccount());

    }

    @Test(expected = UexpectedResponseStatusException.class)
    public void isReadingStatusUnknownResponseProperly()
            throws UexpectedResponseStatusException,
            ResponseMalformedException,
            RequestUnathorizedException,
            AccountAlreadyExistsException {
        // given
        underTest = new AccountRegistrationServerResponse(HttpURLConnection.HTTP_BAD_GATEWAY, testResponse);

        // then
        underTest.readResponse();

        assertNull(underTest.getAccount());

    }
}