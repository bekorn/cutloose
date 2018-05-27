package com.cutloose.cutloose;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.cutloose.cutloose.utils.AppDatabase;

public class CutLoose extends Application {
    AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "cutloose_db").allowMainThreadQueries().build();

    }

    public AppDatabase getDatabase() {
        return database;
    }
}
