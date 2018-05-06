package com.cutloose.cutloose.model;

import java.util.ArrayList;

/**
 * Created by finge on 5/6/2018.
 */

public class Chat {
    private String id;
    private Event eventType;
    private ArrayList<Profile> owners;

    public void setId(String id) {
        this.id = id;
    }

    public void setEventType(Event eventType) {
        this.eventType = eventType;
    }

    public void setOwners(ArrayList<Profile> owners) {
        this.owners = owners;
    }

    public String getId() {

        return id;
    }

    public Event getEventType() {
        return eventType;
    }

    public ArrayList<Profile> getOwners() {
        return owners;
    }

    public Chat() {
    }

    public Chat(String id, Event eventType, ArrayList<Profile> owners) {

        this.id = id;
        this.eventType = eventType;
        this.owners = owners;
    }
}
