package com.orelandshadi.gamerfinder.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.orelandshadi.gamerfinder.R;
import com.orelandshadi.gamerfinder.models.Game;
import com.orelandshadi.gamerfinder.models.RecyclerViewAdapter;
import com.orelandshadi.gamerfinder.models.SessionData;
import com.orelandshadi.gamerfinder.models.UserData;
import com.orelandshadi.gamerfinder.ui.userprofile.MainGamesActivity;
import com.orelandshadi.gamerfinder.utils.HttpRequest;
import com.orelandshadi.gamerfinder.utils.HttpResponseCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FavoriteGamesActivity extends AppCompatActivity implements HttpResponseCallback {

    private Button doneButton;
    private Button backButton;
    private ArrayList<Integer> mFavoriteGame = new ArrayList<>();
    private FavoriteGamesActivity self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_games);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        List<Game> mListOfGame = getData();

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_id);
        final RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, mListOfGame, mFavoriteGame);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setAdapter(myAdapter);

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

//                for (int i = 0; i < mFavoriteGame.size(); i++) {
//                    Log.d("index: " + i, mFavoriteGame.get(i).toString());
//                }

                UserData userData = SessionData.sharedInstance().getUserData();

                if (mFavoriteGame.size() > 0) {
                    for (int i = 0; i < mFavoriteGame.size(); i++) {
                        Log.d("@@@ device", mFavoriteGame.toString());
                    }
                    userData.setDidUserCompleteRegistration(true);
                    userData.setFavoriteGames(mFavoriteGame);

                     /*
                        @@@@@@@@@@@@@@@@@@@@@@@@@@@@
                        SEND LOGIN REQUEST TO THE SERVER
                        @@@@@@@@@@@@            @@@@@@@@@

                     */
                    Context mContext = getApplicationContext();

                    try {
                        JSONObject jsonBodyObj = new JSONObject();

                        jsonBodyObj.put("UserName", userData.getUsername());
                        jsonBodyObj.put("Email", userData.getEmail());
                        jsonBodyObj.put("Password", userData.getPassword());
                        jsonBodyObj.put("Gender", userData.getGender());
                        jsonBodyObj.put("HasMicrophone", userData.getHasMicrophone());
                        jsonBodyObj.put("Age", userData.getAge());
                        jsonBodyObj.put("RoleName", "player");
                        jsonBodyObj.put("AboutMySelf", userData.getAbout());
                        jsonBodyObj.put("CountryName", userData.getCountry());
                        jsonBodyObj.put("UserImage", "test");

                        final JSONArray jsonBodyFavoriteGames = new JSONArray();
                        for (int id : userData.getFavoriteGames()) {
                            jsonBodyFavoriteGames.put(id);
                        }
                        jsonBodyObj.put("FavoriteGames", jsonBodyFavoriteGames);

                        JSONArray jsonBodyFavoritePlatforms = new JSONArray();
                        for (int id : userData.getFavoritePlatforms()) {
                            jsonBodyFavoritePlatforms.put(id);
                        }
                        jsonBodyObj.put("FavoritePlatforms", jsonBodyFavoritePlatforms);

                        HttpRequest httpRequest = new HttpRequest(mContext);
                        httpRequest.httpPostJsonRequest("signUp", jsonBodyObj, self);

                        Log.d("@@@ FavGameActivity", "after request call");
                        Log.d("@@@ jsonBodyObj", jsonBodyObj.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    Toast.makeText(FavoriteGamesActivity.this, "Please at least one favorite Game.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private List<Game> getData() {
        List<Game> mListOfGame = new ArrayList<>();
        mListOfGame.add(new Game(1, "Apex Legends", R.drawable.apexlegends));
        mListOfGame.add(new Game(2, "Fortnite", R.drawable.fortnite));
        mListOfGame.add(new Game(3, "Call Of Duty Black ops 4", R.drawable.callofdutyblackops4));
        mListOfGame.add(new Game(4, "Rainbow six Siege", R.drawable.rainbowsixsiege));
        mListOfGame.add(new Game(5, "The Division 2", R.drawable.thedivision2));
        mListOfGame.add(new Game(6, "Playerunknown's Battlegrounds", R.drawable.playerunknownbattlegrounds));
        mListOfGame.add(new Game(7, "Black Desert Online", R.drawable.blackdesertonline));
        mListOfGame.add(new Game(8, "League of Legends", R.drawable.leagueoflegends));
        mListOfGame.add(new Game(9, "World of Warcraft", R.drawable.warcraft));
        mListOfGame.add(new Game(10, "Destiny 2", R.drawable.destiny2));
        mListOfGame.add(new Game(11, "Battlefield V", R.drawable.battlefieldv));
        mListOfGame.add(new Game(12, "Dota 2", R.drawable.dota2));
        mListOfGame.add(new Game(13, "Grand Theft Auto V", R.drawable.gtav));
        mListOfGame.add(new Game(14, "FIFA 19", R.drawable.fifa19));
        mListOfGame.add(new Game(15, "Mortal Kombat 11", R.drawable.mk11));
        return mListOfGame;
    }

    @Override
    public void onSuccessResponse(String result) {
        Log.d("@@@ FavoriteGActivity", "onSuccessResponse: " + result);
        Intent intent = new Intent(FavoriteGamesActivity.this, MainGamesActivity.class);
        startActivity(intent);
    }

    @Override
    public void onErrorResponse(String result) {
        Log.d("@@@ FavoriteGActivity", "onErrorResponse: " + result);
    }

}
