package com.orelandshadi.gamerfinder.models;

import android.view.View;

public class Game {

    private String Title;
    private int Thumbnail ;
    private boolean isSelected =false;

    public Game() {
    }

    public Game(String title,int thumbnail) {
        Title = title;
        Thumbnail = thumbnail;
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
                "Title='" + Title + '\'' +
                ", Thumbnail=" + Thumbnail +
                '}';
    }
}

