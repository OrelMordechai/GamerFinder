package com.orelandshadi.gamerfinder.ui.login;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.orelandshadi.gamerfinder.R;
import com.orelandshadi.gamerfinder.models.SessionData;
import com.orelandshadi.gamerfinder.models.UserData;
import com.orelandshadi.gamerfinder.ui.game.MainGamesActivity;

import java.util.ArrayList;

public class FavoriteGamesActivity extends AppCompatActivity {

    private Button fifaButton;
    private Button needForSpeedButton;
    private Button callofdutyButton;
    private Button starwarsButton;
    private Button mortalkombatButton;
    private Button dragonballButton;
    private Button justcause2Button;
    private Button assiasinButton;
    private Button carsButton;

    private ArrayList<UserData.FavoriteGame> mfavoriteGame = new ArrayList<>();

    private Button doneButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_games);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        fifaButton = (Button) findViewById(R.id.game2);
        needForSpeedButton = (Button) findViewById(R.id.game6);
        callofdutyButton = (Button) findViewById(R.id.game4);
        starwarsButton = (Button) findViewById(R.id.game1);
        mortalkombatButton = (Button) findViewById(R.id.game9);

        dragonballButton = (Button) findViewById(R.id.game8);
        justcause2Button = (Button) findViewById(R.id.game3);
        assiasinButton = (Button) findViewById(R.id.game5);
        carsButton = (Button) findViewById(R.id.game7);

        doneButton = (Button) findViewById(R.id.doneId);
        backButton = (Button) findViewById(R.id.backId);


        fifaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fifaButton.setSelected(!fifaButton.isSelected());
                if(fifaButton.isSelected()) {
                    mfavoriteGame.add(UserData.FavoriteGame.Fifa);
                    Toast.makeText(FavoriteGamesActivity.this,"You select Fifa ",Toast.LENGTH_SHORT).show();
                } else {
                    mfavoriteGame.remove(UserData.FavoriteGame.Fifa);
                    Toast.makeText(FavoriteGamesActivity.this,"unselect Fifa ",Toast.LENGTH_SHORT).show();
                }

            }
        });

        needForSpeedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                needForSpeedButton.setSelected(!needForSpeedButton.isSelected());
                if(needForSpeedButton.isSelected()) {
                    mfavoriteGame.add(UserData.FavoriteGame.Need4speed);
                    Toast.makeText(FavoriteGamesActivity.this,"You select Need For Speed ",Toast.LENGTH_SHORT).show();
                } else {
                    mfavoriteGame.remove(UserData.FavoriteGame.Need4speed);
                    Toast.makeText(FavoriteGamesActivity.this,"unselect Need For Speed ",Toast.LENGTH_SHORT).show();
                }

            }
        });


        callofdutyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callofdutyButton.setSelected(!callofdutyButton.isSelected());
                if(callofdutyButton.isSelected()) {
                    mfavoriteGame.add(UserData.FavoriteGame.Callofduty);
                    Toast.makeText(FavoriteGamesActivity.this,"You select Call Of Duty ",Toast.LENGTH_SHORT).show();
                } else {
                    mfavoriteGame.remove(UserData.FavoriteGame.Callofduty);
                    Toast.makeText(FavoriteGamesActivity.this,"unselect Call Of Duty ",Toast.LENGTH_SHORT).show();
                }

            }
        });


        starwarsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starwarsButton.setSelected(!starwarsButton.isSelected());
                if(starwarsButton.isSelected()) {
                    mfavoriteGame.add(UserData.FavoriteGame.Starwars);
                    Toast.makeText(FavoriteGamesActivity.this,"You select Star Wars ",Toast.LENGTH_SHORT).show();
                } else {
                    mfavoriteGame.remove(UserData.FavoriteGame.Starwars);
                    Toast.makeText(FavoriteGamesActivity.this,"unselect Star Wars ",Toast.LENGTH_SHORT).show();
                }

            }
        });

        mortalkombatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mortalkombatButton.setSelected(!mortalkombatButton.isSelected());
                if(mortalkombatButton.isSelected()) {
                    mfavoriteGame.add(UserData.FavoriteGame.Mortalkombat);
                    Toast.makeText(FavoriteGamesActivity.this,"You select Mortal kombat ",Toast.LENGTH_SHORT).show();
                } else {
                    mfavoriteGame.remove(UserData.FavoriteGame.Mortalkombat);
                    Toast.makeText(FavoriteGamesActivity.this,"unselect Mortal kombat ",Toast.LENGTH_SHORT).show();
                }

            }
        });



        dragonballButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dragonballButton.setSelected(!dragonballButton.isSelected());
                if(dragonballButton.isSelected()) {
                    mfavoriteGame.add(UserData.FavoriteGame.Dragonball);
                    Toast.makeText(FavoriteGamesActivity.this,"You select Dragon Ball ",Toast.LENGTH_SHORT).show();
                } else {
                    mfavoriteGame.remove(UserData.FavoriteGame.Dragonball);
                    Toast.makeText(FavoriteGamesActivity.this,"unselect Dragon Ball ",Toast.LENGTH_SHORT).show();
                }

            }
        });

        justcause2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                justcause2Button.setSelected(!justcause2Button.isSelected());
                if(justcause2Button.isSelected()) {
                    mfavoriteGame.add(UserData.FavoriteGame.Justcause2);
                    Toast.makeText(FavoriteGamesActivity.this,"You select Just Cause 2 ",Toast.LENGTH_SHORT).show();
                } else {
                    mfavoriteGame.remove(UserData.FavoriteGame.Justcause2);
                    Toast.makeText(FavoriteGamesActivity.this,"unselect Just Cause 2 ",Toast.LENGTH_SHORT).show();
                }

            }
        });

        assiasinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assiasinButton.setSelected(!assiasinButton.isSelected());
                if(assiasinButton.isSelected()) {
                    mfavoriteGame.add(UserData.FavoriteGame.Assiasin);
                    Toast.makeText(FavoriteGamesActivity.this,"You select Assiasin ",Toast.LENGTH_SHORT).show();
                } else {
                    mfavoriteGame.remove(UserData.FavoriteGame.Assiasin);
                    Toast.makeText(FavoriteGamesActivity.this,"unselect Assiasin ",Toast.LENGTH_SHORT).show();
                }

            }
        });

        carsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carsButton.setSelected(!carsButton.isSelected());
                if(carsButton.isSelected()) {
                    mfavoriteGame.add(UserData.FavoriteGame.Cars);
                    Toast.makeText(FavoriteGamesActivity.this,"You select Cars ",Toast.LENGTH_SHORT).show();
                } else {
                    mfavoriteGame.remove(UserData.FavoriteGame.Cars);
                    Toast.makeText(FavoriteGamesActivity.this,"unselect Cars ",Toast.LENGTH_SHORT).show();
                }

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mfavoriteGame.size() > 0) {
                    SessionData.sharedInstance().getUserData().setDidUserCompleteRegistration(true);
                    SessionData.sharedInstance().getUserData().setMfavoriteGame(mfavoriteGame);
                    Intent intent = new Intent(FavoriteGamesActivity.this, MainGamesActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(FavoriteGamesActivity.this, "Please select your Favorite Games.",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}
