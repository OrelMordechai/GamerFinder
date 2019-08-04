package com.orelandshadi.gamerfinder.ui.login;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.orelandshadi.gamerfinder.models.PasswordDialog;
import com.orelandshadi.gamerfinder.utils.StringUtils;
import com.orelandshadi.gamerfinder.R;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private TextInputLayout mTextInputLayoutEmail;
    private TextInputLayout mTextInputLayoutPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        loginButton = (Button) findViewById(R.id.btn_Login);

        mTextInputLayoutEmail = findViewById(R.id.text_input_email);
        mTextInputLayoutPassword = findViewById(R.id.text_input_password);

        findViewById(R.id.iv_lock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
                Log.d("icon_pass", "icon_Password clicked");
            }

            private void openDialog() {
                PasswordDialog exampleDialog = new PasswordDialog();
                exampleDialog.show(getSupportFragmentManager(), "pass dialog");
            }
        });
    }

    private boolean validateEmail() {
        boolean isValid = false;
        String emailInput = mTextInputLayoutEmail.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            mTextInputLayoutEmail.setError("Field can't be empty");
        } else if (!StringUtils.isValidEmail(emailInput)) {
            mTextInputLayoutEmail.setError("Please enter a valid email address");
        } else {
            mTextInputLayoutEmail.setError(null);
            isValid = true;
        }
        return isValid;
    }

    private boolean validatePassword() {
        boolean isValid = false;
        String passwordInput = mTextInputLayoutPassword.getEditText().getText().toString().trim();

//        Log.d("@@@@@@@ isValidPassword", passwordInput);

        if (passwordInput.isEmpty()) {
            mTextInputLayoutPassword.setError("Field can't be empty");
        } else if (!StringUtils.isValidPassword(passwordInput)) {
            mTextInputLayoutPassword.setError("Password too weak, Click on the lock icon to get information");
        } else if (passwordInput.length() < 6) {
            mTextInputLayoutPassword.setError("Must contain at least 6 charters");
        } else if (passwordInput.length() > 16) {
            mTextInputLayoutPassword.setError("Must contain less than 16 charters");
        } else {
            mTextInputLayoutPassword.setError(null);
            isValid = true;
        }
        return isValid;
    }

    public void confirmInput(View v) {
        if (!validateEmail() | !validatePassword()) {
            return;
        }

        //////// SHOULD BE DELETED
        String input = "Email: " + mTextInputLayoutEmail.getEditText().getText().toString();
        input += "\n";
        input += "Password: " + mTextInputLayoutPassword.getEditText().getText().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
        //////// SHOULD BE DELETED ^

    }

    public void signUpButton(View v) {
        startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
    }

    public void forgotPasswordButton(View v) {
        startActivity(new Intent(getApplicationContext(), ConfirmEmailActivity.class));
    }

}
