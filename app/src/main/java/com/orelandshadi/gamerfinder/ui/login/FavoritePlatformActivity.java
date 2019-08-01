package com.orelandshadi.gamerfinder.ui.login;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.orelandshadi.gamerfinder.R;
import com.orelandshadi.gamerfinder.models.SessionData;
import com.orelandshadi.gamerfinder.models.UserData;

import java.util.ArrayList;

public class FavoritePlatformActivity extends AppCompatActivity {

    private ImageButton xbox;
    private ImageButton ps4;
    private ImageButton pc;
    private Button nextButton;
    private Button backButton;

    private ArrayList<UserData.UserDevice> mDevices = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_platform);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        xbox = (ImageButton) findViewById(R.id.xbox);
        ps4 = (ImageButton) findViewById(R.id.ps4);
        pc = (ImageButton) findViewById(R.id.pc);
        nextButton = (Button) findViewById(R.id.nextId);
        backButton = (Button) findViewById(R.id.backId);

        xbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xbox.setSelected(!xbox.isSelected());
                if(xbox.isSelected()) {
                    mDevices.add(UserData.UserDevice.Xbox);
                    Toast.makeText(FavoritePlatformActivity.this,"You select Xbox One",Toast.LENGTH_SHORT).show();
                } else {
                    mDevices.remove(UserData.UserDevice.Xbox);
                }

            }
        });

        ps4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ps4.setSelected(!ps4.isSelected());
                if(ps4.isSelected()) {
                    mDevices.add(UserData.UserDevice.PS4);
                    Toast.makeText(FavoritePlatformActivity.this,"You select PS4",Toast.LENGTH_SHORT).show();
                } else {
                    mDevices.remove(UserData.UserDevice.PS4);
                }
            }
        });

        pc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pc.setSelected(!pc.isSelected());
                if(pc.isSelected()) {
                    mDevices.add(UserData.UserDevice.PC);
                    Toast.makeText(FavoritePlatformActivity.this,"You select PC",Toast.LENGTH_SHORT).show();
                } else {
                    mDevices.remove(UserData.UserDevice.PC);
                }

            }
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDevices.size() > 0) {
                    SessionData.sharedInstance().getUserData().setDevice(mDevices);
                    Intent intent = new Intent(FavoritePlatformActivity.this, FavoriteGamesActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(FavoritePlatformActivity.this, "Please select your devices.",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}
