package com.orelandshadi.gamerfinder.models;


import com.orelandshadi.gamerfinder.R;

import java.util.ArrayList;

public class UserData {

    public enum UserGender {
        Male, Female
    }

    public enum UserDevice{
        Xbox, PS4, PC
    }

//    public enum FavoriteGame{
//        Apex_Legends("Apex Legends", R.drawable.apexlegends),
//        Fortnite("Fortnite", R.drawable.fortnite),
//        Call_Of_Duty_Black_ops_4("Call Of Duty Black ops 4", R.drawable.callofdutyblackops4),
//        Rainbow_six_Siege("Rainbow six Siege", R.drawable.rainbowsixsiege),
//        The_Division_2("The Division 2", R.drawable.thedivision2),
//        Playerunknowns_Battlegrounds("Playerunknown's Battlegrounds", R.drawable.playerunknownbattlegrounds),
//        Black_Desert_Online("Black Desert Online", R.drawable.blackdesertonline),
//        League_of_Legends("League of Legends", R.drawable.leagueoflegends),
//        World_of_Warcraft("World of Warcraft", R.drawable.warcraft),
//        Destiny_2("Destiny 2", R.drawable.destiny2),
//        Battlefield_V("Battlefield V", R.drawable.battlefieldv),
//        Dota_2("Dota 2", R.drawable.dota2),
//        Grand_Theft_Auto_V("Grand Theft Auto V", R.drawable.gtav),
//        FIFA_19("FIFA 19", R.drawable.fifa19),
//        Mortal_Kombat_11("Mortal Kombat 11", R.drawable.mk11);
//
//        private String Title;
//        private int idImg;
//
//        FavoriteGame(String title, int idImg) {
//            Title = title;
//            this.idImg = idImg;
//        }
//
//        public String getTitle() {
//            return Title;
//        }
//
//        public int getIdImg() {
//            return idImg;
//        }
//    }

    private String mEmail;
    private String mPassword;
    private String mUsername;
    private UserGender mGender;
    private Integer mAge;
    private String mCountry;
    private String mAbout;
    private ArrayList<UserDevice> mDevices;
    private ArrayList<Game> mfavoriteGame;
    private boolean didUserCompleteRegistration;

    public UserData() {

    }

    public UserData(String email, String password) {
        mEmail = email;
        mPassword = password;
    }

    public UserData(String email, String password, String username, UserGender gender, Integer age, String country, String about) {
        mEmail = email;
        mPassword = password;
        mUsername = username;
        mGender = gender;
        mAge = age;
        mCountry = country;
        mAbout = about;
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

    public  ArrayList<Game> getMfavoriteGame() {
        return mfavoriteGame;
    }

    public void setMfavoriteGame( ArrayList<Game> gameFav) {
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
