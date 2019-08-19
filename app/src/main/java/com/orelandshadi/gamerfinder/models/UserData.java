package com.orelandshadi.gamerfinder.models;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserData {

//    public enum UserGender {
//        Male, Female
//    }

    public enum UserPlatforms {
        Xbox(1), PS4(2), PC(3);

        private int value;
        private static Map map = new HashMap<>();

        private UserPlatforms(int value) {
            this.value = value;
        }

        static {
            for (UserPlatforms pageType : UserPlatforms.values()) {
                map.put(pageType.value, pageType);
            }
        }

        public static UserPlatforms valueOf(int pageType) {
            return (UserPlatforms) map.get(pageType);
        }

        public int getValue() {
            return value;
        }

    }

    private String mEmail;
    private String mPassword;
    private String mUsername;
    private String mGender;
    private boolean mHasMicrophone;
    private Integer mAge;
    private String mCountry;
    private String mAbout;
    private ArrayList<Integer> mFavoritePlatforms;
    private ArrayList<Integer> mFavoriteGames;
    private boolean didUserCompleteRegistration;

    public UserData() {

    }

    public UserData(String email, String password) {
        mEmail = email;
        mPassword = password;
    }

    public UserData(String email, String password, String username, String gender, Integer age, boolean hasMicrophone, String country, String about) {
        mEmail = email;
        mPassword = password;
        mUsername = username;
        mGender = gender;
        mAge = age;
        mCountry = country;
        mAbout = about;
        mHasMicrophone = hasMicrophone;
    }

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

    public ArrayList<Integer> getFavoritePlatforms() {
        return mFavoritePlatforms;
    }

    public ArrayList<Integer> getFavoriteGames() {
        return mFavoriteGames;
    }

    public void setFavoriteGames(ArrayList<Integer> gameFav) {
        mFavoriteGames = gameFav;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
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

    public boolean getHasMicrophone() {
        return mHasMicrophone;
    }

    public void setHasMicrophone(boolean hasMicrophone) {
        mHasMicrophone = hasMicrophone;
    }

    public void setFavoritePlatforms(ArrayList<Integer> favoritePlatforms) {
        mFavoritePlatforms = favoritePlatforms;
    }

    public boolean isDidUserCompleteRegistration() {
        return didUserCompleteRegistration;
    }

    public void setDidUserCompleteRegistration(boolean didUserCompleteRegistration) {
        this.didUserCompleteRegistration = didUserCompleteRegistration;
    }
}
