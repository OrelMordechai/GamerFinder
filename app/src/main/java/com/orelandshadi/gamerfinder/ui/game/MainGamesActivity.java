package com.orelandshadi.gamerfinder.ui.game;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.orelandshadi.gamerfinder.R;

public class MainGamesActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private Button fifaButton;
    private Button needForSpeedButton;
    private Button callofdutyButton;
    private Button starwarsButton;
    private Button mortalkombatButton;
    private Button dragonballButton;
    private Button justcause2Button;
    private Button assiasinButton;
    private Button carsButton;

    private Button addFriendButton;
    private Button homeButton;
    private Button searchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_games);
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

//        menuButton = (Button) findViewById(R.id.showpopup);
        addFriendButton = (Button) findViewById(R.id.addFriendId);
        homeButton = (Button) findViewById(R.id.homeId);
        searchButton = (Button) findViewById(R.id.searchId);





        fifaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainGamesActivity.this, ChatRoomActivity.class);
                startActivity(intent);
            }
        });

        needForSpeedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainGamesActivity.this, ChatRoomActivity.class);
                startActivity(intent);
            }
        });

        callofdutyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainGamesActivity.this, ChatRoomActivity.class);
                startActivity(intent);
            }
        });


        starwarsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainGamesActivity.this, ChatRoomActivity.class);
                startActivity(intent);
            }
        });

        mortalkombatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainGamesActivity.this, ChatRoomActivity.class);
                startActivity(intent);
            }
        });

        dragonballButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainGamesActivity.this, ChatRoomActivity.class);
                startActivity(intent);
            }
        });

        carsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainGamesActivity.this, ChatRoomActivity.class);
                startActivity(intent);
            }
        });

        assiasinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainGamesActivity.this, ChatRoomActivity.class);
                startActivity(intent);
            }
        });

        justcause2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainGamesActivity.this, ChatRoomActivity.class);
                startActivity(intent);
            }
        });


        addFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainGamesActivity.this, FriendsActivity.class);
                startActivity(intent);
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainGamesActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainGamesActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }


    // show Popup they are in Folder [1-menu 2-popup_menu.xml]
    public void showPopup(View v){
        PopupMenu popup  = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }

    // On click in item popup
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profileItem:
                Intent intent = new Intent(MainGamesActivity.this, ProfileActivity.class);
                startActivity(intent);
                Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.addFriendItem:
                Intent intent1 = new Intent(MainGamesActivity.this, AddFriendsActivity.class);
                startActivity(intent1);
                Toast.makeText(this, "Add friend clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.settingsItem:
                Intent intent2 = new Intent(MainGamesActivity.this, SettingsActivity.class);
                startActivity(intent2);
                Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }



}
