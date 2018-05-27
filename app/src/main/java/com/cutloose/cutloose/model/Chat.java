package com.cutloose.cutloose.model;

import android.databinding.Bindable;
import android.text.TextUtils;

import com.cutloose.cutloose.BR;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by finge on 5/6/2018.
 */

public class Chat extends BaseModel {
    private String id;
    private String eventId;
    private Event eventType;
    private long createdAt;
    private ArrayList<Profile> ownersList;

    public void setId(String id) {
        this.id = id;
    }

    public void setEventType(Event eventType) {
        this.eventType = eventType;
    }

    public void setOwnersList(ArrayList<Profile> ownersList) {
        this.ownersList = ownersList;
        notifyPropertyChanged(BR.owners);
    }

    public String getId() {

        return id;
    }

    public Event getEventType() {
        return eventType;
    }

    @Bindable
    public String getOwners() {
        if (ownersList == null || ownersList.size() == 0) {
            return "Loading...";
        }

        List<String> names = new ArrayList<>();

        for (Profile profile : ownersList.subList(0, Math.min(3, ownersList.size()))) {
            names.add(profile.getName());
        }

        return TextUtils.join(", ", names);
    }

    public Chat() {
    }

    public Chat(String id, Event eventType, ArrayList<Profile> ownersList) {

        this.id = id;
        this.eventType = eventType;
        this.ownersList = ownersList;
    }

    public Chat(String id, Event eventType, long createdAt, ArrayList<Profile> ownersList) {
        this.id = id;
        this.eventType = eventType;
        this.createdAt = createdAt;
        this.ownersList = ownersList;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
