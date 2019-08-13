package com.orelandshadi.gamerfinder.ui.login;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.orelandshadi.gamerfinder.models.PasswordDialog;
import com.orelandshadi.gamerfinder.utils.StringUtils;
import com.orelandshadi.gamerfinder.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

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

        loginButton = (Button) findViewById(R.id.btn_Login);
        mTextInputLayoutEmail = (TextInputLayout) findViewById(R.id.text_input_email);
        mTextInputLayoutPassword = (TextInputLayout) findViewById(R.id.text_input_password);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmInput(v);
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
        } else {
            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "http://10.0.2.2:44362/userHandle/all";

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            Log.d("@@@ Respone", "Response is: " + response.substring(0, 500));
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("@@@ Respone", "That didn't work!");
                }
            });

// Add the request to the RequestQueue.
            queue.add(stringRequest);

//            final RequestQueue queue = Volley.newRequestQueue(this);
//            String URL = "https://www.google.com";
//
//            String emailInput = mTextInputLayoutEmail.getEditText().getText().toString().trim();
//            String passwordInput = mTextInputLayoutPassword.getEditText().getText().toString().trim();
//            Log.d("@@@ EMAIL", emailInput);
//            Log.d("@@@ PASS", passwordInput);
//            StringRequest request = new StringRequest(Request.Method.POST, URL,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            try {
//                                JSONObject jsonObj = new JSONObject(response);
//                                Log.d("@@@ respone:", "Good!");
//                                // parse response
//                            } catch (JSONException e) {
//                                Log.d("@@@ getParams", "in Json Exception ...");
//                                e.printStackTrace();
//                                queue.stop();
//                            }
//                        }
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Log.d("@@@ getParams", "in error ...");
//                            NetworkResponse response = error.networkResponse;
//                            if (response != null && response.data != null) {
//                                String errorString = new String(response.data);
//                                Log.d("@@@ respone:", errorString);
//                                queue.stop();
//                            }
//                        }
//                    }
//            ) {
//
//                @Override
//                public Map<String, String> getHeaders() {
//                    HashMap<String, String> headers = new HashMap<>();
//                    headers.put("Accept", "application/json");
//                    //headers.put("Authorization", "Basic " + myToken);
//                    return headers;
//                }
//
//                @Override
//                protected Map<String, String> getParams() {
//                    Map<String, String> params = new HashMap<>();
//                    Log.d("@@@ getParams", "getParams");
//                    params.put("email", mTextInputLayoutEmail.getEditText().getText().toString());
//                    params.put("password", mTextInputLayoutPassword.getEditText().getText().toString());
//                    Log.d("@@@ getParams", "wait ...");
//                    return params;
//                }
//            };
//            queue.add(request);


//            String emailInput = mTextInputLayoutEmail.getEditText().getText().toString().trim();
//            String passwordInput = mTextInputLayoutPassword.getEditText().getText().toString().trim();
//
//            Log.d("@@@ EMAIL", emailInput);
//            Log.d("@@@ PASS", passwordInput);
//
//            final RequestQueue queue = Volley.newRequestQueue(this);
//            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            // response
//                            Log.d("@@@ Response", response);
//                            queue.stop();
//                        }
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            // error
//                            Log.d("@@@ Error.Response", error.toString());
//                            error.printStackTrace();
//                            queue.stop();
//                        }
//                    }
//            ) {
//                @Override
//                protected Map<String, String> getParams() {
//                    Log.d("@@@ getParams", "getParams");
//                    Map<String, String> params = new HashMap<String, String>();
//                    params.put("name", "Alif");
//                    params.put("domain", "http://itsalif.info");
//
//                    return params;
//                }
//            };
//            postRequest.setRetryPolicy(new DefaultRetryPolicy(
//                    10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
//            ));
//            queue.add(postRequest);
        }

    }

    public void signUpButton(View v) {
        startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
    }

    public void forgotPasswordButton(View v) {
        startActivity(new Intent(getApplicationContext(), ConfirmEmailActivity.class));
    }

}
