package com.maxcropdata.maxcropemployee.model.account;

import android.content.Context;

import com.maxcropdata.maxcropemployee.model.registrationform.RegistrationForm;
import com.maxcropdata.maxcropemployee.model.registrationform.RegistrationFormController;
import com.maxcropdata.maxcropemployee.model.server.response.AccountRegistrationServerResponse;
import com.maxcropdata.maxcropemployee.shared.utils.FileManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class AccountController {
    public static final String FILE_NAME = "account.json";


    public static Account prepareAccount(RegistrationForm registrationForm) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        final Account account = new Account();

        RegistrationFormController.processRegistrationForm(registrationForm);

        account.setPassword(registrationForm.getDesiredHashedPassword());
        account.setName(registrationForm.getName());
        account.setLastName(registrationForm.getLastName());

        return account;
    }

    public static void mergeWithRegistrationResponse(Account localAccount, AccountRegistrationServerResponse serverResponse) {
        localAccount.setAccountId(serverResponse.getAccount().getAccountId());
        localAccount.setExpirationDate(serverResponse.getAccount().getExpirationDate());
        localAccount.setLogin(serverResponse.getAccount().getLogin());
        localAccount.setWorkerId(serverResponse.getAccount().getWorkerId());
    }

    public static void saveAccountToFileSystem(
            Context context,
            Account account)
            throws IllegalAccessException {
        saveAccountToFileSystem(context, account, FILE_NAME);
    }


    public static void saveAccountToFileSystem(
            Context context,
            Account account,
            String fileName)
            throws IllegalAccessException {
        AccountService service = new AccountService();
        FileManager.writeFileToStorage(context, fileName, service.toJSON(account));
    }


    public static Account readAccountFromFileSystem(Context context)
            throws JSONException, IllegalAccessException {
        return readAccountFromFileSystem(context, FILE_NAME);
    }

    public static Account readAccountFromFileSystem(
            Context context,
            String fileName
    ) throws JSONException, IllegalAccessException {

        AccountService service = new AccountService();

        String file = FileManager.readFileFromStorage(context, fileName);

        if (file != null) {
            return service.fromJSON(new JSONObject(file));
        } else {
            return null;
        }
    }


}
