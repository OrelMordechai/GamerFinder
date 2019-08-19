package com.orelandshadi.gamerfinder.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.orelandshadi.gamerfinder.R;
import com.orelandshadi.gamerfinder.models.SessionData;
import com.orelandshadi.gamerfinder.models.UserData;
import com.orelandshadi.gamerfinder.models.PasswordDialog;
import com.orelandshadi.gamerfinder.ui.userprofile.MainGamesActivity;
import com.orelandshadi.gamerfinder.utils.HttpRequest;
import com.orelandshadi.gamerfinder.utils.HttpResponseCallback;
import com.orelandshadi.gamerfinder.utils.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity implements HttpResponseCallback {

    // All this variables are NULL
    private Button signUpButton;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // We call this function to fill the variables
        initViews();

        //User information about entering the password
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


    //Initialize the views
    private void initViews() {

        signUpButton = (Button) findViewById(R.id.btn_SignUp);
        emailEditText = (EditText) findViewById(R.id.SignUpEmail);
        passwordEditText = (EditText) findViewById(R.id.SignUpPassword);
        confirmPasswordEditText = (EditText) findViewById(R.id.ConfirmPassword);
        mCheckBox = (CheckBox) findViewById(R.id.Checkbox_Terms);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmInput(v);
            }
        });
    }

    private boolean validateEmail() {
        boolean isValid = false;
        String emailInput = emailEditText.getText().toString().trim();

        Log.d("@@@ [emailInput]: ", emailInput);

        if (emailInput.isEmpty()) {
            emailEditText.setError("Field can't be empty");
        } else if (!StringUtils.isValidEmail(emailInput)) {
            emailEditText.setError("Please enter a valid email address");
        } else {
            emailEditText.setError(null);
            isValid = true;
        }
        return isValid;
    }

    private boolean validatePassword() {
        boolean isValid = false;
        String passwordInput = passwordEditText.getText().toString().trim();
        String passwordConfirmInput = confirmPasswordEditText.getText().toString().trim();

        Log.d("@@@ [passwordInput]: ", passwordInput);
        Log.d("@@@ [passwordConfirm]: ", passwordConfirmInput);

        if (passwordInput.isEmpty()) {
            passwordEditText.setError("Field can't be empty");
        } else if (passwordConfirmInput.isEmpty()) {
            confirmPasswordEditText.setError("Field can't be empty");
        } else if (!passwordInput.equals(passwordConfirmInput)) {
            confirmPasswordEditText.setError("Password don't match, try again.");
        } else if (!StringUtils.isValidPassword(passwordInput)) {
            passwordEditText.setError("Password too weak, Click on the lock icon to get information");
        } else if (passwordInput.length() < 6) {
            passwordEditText.setError("Must contain at least 6 charters");
        } else if (passwordInput.length() > 16) {
            passwordEditText.setError("Must contain less than 16 charters");
        } else {
            passwordEditText.setError(null);
            confirmPasswordEditText.setError(null);
            isValid = true;
        }
        return isValid;
    }

    private boolean validateCheckbox() {
        boolean isValid = false;

        Log.d("@@@ [mCheckBox]: ", mCheckBox.isChecked() ? "TRUE" : "FALSE");

        if (mCheckBox.isChecked()) {
            isValid = true;
        } else {
            Toast.makeText(getApplicationContext(), "Please read and agree terms and privacy.", Toast.LENGTH_LONG).show();
        }
        return isValid;
    }

    @Override
    public void onSuccessResponse(String result) {
        Log.d("@@@ SignUpActivity", "onSuccessResponse: " + result);
        final String emailInput = emailEditText.getText().toString().trim();
        final String passwordInput = passwordEditText.getText().toString().trim();
        Toast.makeText(SignUpActivity.this, "Ok", Toast.LENGTH_SHORT).show();
        UserData userData = new UserData(emailInput, passwordInput);
        SessionData.sharedInstance().setUserData(userData);
        startActivity(new Intent(SignUpActivity.this, DetailsActivity.class));
    }

    @Override
    public void onErrorResponse(String result) {
        Log.d("@@@ SignUpActivity", "onErrorResponse: " + result);
    }

    public void confirmInput(View v) {
        if (!validateEmail() | !validatePassword() | !validateCheckbox()) {
            return;
        } else {

            Context mContext = getApplicationContext();

            final String emailInput = emailEditText.getText().toString().trim();

            Log.d("@@@ EMAIL", emailInput);

            JSONObject jsonBodyObj = new JSONObject();
            try {
                jsonBodyObj.put("email", emailInput);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            HttpRequest httpRequest = new HttpRequest(mContext);
            httpRequest.httpPostJsonRequest("isEmailExists", jsonBodyObj, this);
            Log.d("@@@ SignUpActivity", "confirmInput() after request call");
        }
    }

    //Back to Login screen
    //TODO: Change method name == I change it !
    public void loginButton(View v) {
        finish();
    }

}