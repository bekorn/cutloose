package com.cutloose.cutloose.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by finge on 5/6/2018.
 */

public class Profile {
    private String id;
    private String name;
    private String photoPath;
    private Date birthDate;
    private ArrayList<String> tags;

    public Profile() {
    }

    public Profile(String id, String name, String photoPath, Date birthDate, ArrayList<String> tags) {
        this.id = id;
        this.name = name;
        this.photoPath = photoPath;
        this.birthDate = birthDate;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getId() {
        return id;
    }

    public ArrayList<String> getTags() {
        return tags;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }
}
