package com.orelandshadi.gamerfinder.ui.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.orelandshadi.gamerfinder.R;

public class ConfirmEmailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_email);
    }

    public void btn_send(View view) {
        Toast.makeText(getApplicationContext(), "The code send to email", Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "Please verify to continue", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),ConfirmPasswordActivity.class));
        finish();
    }

}