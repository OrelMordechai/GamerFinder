package com.orelandshadi.gamerfinder.ui.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.orelandshadi.gamerfinder.R;
import com.orelandshadi.gamerfinder.models.SessionData;
import com.orelandshadi.gamerfinder.models.UserData;
import com.orelandshadi.gamerfinder.utils.StringUtils;


public class SignUpActivity extends AppCompatActivity {

    // All this variables are NULL
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private Button signUpButton;
    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        // We calling this function for fill the variables
        initViews();
    }

    //Initialize the views
    private void initViews() {

        emailEditText = (EditText) findViewById(R.id.SignUpEmail);
        passwordEditText = (EditText) findViewById(R.id.SignUpPassword);
        confirmPasswordEditText = (EditText) findViewById(R.id.ConfirmPassword);
        signUpButton = (Button) findViewById(R.id.btn_SignUp);
        mCheckBox = (CheckBox) findViewById(R.id.Checkbox_Terms);


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validUserDetails();
            }
        });
    }

    //Validate the user credentials
    private void validUserDetails() {
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();
        String email = emailEditText.getText().toString();

        // Checking all the details
        if (email != null && password != null && confirmPassword != null && !password.isEmpty()) {
            if (!StringUtils.isValidEmail(email)) {
                Toast.makeText(getApplicationContext(), "Enter valid email", Toast.LENGTH_LONG).show();
                return;
            } else if (!password.equals(confirmPassword)) {
                Toast.makeText(getApplicationContext(), "Enter valid password", Toast.LENGTH_LONG).show();
                return;
            } else {
                if( mCheckBox.isChecked()) {
                    UserData userData = new UserData(email, password);
                    SessionData.sharedInstance().setUserData(userData);
                    startActivity(new Intent(getApplicationContext(), DetailsActivity.class));
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please select the terms", Toast.LENGTH_LONG).show();
                }

            }
        } else {
            Toast.makeText(getApplicationContext(), "Please enter your credentials", Toast.LENGTH_LONG).show();
        }
    }

    //Back to Login screen
    //TODO: Change method name == I change it !
    public void loginButton(View v) {
        finish();
    }

}