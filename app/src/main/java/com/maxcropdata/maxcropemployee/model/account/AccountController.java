package com.maxcropdata.maxcropemployee.model.account;

import android.content.Context;
import android.util.Log;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.model.registrationform.RegistrationForm;
import com.maxcropdata.maxcropemployee.model.registrationform.RegistrationFormController;
import com.maxcropdata.maxcropemployee.model.server.response.AccountLoginServerResponse;
import com.maxcropdata.maxcropemployee.model.server.response.AccountRegistrationServerResponse;
import com.maxcropdata.maxcropemployee.shared.utils.FileManager;
import com.maxcropdata.maxcropemployee.shared.utils.PasswordUtils;
import com.maxcropdata.maxcropemployee.view.AccountSettingsFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

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

    public static void mergeWithLoginResponse(Account localAccount, AccountLoginServerResponse serverResponse) {
        localAccount.setAccountId(serverResponse.getAccount().getAccountId());
        localAccount.setExpirationDate(serverResponse.getAccount().getExpirationDate());
        localAccount.setWorkerId(serverResponse.getAccount().getWorkerId());
        localAccount.setLastName(serverResponse.getAccount().getLastName());
        localAccount.setName(serverResponse.getAccount().getName());
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
        Log.d("MCM", "AccountController.readAccountFromFileSystem: " + file);

        if (file != null && file.length() > 0) {
            return service.fromJSON(new JSONObject(file));
        } else {
            return null;
        }
    }


    public static void loginAccount(String login, String plainPassword, MainActivity activity)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, PasswordTooShortException,
            LoginTooShortException, PasswordIsPlaceHolderException {

        if (activity.getUserAccount() == null) activity.setUserAccount(new Account());

        if (login != null && login.length() > MIN_LOGIN_LENGTH)
            activity.getUserAccount().setLogin(login);
        else throw new LoginTooShortException();

        if (plainPassword != null && !plainPassword.equals(AccountSettingsFragment.PSWD_PLACEHOLDER)) {
            if (plainPassword.length() >= MIN_PASSWORD_LENGTH) {

                activity.getUserAccount().setPassword(PasswordUtils.generatePassword(
                        activity.getUserAccount().getLogin(), plainPassword)
                );

                //at this point we don't know if users account is not expired
                //lets set temporary expiration date for tomorrow and check with server
                activity.getUserAccount().setExpirationDate(new Date(new Date().getTime() + 8400000));
                activity.getUserAccount().setName("");
                activity.getUserAccount().setLastName("");


            } else throw new PasswordTooShortException();
        } else {
            throw new PasswordIsPlaceHolderException();
        }
    }
}
