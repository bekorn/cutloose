package com.cutloose.cutloose.model;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by finge on 5/6/2018.
 */

public class Chat extends BaseModel {
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

    public String showOwners() {
        List<String> names = new ArrayList<>();

        for( Profile profile : owners.subList( 0, Math.min( 3, owners.size() ) ) ) {
            names.add( profile.getName() );
        }

        return TextUtils.join( ", ", names );
    }

    public Chat() {
    }

    public Chat(String id, Event eventType, ArrayList<Profile> owners) {

        this.id = id;
        this.eventType = eventType;
        this.owners = owners;
    }
}
