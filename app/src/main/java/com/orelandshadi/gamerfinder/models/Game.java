package com.orelandshadi.gamerfinder.models;

import android.view.View;

public class Game {

    private int ID;
    private String Title;
    private int Thumbnail;
    private boolean isSelected = false;

    public Game() {
    }

    public Game(int id, String title, int thumbnail) {
        ID = id;
        Title = title;
        Thumbnail = thumbnail;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public String toString() {
        return "Game{" +
                "ID=" + ID +
                ", Title='" + Title + '\'' +
                ", Thumbnail=" + Thumbnail +
                ", isSelected=" + isSelected +
                '}';
    }

}

