package com.orelandshadi.gamerfinder.ui.login;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    private ArrayList<Integer> mFavoritePlatforms = new ArrayList<>();

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
                UserData.UserPlatforms platformType = UserData.UserPlatforms.Xbox;
                int platformTypeId = platformType.getValue();
                if (xbox.isSelected()) {
                    mFavoritePlatforms.add(platformTypeId);
                    //Toast.makeText(FavoritePlatformActivity.this,"You select Xbox One",Toast.LENGTH_SHORT).show();
                } else {
                    mFavoritePlatforms.remove(platformTypeId);
                }
            }
        });

        ps4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ps4.setSelected(!ps4.isSelected());
                UserData.UserPlatforms platformType = UserData.UserPlatforms.PS4;
                int platformTypeId = platformType.getValue(); // productTypeId = 3
                if (ps4.isSelected()) {
                    mFavoritePlatforms.add(platformTypeId);
                    //Toast.makeText(FavoritePlatformActivity.this,"You select PS4",Toast.LENGTH_SHORT).show();
                } else {
                    mFavoritePlatforms.remove(platformTypeId);
                }
            }
        });

        pc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pc.setSelected(!pc.isSelected());
                UserData.UserPlatforms platformType = UserData.UserPlatforms.PC;
                int platformTypeId = platformType.getValue(); // productTypeId = 3
                if (pc.isSelected()) {
                    mFavoritePlatforms.add(platformTypeId);
                    //Toast.makeText(FavoritePlatformActivity.this,"You select PC",Toast.LENGTH_SHORT).show();
                } else {
                    mFavoritePlatforms.remove(platformTypeId);
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFavoritePlatforms.size() > 0) {
                    for (int i = 0; i < mFavoritePlatforms.size(); i++) {
                        Log.d("@@@ platform ", mFavoritePlatforms.toString());
                    }

                    SessionData.sharedInstance().getUserData().setFavoritePlatforms(mFavoritePlatforms);
                    Intent intent = new Intent(FavoritePlatformActivity.this, FavoriteGamesActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(FavoritePlatformActivity.this, "Please select your favorite platforms.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
