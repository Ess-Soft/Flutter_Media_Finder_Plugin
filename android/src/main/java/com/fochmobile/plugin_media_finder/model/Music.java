package com.fochmobile.plugin_media_finder.model;

import java.util.HashMap;

public class Music {
    // Properties
    private long id;
    private String artist;
    private String title;
    private String album;
    private long albumId;
    private long duration;
    private String uri;
    private String albumArt;

    // Empty Constructor
    public Music(){}

    // Constructor
    public Music(long id, String artist, String title, String album, long albumId, long duration, String uri, String albumArt) {
        this.id = id;
        this.artist = artist;
        this.title = title;
        this.album = album;
        this.albumId = albumId;
        this.duration = duration;
        this.uri = uri;
        this.albumArt = albumArt;
    }

    // Getters & Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getAlbumArt() {
        return albumArt;
    }

    public void setAlbumArt(String albumArt) {
        this.albumArt = albumArt;
    }

    HashMap<String,Object> toMap(){
        HashMap<String,Object> songsMap = new HashMap<>();
        songsMap.put("id", id);
        songsMap.put("artist",artist);
        songsMap.put("title",title);
        songsMap.put("album",album);
        songsMap.put("albumId",albumId);
        songsMap.put("duration", duration);
        songsMap.put("uri",uri);
        songsMap.put("albumArt",albumArt);
        return songsMap;
    }
}
