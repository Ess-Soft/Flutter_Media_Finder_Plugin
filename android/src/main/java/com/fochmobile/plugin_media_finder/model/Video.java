package com.fochmobile.plugin_media_finder.model;

import java.util.HashMap;

public class Video {
    // Properties
    private long id;
    private String title;
    private long duration;
    private String size;
    private String uri;
    private String thumbnail;

    // empty constructor
    public Video(){}

    // constructor
    public Video(long id, String title, long duration, String size, String uri, String thumbnail) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.size = size;
        this.uri = uri;
        this.thumbnail = thumbnail;
    }

    // getter & setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    HashMap<String,Object> toMap(){
        HashMap<String,Object> videoMap = new HashMap<>();
        videoMap.put("id", id);
        videoMap.put("title",title);
        videoMap.put("thumbnail",thumbnail);
        videoMap.put("size",size);
        videoMap.put("duration", duration);
        videoMap.put("uri",uri);
        return videoMap;
    }
}
