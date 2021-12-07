package com.maxcropdata.maxcropemployee.model.token;

import java.util.Date;
import java.util.Objects;

/*
authorization token
 */
public class Token {
    private Date requestDate;
    private String authorizationString;
    private long idAccount;

    public Token() {

    }

    public Token(Date requestDate, String authorizationString, long idAccount) {
        this.requestDate = requestDate;
        this.authorizationString = authorizationString;
        this.idAccount = idAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return idAccount == token.idAccount &&
                Objects.equals(requestDate, token.requestDate) &&
                Objects.equals(authorizationString, token.authorizationString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestDate, authorizationString, idAccount);
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getAuthorizationString() {
        return authorizationString;
    }

    public void setAuthorizationString(String authorizationString) {
        this.authorizationString = authorizationString;
    }

    public long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(long idAccount) {
        this.idAccount = idAccount;
    }



}
