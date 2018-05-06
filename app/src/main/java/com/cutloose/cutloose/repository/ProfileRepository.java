package com.cutloose.cutloose.repository;

import android.arch.lifecycle.MutableLiveData;
import android.provider.ContactsContract;

import com.cutloose.cutloose.model.Profile;

import java.util.ArrayList;
import java.util.Date;



public class ProfileRepository {
    private static final ProfileRepository ourInstance = new ProfileRepository();
    public static ProfileRepository getInstance() {
        return ourInstance;
    }

    public void setUserProfile(MutableLiveData<Profile> mut){
        mut.setValue(new Profile("1","Barkin","http://barkinkaplan.com/images/HeadShot1.jpg",new Date(135242471),new ArrayList<String>()));
    }
}
