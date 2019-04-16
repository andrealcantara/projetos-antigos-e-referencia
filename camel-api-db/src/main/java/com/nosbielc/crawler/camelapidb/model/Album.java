package com.nosbielc.crawler.camelapidb.model;

import java.util.List;

public class Album {

    private String artist;
    private String title;
    private List<Track> tracks;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}