package com.cutloose.cutloose.model;

import android.databinding.Bindable;

import com.cutloose.cutloose.BR;

public class Event extends BaseModel {

    private String eventId;
    private String name;
    private String pictureURL;
    private int popularity;
    private int activeEventsCount;

    public Event() {
    }

    public Event(String eventId, String name, String pictureURL, int popularity, int activeEventsCount) {
        this.eventId = eventId;
        this.name = name;
        this.pictureURL = pictureURL;
        this.popularity = popularity;
        this.activeEventsCount = activeEventsCount;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
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

    @Bindable
    public int getPopularity() {
        return popularity;
    }

    public void setPopularity( int popularity ) {
        this.popularity = popularity;
        notifyPropertyChanged(BR.popularity);
    }

    public int getActiveEventsCount() {
        return activeEventsCount;
    }

    public void setActiveEventsCount( int activeEventsCount ) {
        this.activeEventsCount = activeEventsCount;
    }
}
