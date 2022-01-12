package com.maxcropdata.maxcropemployee.model.account;

import android.content.Context;

import com.maxcropdata.maxcropemployee.model.registrationform.RegistrationForm;
import com.maxcropdata.maxcropemployee.model.registrationform.RegistrationFormController;
import com.maxcropdata.maxcropemployee.model.server.response.AccountRegistrationServerResponse;
import com.maxcropdata.maxcropemployee.shared.utils.FileManager;
import com.maxcropdata.maxcropemployee.shared.utils.PasswordUtils;
import com.maxcropdata.maxcropemployee.view.AccountSettingsFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class AccountController {
    public static final String FILE_NAME = "account.json";
    public static final int MIN_PASSWORD_LENGTH = 8;
    public static final int MIN_LOGIN_LENGTH = 5;


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

        if (file != null && file.length() > 0) {
            return service.fromJSON(new JSONObject(file));
        } else {
            return null;
        }
    }


    public static boolean loginAccount(String login, String plainPassword, Account account)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {

        if (account == null) account = new Account();

        if (login != null && login.length() > MIN_LOGIN_LENGTH) account.setLogin(login);
        else return false;

        if (plainPassword != null &&
            plainPassword != AccountSettingsFragment.PSWD_PLACEHOLDER &&
            plainPassword.length() > MIN_PASSWORD_LENGTH) {
            account.setPassword(PasswordUtils.generatePassword(account.getLogin(), plainPassword));
        } else return false;

        return true;
    }
}
