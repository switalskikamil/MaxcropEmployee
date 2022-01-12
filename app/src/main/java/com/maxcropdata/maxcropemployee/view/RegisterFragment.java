package com.maxcropdata.maxcropemployee.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.R;
import com.maxcropdata.maxcropemployee.model.account.AccountController;
import com.maxcropdata.maxcropemployee.model.registrationform.RegistrationForm;
import com.maxcropdata.maxcropemployee.model.registrationform.RegistrationFormController;
import com.maxcropdata.maxcropemployee.model.server.ServerController;
import com.maxcropdata.maxcropemployee.model.server.request.AccountRegistrationServerRequest;
import com.maxcropdata.maxcropemployee.model.token.Token;
import com.maxcropdata.maxcropemployee.shared.utils.Helper;
import com.maxcropdata.maxcropemployee.view.dialogs.AppDatePickerDialog;
import com.maxcropdata.maxcropemployee.view.mctoast.MCToast;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


public class RegisterFragment extends Fragment {
    public static RegisterFragment getInstance() {
        return new RegisterFragment();
    }

    private EditText nameEdit;
    private EditText lastNameEdit;
    private EditText employerCodeEdit;
    private EditText passwordEdit;
    private EditText repeatPasswordEdit;
    private Date birthDate = new Date();
    private TextView birthDateText;
    private MainActivity activity;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_register, container, false);

        activity = (MainActivity)getActivity();

        nameEdit = root.findViewById(R.id.register_name);
        lastNameEdit = root.findViewById(R.id.register_last_name);
        employerCodeEdit = root.findViewById(R.id.register_employer_code);
        passwordEdit = root.findViewById(R.id.register_password);
        repeatPasswordEdit = root.findViewById(R.id.register_repeat_password);
        birthDateText = root.findViewById(R.id.register_birth_date);

        final Button verifyBtn = root.findViewById(R.id.btn_verify_register);
        final Button cancelBtn = root.findViewById(R.id.btn_cancel_register);

        verifyBtn.setOnClickListener(v -> {
            try {
                performVerification();

            } catch (NoSuchFieldException |
                    NoSuchAlgorithmException |
                    IllegalAccessException |
                    UnsupportedEncodingException e) {
                MCToast.displayText((MainActivity) getActivity(), Toast.LENGTH_SHORT, e.getMessage());
            }
        });

        cancelBtn.setOnClickListener(v -> {
            activity.loadFragment(MainMenuFragment.getInstance());
        });

        birthDateText.setOnClickListener(v -> AppDatePickerDialog.popDialog(
                (MainActivity) getActivity(),
                birthDate,
                (date) -> birthDateText.setText(Helper.DATE_FORMAT.format(date))
        ));


        return root;
    }


    private void performVerification()
            throws NoSuchFieldException, NoSuchAlgorithmException,
            IllegalAccessException, UnsupportedEncodingException {
        int validatedPassword = validatePassword();
        int validateBirthDate = validateBirthDate();

        if (validatedPassword+validateBirthDate == 0) {
            RegistrationForm registrationForm = new RegistrationForm.Builder()
                    .name(nameEdit.getText().toString())
                    .lastName(lastNameEdit.getText().toString())
                    .desiredPlainPassword(passwordEdit.getText().toString())
                    .employerCode(Integer.valueOf(employerCodeEdit.getText().toString()))
                    .dateOfBirth(birthDate)
                    .build();

            verifyRegistrationWithTheServer(registrationForm);
        } else {
            MCToast.displayText((MainActivity) getActivity(), Toast.LENGTH_SHORT, getString(validatedPassword));
            MCToast.displayText((MainActivity) getActivity(), Toast.LENGTH_SHORT, getString(validateBirthDate));
        }
    }

    private int validatePassword() {
        if (!passwordEdit.getText().toString().equals(repeatPasswordEdit.getText().toString())) return R.string.warning_password_not_match;
        if (passwordEdit.getText().toString().length() < AccountController.MIN_PASSWORD_LENGTH) return R.string.warning_password_too_short;
        return 0;
    }

    private int validateBirthDate() {
        Calendar c = Calendar.getInstance();
        c.setTime(birthDate);
        c.add(Calendar.YEAR, 18);
        Date dateOfAdulthood = c.getTime();

        if (new Date().compareTo(dateOfAdulthood) < 0) return R.string.warning_wrong_birth_date;
        return 0;
    }

    private void verifyRegistrationWithTheServer(RegistrationForm registrationForm)
            throws UnsupportedEncodingException, NoSuchAlgorithmException,
            NoSuchFieldException, IllegalAccessException {

        // hash password etc
        RegistrationFormController.processRegistrationForm(registrationForm);

        // prepare request
        final AccountRegistrationServerRequest request = new AccountRegistrationServerRequest(
                new Token(),
                RegistrationFormController.convertToJSONPayload(registrationForm),
                activity.getServer(),
                activity
        );

        // perform server request
        ServerController.getInstance().executeServerRequest(request);
    }


}