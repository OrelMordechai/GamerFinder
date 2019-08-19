package com.orelandshadi.gamerfinder.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.orelandshadi.gamerfinder.R;
import com.orelandshadi.gamerfinder.models.LogFailDialog;
import com.orelandshadi.gamerfinder.models.PasswordDialog;
import com.orelandshadi.gamerfinder.ui.userprofile.MainGamesActivity;
import com.orelandshadi.gamerfinder.utils.HttpRequest;
import com.orelandshadi.gamerfinder.utils.HttpResponseCallback;
import com.orelandshadi.gamerfinder.utils.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements HttpResponseCallback {

    private Button loginButton;
    private TextInputLayout mTextInputLayoutEmail;
    private TextInputLayout mTextInputLayoutPassword;

    //String url = "http://10.0.2.2:44362/userHandle/Login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // We call this function to fill the variables
        initViews();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmInput(v);
            }
        });

        findViewById(R.id.iv_lock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
                Log.d("icon_pass", "icon_Password clicked");
            }
        });


    }

    //Initialize the views
    private void initViews() {

        loginButton = (Button) findViewById(R.id.btn_Login);
        mTextInputLayoutEmail = (TextInputLayout) findViewById(R.id.text_input_email);
        mTextInputLayoutPassword = (TextInputLayout) findViewById(R.id.text_input_password);

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

    @Override
    public void onSuccessResponse(String result) {
        Log.d("@@@ LoginActivity", "onSuccessResponse: " + result);
        Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(LoginActivity.this, MainGamesActivity.class));
    }

    @Override
    public void onErrorResponse(String result) {
        Log.d("@@@ LoginActivity", "onErrorResponse: " + result);
        LogDialog();
    }

    public void confirmInput(View v) {
        if (!validateEmail() | !validatePassword()) {
            return;
        } else {

            Context mContext = getApplicationContext();
            final String emailInput = mTextInputLayoutEmail.getEditText().getText().toString().trim();
            final String passwordInput = mTextInputLayoutPassword.getEditText().getText().toString().trim();

            Log.d("@@@ EMAIL", emailInput);
            Log.d("@@@ PASS", passwordInput);

            JSONObject jsonBodyObj = new JSONObject();
            try {
                jsonBodyObj.put("Email", emailInput);
                jsonBodyObj.put("Password", passwordInput);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            HttpRequest httpRequest = new HttpRequest(mContext);
            Log.d("@@@ jsonBodyObj", jsonBodyObj.toString());
            httpRequest.httpPostJsonRequest("login", jsonBodyObj, this);
            Log.d("@@@ line 139", "after request call");
        }
    }

    private void LogDialog() {
        LogFailDialog logFailDialog = new LogFailDialog();
        logFailDialog.show(getSupportFragmentManager(), "login failed");
    }

    private void openDialog() {
        PasswordDialog exampleDialog = new PasswordDialog();
        exampleDialog.show(getSupportFragmentManager(), "pass dialog");
    }

    public void signUpButton(View v) {
        startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
    }

    public void forgotPasswordButton(View v) {
        startActivity(new Intent(getApplicationContext(), ConfirmEmailActivity.class));
    }

}
