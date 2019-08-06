package com.orelandshadi.gamerfinder.ui.login;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.Max;
import com.mobsandgeeks.saripaar.annotation.Min;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.orelandshadi.gamerfinder.R;
import com.orelandshadi.gamerfinder.models.SessionData;
import com.orelandshadi.gamerfinder.models.UserData;

import java.util.List;

public class DetailsActivity extends AppCompatActivity implements Validator.ValidationListener {


    // All this variables are NULL
    @NotEmpty
    @Length(min = 3, max = 15)
    private EditText userNameEditText;
    @Min(3)
    @Max(120)
    private EditText ageEditText;

    private EditText countryEditText;
    private EditText aboutEditText;
    private Button nextButton;
    private Button backButton;
    private RadioGroup radioGenderGroup;

    private Validator validator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // We calling this function for fill the variables
        initViews();
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    // Function for fill the variables
    private void initViews() {
        userNameEditText = (EditText) findViewById(R.id.UserName);
        ageEditText = (EditText) findViewById(R.id.Age);
        countryEditText = (EditText) findViewById(R.id.Country);
        aboutEditText = (EditText) findViewById(R.id.About);
        radioGenderGroup = (RadioGroup) findViewById(R.id.genderId);
        backButton = (Button) findViewById(R.id.backId);

        nextButton = (Button) findViewById(R.id.nextId);


        // When you click on NEXT button it will saved all the data that you filling
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
                String username = userNameEditText.getText().toString();
                if (username.equalsIgnoreCase("pmk")) {
                    userNameEditText.setError(getText(R.string.username_already_exists));
                }
                // We call the function [checkUserDetails] to check all the details that was inserted.
                // If return TRUE then will saved all the details that been inserted.
                if (checkUserDetails()) {
                    SessionData.sharedInstance().getUserData().setUsername(userNameEditText.getText().toString());
                    SessionData.sharedInstance().getUserData().setCountry(countryEditText.getText().toString());
                    SessionData.sharedInstance().getUserData().setAbout(aboutEditText.getText().toString());
                    SessionData.sharedInstance().getUserData().setAge(Integer.parseInt(ageEditText.getText().toString()));
                    boolean isMale = radioGenderGroup.getCheckedRadioButtonId() == R.id.rd_male;
                    SessionData.sharedInstance().getUserData().setGender(isMale ? UserData.UserGender.Male : UserData.UserGender.Female);
                    startActivity(new Intent(getApplicationContext(), FavoritePlatformActivity.class));
                } else { // When you miss something , will show a message that you need to complete your details.
                    Toast.makeText(getApplicationContext(), "Please enter your details", Toast.LENGTH_LONG).show();
                }
            }
        });

        //  When you click on BACK button it will return to the first page
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    // For checking all the details that was inserted.
    private boolean checkUserDetails() {
        boolean status = true;
        if (userNameEditText.getText().toString() == null || userNameEditText.getText().toString().isEmpty()) {
            status = false;
        }

        if (countryEditText.getText().toString() == null || countryEditText.getText().toString().isEmpty()) {
            status = false;
        }

        if (ageEditText.getText().toString() != null || !ageEditText.getText().toString().isEmpty()) {
            try {
                Integer age = Integer.parseInt(ageEditText.getText().toString());
                if (age < 8) {
                    status = false;
                    ageEditText.setError("Please enter an age above 8");
                } else if (age > 120) {
                    status = false;
                    ageEditText.setError("Please enter an age below 120");
                }
            } catch (Exception ex) {
                ageEditText.setError("Please enter valid age between 8-120");
            }
        } else {
            status = false;
        }
        return status;
    }

    @Override
    public void onValidationSucceeded() {
        startActivity(new Intent(getApplicationContext(), FavoritePlatformActivity.class));
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            // Display error messages
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
