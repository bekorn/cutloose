package com.cutloose.cutloose.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "clicks")
public class Clicks {
    private String activityId;
    private String userId;

    @PrimaryKey(autoGenerate = true)
    public int cId;

    public Clicks() {
    }

    @Ignore
    public Clicks(String activityId) {
        this.activityId = activityId;
    }

    @Ignore
    public Clicks(String activityId, String userId) {
        this.activityId = activityId;
        this.userId = userId;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(@NonNull int cId) {
        this.cId = cId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
