package com.orelandshadi.gamerfinder.ui.app;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import com.orelandshadi.gamerfinder.models.SessionData;
import com.orelandshadi.gamerfinder.models.UserData;
import com.orelandshadi.gamerfinder.utils.GameSharedPreferences;

@SuppressLint("Registered")
public class GamerFinderApp extends Application {

    private static GameSharedPreferences sharedPrefs;
    @Override
    public void onCreate() {
        super.onCreate();

        sharedPrefs = new GameSharedPreferences(getApplicationContext());
        UserData userData = sharedPrefs.getUserData();
        SessionData.sharedInstance().setUserData(userData);

        if(userData != null) {
            Log.d("User Data", userData.toString());
        }
    }

    public static GameSharedPreferences sharedPrefs() {
        return sharedPrefs;
    }
}
