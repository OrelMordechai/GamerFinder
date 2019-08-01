package com.orelandshadi.gamerfinder.models;


import java.util.ArrayList;

public class UserData {

    public enum UserGender {
        Male, Female
    }

    public enum UserDevice{
        Xbox, PS4, PC
    }

    public enum FavoriteGame{
        Fifa, Need4speed, Callofduty, Starwars, Mortalkombat, Dragonball, Justcause2, Assiasin, Cars

        }

    private String mEmail;
    private String mPassword;
    private String mUsername;
    private UserGender mGender;
    private Integer mAge;
    private String mCountry;
    private String mAbout;
    private ArrayList<UserDevice> mDevices;
    private ArrayList<FavoriteGame> mfavoriteGame;
    private boolean didUserCompleteRegistration;

    public UserData() {

    }


    public UserData(String email, String password) {
        mEmail = email;
        mPassword = password;
    }

//
//    public UserData(String mUsername, UserGender mGender, Integer mAge, String mCountry, String mAbout, UserDevice mDevice) {
//        this.mUsername = mUsername;
//        this.mGender = mGender;
//        this.mAge = mAge;
//        this.mCountry = mCountry;
//        this.mAbout = mAbout;
//        this.mDevice = mDevice;
//    }



    public String getAbout() {
        return mAbout;
    }

    public void setAbout(String about) {
        mAbout = about;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }


    public Integer getAge() {
        return mAge;
    }

    public void setAge(Integer age) {
        mAge = age;
    }

    public  ArrayList<UserDevice> getDevices() {
        return mDevices;
    }

    public  ArrayList<FavoriteGame> getMfavoriteGame() {
        return mfavoriteGame;
    }

    public void setMfavoriteGame( ArrayList<FavoriteGame> gameFav) {
        mfavoriteGame = gameFav;
    }

    public void setDevice( ArrayList<UserDevice> devices) {
        mDevices = devices;
    }

    public UserGender getGender() {
        return mGender;
    }

    public void setGender(UserGender gender) {
        mGender = gender;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public boolean isDidUserCompleteRegistration() {
        return didUserCompleteRegistration;
    }

    public void setDidUserCompleteRegistration(boolean didUserCompleteRegistration) {
        this.didUserCompleteRegistration = didUserCompleteRegistration;
    }
}
