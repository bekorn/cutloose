package com.cutloose.cutloose.model;

public class Event {

    private String name;
    private String pictureURL;
    private int popularity;
    private int activeEventsCount;

    public Event() {
    }

    public Event(String name, String pictureURL, int popularity, int activeEventsCount ) {
        setName( name );
        setPictureURL( pictureURL );
        setPopularity( popularity );
        setActiveEventsCount( activeEventsCount );
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL( String pictureURL ) {
        this.pictureURL = pictureURL;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity( int popularity ) {
        this.popularity = popularity;
    }

    public int getActiveEventsCount() {
        return activeEventsCount;
    }

    public void setActiveEventsCount( int activeEventsCount ) {
        this.activeEventsCount = activeEventsCount;
    }
}
