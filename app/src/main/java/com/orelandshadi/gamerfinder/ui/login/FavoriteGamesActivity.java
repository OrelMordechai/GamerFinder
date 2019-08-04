package com.orelandshadi.gamerfinder.ui.login;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.orelandshadi.gamerfinder.R;
import com.orelandshadi.gamerfinder.models.Game;
import com.orelandshadi.gamerfinder.models.RecyclerViewAdapter;
import com.orelandshadi.gamerfinder.models.SessionData;
import com.orelandshadi.gamerfinder.models.UserData;
import com.orelandshadi.gamerfinder.ui.userprofile.MainGamesActivity;

import java.util.ArrayList;
import java.util.List;

public class FavoriteGamesActivity extends AppCompatActivity {

    public ArrayList<UserData.FavoriteGame> mFavoriteGames = new ArrayList<>();

    private Button doneButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_games);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        List<Game> mListOfGame = new ArrayList<>();
        mListOfGame = getData();

        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, mListOfGame);
        myrv.setLayoutManager(new GridLayoutManager(this, 3));
        myrv.setAdapter(myAdapter);


        doneButton = (Button) findViewById(R.id.doneId);
        backButton = (Button) findViewById(R.id.backId);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFavoriteGames.size() > 0) {
                    SessionData.sharedInstance().getUserData().setDidUserCompleteRegistration(true);
                    SessionData.sharedInstance().getUserData().setMfavoriteGame(mFavoriteGames);
                    Intent intent = new Intent(FavoriteGamesActivity.this, MainGamesActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(FavoriteGamesActivity.this, "Please select your Favorite Games.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private List<Game> getData() {
        List<Game> mListOfGame = new ArrayList<>();
        mListOfGame.add(new Game("Apex Legends", R.drawable.apexlegends));
        mListOfGame.add(new Game("Fortnite", R.drawable.fortnite));
        mListOfGame.add(new Game("Call Of Duty Black ops 4", R.drawable.callofdutyblackops4));
        mListOfGame.add(new Game("Rainbow six Siege", R.drawable.rainbowsixsiege));
        mListOfGame.add(new Game("The Division 2", R.drawable.thedivision2));
        mListOfGame.add(new Game("Playerunknown's Battlegrounds", R.drawable.playerunknownbattlegrounds));
        mListOfGame.add(new Game("Black Desert Online", R.drawable.blackdesertonline));
        mListOfGame.add(new Game("League of Legends", R.drawable.leagueoflegends));
        mListOfGame.add(new Game("World of Warcraft", R.drawable.warcraft));
        mListOfGame.add(new Game("Destiny 2", R.drawable.destiny2));
        mListOfGame.add(new Game("Battlefield V", R.drawable.battlefieldv));
        mListOfGame.add(new Game("Dota 2", R.drawable.dota2));
        mListOfGame.add(new Game("Grand Theft Auto V", R.drawable.gtav));
        mListOfGame.add(new Game("FIFA 19", R.drawable.fifa19));
        mListOfGame.add(new Game("Mortal Kombat 11", R.drawable.mk11));

        return mListOfGame;
    }

}
