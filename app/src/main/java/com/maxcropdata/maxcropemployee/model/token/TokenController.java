package com.maxcropdata.maxcropemployee.model.token;

import com.maxcropdata.maxcropemployee.model.account.Account;
import com.maxcropdata.maxcropemployee.shared.utils.PasswordUtils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class TokenController {

    public static Token generateToken(Account account) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        final Token token = new Token();

        token.setRequestDate(new Date());

        token.setIdAccount(account.getAccountId());

        token.setLogin(account.getLogin());

        token.setAuthorizationString(TokenController.authorizeToken(token, account));

        return token;
    }


    /*
        generating auth string from password and requestDate
        it can be easly verfied on the server
     */
    private static String authorizeToken(Token token, Account account) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return PasswordUtils.plainPasswordToPasswordHash(account.getPassword() + token.getRequestDate().getTime());
    }
}
