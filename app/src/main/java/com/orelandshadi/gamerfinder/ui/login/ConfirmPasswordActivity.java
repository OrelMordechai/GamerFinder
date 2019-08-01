package com.orelandshadi.gamerfinder.ui.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.orelandshadi.gamerfinder.R;
import com.orelandshadi.gamerfinder.ui.login.LoginActivity;

public class ConfirmPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_password);
    }

    public void btn_restore(View view) {
        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }



}
