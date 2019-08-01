package com.orelandshadi.gamerfinder.ui.login;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.orelandshadi.gamerfinder.R;
import com.orelandshadi.gamerfinder.ui.game.MainGamesActivity;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;


    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

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



    }


    private boolean validateEmail() {
        String emailInput = mTextInputLayoutEmail.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            Toast.makeText(this, "Please enter email",Toast.LENGTH_SHORT).show();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            Toast.makeText(this, "Please enter a valid email address",Toast.LENGTH_SHORT).show();
            return false;
        } else {
            // Adding this to see how we login in main Game!
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), MainGamesActivity.class));
                }
            });
            return true;
        }
    }


    private boolean validatePassword() {
        String passwordInput = mTextInputLayoutPassword.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()) {
            Toast.makeText(this, "Please enter password",Toast.LENGTH_SHORT).show();
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            Toast.makeText(this, "Please enter a valid password",Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    public void confirmInput(View v) {
        if (!validateEmail() | !validatePassword()) {
            return;
        }

        String input = "Email: " + mTextInputLayoutEmail.getEditText().getText().toString();
        input += "\n";
        input += "Password: " + mTextInputLayoutPassword.getEditText().getText().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }

    public void signUpButton(View v){
        startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
    }

    public void forgotPasswordButton(View v){
        startActivity(new Intent(getApplicationContext(), ConfirmEmailActivity.class));
    }

}
