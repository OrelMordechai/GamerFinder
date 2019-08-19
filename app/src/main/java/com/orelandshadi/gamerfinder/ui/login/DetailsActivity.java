package com.orelandshadi.gamerfinder.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.android.volley.toolbox.HttpResponse;
import com.mikelau.countrypickerx.Country;
import com.mikelau.countrypickerx.CountryPickerCallbacks;
import com.mikelau.countrypickerx.CountryPickerDialog;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.Max;
import com.mobsandgeeks.saripaar.annotation.Min;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.orelandshadi.gamerfinder.R;
import com.orelandshadi.gamerfinder.models.SessionData;
import com.orelandshadi.gamerfinder.models.UserData;
import com.orelandshadi.gamerfinder.utils.HttpRequest;
import com.orelandshadi.gamerfinder.utils.HttpResponseCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class DetailsActivity extends AppCompatActivity implements HttpResponseCallback {

    // All this variables are NULL
    private EditText userNameEditText;
    private EditText ageEditText;
    private Button countryButton;
    private EditText aboutEditText;
    private Button nextButton;
    private Button backButton;
    private RadioGroup radioGroupGender;
    private RadioGroup radioGroupHasMicrophone;
    private CountryPickerDialog countryPicker;
    private DetailsActivity self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // We calling this function for fill the variables
        initViews();

        countryPicker = new CountryPickerDialog(DetailsActivity.this, new CountryPickerCallbacks() {
            @Override
            public void onCountrySelected(Country country, int flagResId) {
                Log.d("@@@ Country", "country name: " + country.getCountryName(DetailsActivity.this) + ", flagResId: " + flagResId);
                countryButton.setText(country.getCountryName(DetailsActivity.this));
                countryPicker.dismiss();
            }
        }, false, 0);

        countryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countryPicker.show();
            }
        });

        // When you click on NEXT button it will saved all the data that you filling
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // We call the function [userDetailsAreValid] to check all the details that was inserted.
                if (userDetailsAreValid()) {
                    Context mContext = getApplicationContext();

                    final String userName = userNameEditText.getText().toString().trim();

                    Log.d("@@@ userName", userName);

                    JSONObject jsonBodyObj = new JSONObject();
                    try {
                        jsonBodyObj.put("UserName", userName);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    HttpRequest httpRequest = new HttpRequest(mContext);
                    httpRequest.httpPostJsonRequest("isUserNameExists", jsonBodyObj, self);
                    Log.d("@@@ line 95", "after request call");

                } else { // When you miss something , will show a message that you need to complete your details.
                    Toast.makeText(getApplicationContext(), "Please enter your details" , Toast.LENGTH_LONG).show();
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

    // Function for fill the variables
    private void initViews() {
        userNameEditText = (EditText) findViewById(R.id.UserName);
        ageEditText = (EditText) findViewById(R.id.Age);
        countryButton = (Button) findViewById(R.id.Country);
        aboutEditText = (EditText) findViewById(R.id.About);
        radioGroupGender = (RadioGroup) findViewById(R.id.genderId);
        radioGroupHasMicrophone = (RadioGroup) findViewById(R.id.hasMicID);
        backButton = (Button) findViewById(R.id.backId);
        nextButton = (Button) findViewById(R.id.nextId);
    }

    // For checking all the details that was inserted.
    private boolean userDetailsAreValid() {
        boolean status = true;
        if (userNameEditText.getText().toString() == null || userNameEditText.getText().toString().isEmpty()) {
            userNameEditText.setError("User name can't be empty");
            status = false;
        }

        if (userNameEditText.getText().toString().length() < 3 || userNameEditText.getText().toString().length() > 19) {
            userNameEditText.setError("User name length must be in range 3-19");
            status = false;
        }

        if (countryButton.getText().toString() == null || countryButton.getText().toString().isEmpty()) {
            Log.d("@@@ Country error",countryButton.getText().toString());
            countryButton.setError("Country can't be empty");
            status = false;
        } else{
            countryButton.setError(null);
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
    public void onSuccessResponse(String result) {
        Log.d("@@@ DetailsActivity", "onSuccessResponse: " + result);

        UserData userData = SessionData.sharedInstance().getUserData();
        userData.setUsername(userNameEditText.getText().toString());
        userData.setCountry(countryButton.getText().toString());
        userData.setAbout(aboutEditText.getText().toString());
        userData.setAge(Integer.parseInt(ageEditText.getText().toString()));

        RadioButton genderRadioButton = findViewById(radioGroupGender.getCheckedRadioButtonId());
        String gender = genderRadioButton.getText().toString();
        userData.setGender(gender);

        RadioButton hasMicrophoneRadioButton = findViewById(radioGroupHasMicrophone.getCheckedRadioButtonId());
        boolean hasMicrophone = hasMicrophoneRadioButton.getText().toString().equals("Yes") ? true : false;
        userData.setHasMicrophone(hasMicrophone);

        startActivity(new Intent(getApplicationContext(), FavoritePlatformActivity.class));
    }

    @Override
    public void onErrorResponse(String result) {
        Log.d("@@@ DetailsActivity", "onErrorResponse: " + result);
    }
}