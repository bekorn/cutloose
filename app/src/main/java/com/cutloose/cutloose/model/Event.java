package com.cutloose.cutloose.model;

import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.cutloose.cutloose.BR;

public class Event extends BaseModel implements Parcelable {

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

    protected Event(Parcel in) {
        eventId = in.readString();
        name = in.readString();
        pictureURL = in.readString();
        popularity = in.readInt();
        activeEventsCount = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(eventId);
        dest.writeString(name);
        dest.writeString(pictureURL);
        dest.writeInt(popularity);
        dest.writeInt(activeEventsCount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    @Bindable
    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
        notifyPropertyChanged(BR.popularity);
    }

    @Bindable
    public int getActiveEventsCount() {
        return activeEventsCount;
    }

    public void setActiveEventsCount(int activeEventsCount) {
        this.activeEventsCount = activeEventsCount;
        notifyPropertyChanged(BR.activeEventsCount);
    }
}
