package com.orelandshadi.gamerfinder.models;

public class SessionData {
    private static final SessionData ourInstance = new SessionData();

    static SessionData getInstance() {
        return ourInstance;
    }

    private UserData userData;

    private SessionData() {

    }

    public static SessionData sharedInstance() {
        return ourInstance;
    }


    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
