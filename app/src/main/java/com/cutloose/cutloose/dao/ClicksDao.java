package com.cutloose.cutloose.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.cutloose.cutloose.model.Clicks;

@Dao
public interface ClicksDao {
    @Insert
    public void insertClicks(Clicks... clicks);

    @Query("SELECT COUNT(cId) FROM clicks WHERE activityId = :activityId AND userId = :userId")
    public int getCount(String activityId, String userId);
}
