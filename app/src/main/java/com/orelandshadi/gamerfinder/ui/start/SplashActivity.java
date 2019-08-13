package com.orelandshadi.gamerfinder.ui.start;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.orelandshadi.gamerfinder.R;
import com.orelandshadi.gamerfinder.models.SessionData;
import com.orelandshadi.gamerfinder.models.UserData;
import com.orelandshadi.gamerfinder.ui.userprofile.MainGamesActivity;
import com.orelandshadi.gamerfinder.ui.login.LoginActivity;


public class SplashActivity extends AppCompatActivity {

    private TextView tv;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        tv = (TextView) findViewById(R.id.tv);
        iv = (ImageView) findViewById(R.id.iv);

        Animation anim = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        tv.startAnimation(anim);
        iv.startAnimation(anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                UserData userData = SessionData.sharedInstance().getUserData();
                boolean didUserCompleteRegistration = false;
                if(userData != null) {
                    didUserCompleteRegistration = userData.isDidUserCompleteRegistration();
                }
                if(didUserCompleteRegistration) {
                    intent = new Intent(SplashActivity.this, MainGamesActivity.class);
                } else {
                    intent = new Intent(SplashActivity.this, LoginActivity.class);
                }

                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}
