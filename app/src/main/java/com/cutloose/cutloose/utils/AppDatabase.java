package com.cutloose.cutloose.utils;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.cutloose.cutloose.dao.ClicksDao;
import com.cutloose.cutloose.model.Clicks;

@Database(entities = {Clicks.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ClicksDao clicksDao();
}
