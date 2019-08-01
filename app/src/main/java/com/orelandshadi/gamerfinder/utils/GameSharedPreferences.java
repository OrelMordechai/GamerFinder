package com.orelandshadi.gamerfinder.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.orelandshadi.gamerfinder.models.UserData;

public class GameSharedPreferences {

    private SharedPreferences pref;

    private GameSharedPreferences() {

    }

    public GameSharedPreferences(Context context) {
        pref = context.getSharedPreferences("GameSharedPreferences", 0);
    }

    private SharedPreferences.Editor editor() {
        return pref.edit();
    }

    public void saveUserData(UserData userData) {
        Gson gson = new Gson();
        String json = gson.toJson(userData);
        SharedPreferences.Editor editor = editor();
        editor.putString("UserData", json);
        editor.commit();
    }

    public UserData getUserData() {
        SharedPreferences.Editor editor = editor();
        Gson gson = new Gson();
        String json = pref.getString("UserData", "");
        UserData obj = gson.fromJson(json, UserData.class);
        return obj;
    }

    public void remove(String key) {
        SharedPreferences.Editor editor = editor();
        editor.remove(key);
        editor.commit();
    }

    public void clear() {
        SharedPreferences.Editor editor = editor();
        editor.clear();
        editor.commit();
    }

}
